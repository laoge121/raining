package com.laoge.raining.client;

import com.google.common.collect.Maps;
import com.laoge.raining.client.annotation.RainClient;
import com.laoge.raining.client.invoke.InvokeService;
import com.laoge.raining.client.router.DirectAlgorithm;
import com.laoge.raining.client.router.Node;
import com.laoge.raining.client.router.RibbonAlgorithm;
import com.laoge.raining.client.router.RouterAlgorithm;
import com.laoge.raining.client.serialize.KryoUtil;
import mousio.etcd4j.EtcdClient;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * 扫描需要监听的bean 数据进行数据初始化
 * Created by yuhou on 2017/9/20.
 */
@Configuration
@ConditionalOnClass(RainClient.class)
public class RainClientBootstrap implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(RainClientBootstrap.class);

    //用于存储 调用服务的类信息
    private static final Map<String, Class> beanToProcess = Maps.newHashMap();

    //存储实例化的bean
    private static final Map<String, RainClientBean> rainClientMap = Maps.newConcurrentMap();

    @Resource
    private DefaultListableBeanFactory beanFactory;

    @Resource
    private GenericKeyedObjectPool<Node, TTransport> genericKeyedObjectPool;

    @Resource
    private EtcdClient etcdClient;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        //获取class 信息
        Class clazz = bean.getClass();
        for (; clazz != null; ) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(RainClient.class)) {
                    beanToProcess.put(beanName, clazz);
                }
            }

            clazz = clazz.getSuperclass();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        //如果不存在 直接返回
        if (!beanToProcess.containsKey(beanName)) {
            return bean;
        }

        //获取目标对象接口
        Object target = getTargetBean(bean);

        //获取引用的目标类
        Class clazz = beanToProcess.get(beanName);

        //获取所有使用@RianCient 注解的第三方服务
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(RainClient.class)) {
                continue;
            }
            RainClient rainClient = field.getAnnotation(RainClient.class);

            if (beanFactory.containsBean(field.getName())) {
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, target, beanFactory.getBean(field.getName()));
                continue;
            }

            String realClassName = getRealClassName(field.getType());

            RainClientBean rainClientBean = this.createRainClientBean(rainClient);
            rainClientMap.put(beanName + "-" + realClassName, rainClientBean);
            ProxyFactory proxyFactory = createFieldProxyFactory(target, field.getType(), field.getName());
            addProxyFactoryAdvice(proxyFactory, rainClientBean);

            proxyFactory.setFrozen(true);
            proxyFactory.setProxyTargetClass(true);

            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, target, proxyFactory.getProxy());
        }

        return bean;
    }

    private void addProxyFactoryAdvice(ProxyFactory factory, final RainClientBean rainClientBean) {
        factory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {

                Object result = null;

                for (int i = 0; i < rainClientBean.getRetryTiems(); i++) {

                    Node node = null;

                    TTransport tTransport = null;

                    node = rainClientBean.getRouter().getClientNode();

                    tTransport = genericKeyedObjectPool.borrowObject(node);

                    TProtocol tProtocol = new TBinaryProtocol(tTransport, true, true);

                    InvokeService.Client client = new InvokeService.Client(tProtocol);

                    Object objs[] = invocation.getArguments();
                    StringBuilder sb = new StringBuilder("");
                    for (Object obj : objs) {
                        sb.append(KryoUtil.writeObjectToString(obj) + "@^@");
                    }
                    String ret = client.invoke(1, "code", invocation.getThis().getClass().getTypeName(), invocation.getMethod().getName(), sb.toString());

                    if (StringUtils.isEmpty(ret)) {
                        throw new RuntimeException("request exception error::" + ret);
                    }

                    result = KryoUtil.readFromString(ret);
                    break;
                }

                return result;
            }
        });
    }

    /**
     * 创建 field的代理工程类
     *
     * @param target
     * @param type
     * @param name
     * @return
     */
    public ProxyFactory createFieldProxyFactory(Object target, Class<?> type, String name) {

        ProxyFactory proxyFactory = null;
        try {
            proxyFactory = new ProxyFactory(BeanUtils.instantiateClass(type));
        } catch (Exception e) {
            logger.error("create:{}:{}proxy factory exception {}", target, name, e);
            throw new RuntimeException("create:" + target + ":" + name + "proxy factory exception {}", e);
        }
        return proxyFactory;
    }

    /**
     * 创建调用bean
     *
     * @param
     * @param rainClient
     * @return
     */
    public RainClientBean createRainClientBean(RainClient rainClient) {
        RainClientBean rainClientBean = new RainClientBean();
        RouterAlgorithm router;
        if (StringUtils.isEmpty(rainClient.address())) {
            router = new RibbonAlgorithm(rainClient.appname(), etcdClient);
        } else {
            router = new DirectAlgorithm(rainClient.address());
        }

        rainClientBean.setRouter(router);
        rainClientBean.setTimeOut(rainClient.timeout());
        rainClientBean.setRetryTiems(rainClient.retryTimes());

        return rainClientBean;
    }


    public String getRealClassName(Class<?> clazz) {
        String className = clazz.getCanonicalName();
        String name = className.substring(0, className.lastIndexOf("."));
        logger.info("realClass name method ret old:{},new:{}", className, name);
        return name;
    }

    public Object getTargetBean(Object bean) {
        Object target = bean;

        try {
            while (AopUtils.isAopProxy(target)) {

                target = ((Advised) target).getTargetSource().getTarget();
            }
        } catch (Exception e) {
            logger.error("get target bean exception {}", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
