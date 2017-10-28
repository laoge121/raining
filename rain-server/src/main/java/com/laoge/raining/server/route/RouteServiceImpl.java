package com.laoge.raining.server.route;

import com.google.common.collect.Lists;
import com.laoge.raining.common.route.RainRequest;
import com.laoge.raining.common.route.RainRequestParam;
import com.laoge.raining.common.route.RainResponse;
import com.laoge.raining.common.route.RouteService;
import com.laoge.raining.common.serialize.KryoUtil;
import com.laoge.raining.server.util.BeanUtil;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * 路由实现
 */
public class RouteServiceImpl implements RouteService.Iface {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);
    @Override
    public RainResponse route(RainRequest rainRequest) throws TException {

        logger.info("request param RainRequst: {}", rainRequest);

        if (StringUtils.isEmpty(rainRequest.getClassRUI())) {
            throw new RuntimeException("路径参数异常!");
        }
        Object object = BeanUtil.getBean(rainRequest.getClassName());

        Class[] args = null;
        List<Object> paramList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(rainRequest.getParamList())) {
            try {
                Iterator<RainRequestParam> iterator = rainRequest.getParamList().iterator();
                args = new Class[rainRequest.getParamListSize()];
                int i=0;
                for (; iterator.hasNext(); ) {
                    RainRequestParam param = iterator.next();
                    Class clazz=   Class.forName(param.getClassRUI());
                    Object obj= KryoUtil.readObjectFromString(param.getBody(),clazz);
                    paramList.add(obj);
                    args[i]=clazz;
                    i++;
                }
            } catch (Exception e) {
                logger.error("Route Service Parsing data Exception!", e);
                return RainResponseUtil.responseError("parsing data Exception!");
            }
        }
        try {

            Method method = object.getClass().getMethod(rainRequest.getMethodName(), args);
            Object result = method.invoke(object, paramList.toArray());
            logger.info("class: {} method: {} result data:{}", rainRequest.getClassName(), method, result);
            return RainResponseUtil.responseSuccess(result);
        } catch (Exception e) {
            logger.error("方法执行异常{}", e);
            throw new RuntimeException(e);
        }
    }
}
