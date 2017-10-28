package com.laoge.raining.server.register;

import com.laoge.raining.server.config.RainAutoConfiguration;
import com.laoge.raining.server.config.RainServerProperties;
import com.laoge.raining.server.context.RainApplicationContext;
import com.laoge.raining.server.util.BeanUtil;
import com.laoge.raining.server.util.InetAddressUtil;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 在 rpc 服务启动后将系统的 类路径注入到 etcd 中
 * Created by yuhou on 2017/9/14.
 */
@Configuration
@EnableConfigurationProperties(RainServerProperties.class)
@AutoConfigureAfter({RainAutoConfiguration.class, BeanUtil.class})
public class RainAutoRegisterConfiguration implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(RainAutoRegisterConfiguration.class);


    @Resource
    private EtcdClient etcdClient;

    @Resource
    private RainApplicationContext rainApplicationContext;

    @Resource
    private RainServerProperties rainServerProperties;

    @Override
    public void afterPropertiesSet() throws Exception {

        String basePath = "rain-server-raining";// + rainServerProperties.getServerName();

        rainApplicationContext.setBasePath(basePath);
        //注册根目录
        try {
            etcdClient.getDir(basePath).send().get();
        } catch (Exception e) {
            logger.error("etcd base path not having {}", e.getLocalizedMessage());

            logger.info("etcd base path create!");
            etcdClient.putDir(basePath).send().get();
        }
        String hosts = InetAddressUtil.getLocalHostLANAddress().getHostAddress() + ":" + rainServerProperties.getPort();

        rainApplicationContext.setServerBathPath(hosts);

        // etcdClient.putDir(basePath).send().get();

        logger.info("send etcd base path {}", basePath);

        //将rainContent 中的 对象 信息进行 etcd 注册
        Map<String, String> content = rainApplicationContext.beanContent();

        for (Map.Entry<String, String> entry : content.entrySet()) {
            //上传服务的路径
            String classPath = basePath + "/" + entry.getKey();//basePath + "/" + entry.getKey();
            String hostss = "";
            try {
                EtcdKeysResponse response = etcdClient.get(classPath).send().get();
                hostss = response.getNode().getValue();
                if (!StringUtils.isEmpty(hostss)) {
                    hostss += ";" + hosts;
                }
            } catch (Exception e) {
                logger.error(classPath + "is frist one register!");
                hostss = hosts;
            }
            etcdClient.put(classPath, hostss).send().get();
            logger.info("send etcd class path {}", classPath);

           /* Method[] methods = BeanUtil.getBean(entry.getValue()).getClass().getDeclaredMethods();

            for (Method method : methods) {
                //获取method的参数类型
                Parameter[] parameters = method.getParameters();
                String methodPath = classPath + "/" + method.getName();
                Map<Integer, String> paramMap = Maps.newHashMap();
                int i = 0;
                String paramStr = "";
                for (Parameter parameter : parameters) {
                    paramStr += parameter.getName() + ":" + parameter.getType().getSimpleName() + ",";
                    paramMap.put(i, parameter.getType().getTypeName());
                    i++;
                }
                if (!StringUtils.isEmpty(paramStr))
                    paramStr = paramStr.substring(0, paramStr.length() - 1);
                logger.info("send etcd method path:{},metohd param:{}", methodPath + "@" + paramStr, paramMap);

                etcdClient.put(methodPath + "@" + paramStr, JacksonUtil.bean2Json(paramMap)).send().get();

            }*/
        }
    }
}
