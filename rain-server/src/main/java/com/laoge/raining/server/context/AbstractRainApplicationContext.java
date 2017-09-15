package com.laoge.raining.server.context;

import com.google.common.collect.Maps;
import com.laoge.raining.server.exception.RainServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 定义系统的 基础操作
 * Created by yuhou on 2017/9/14.
 */
@Component
public abstract class AbstractRainApplicationContext implements RainApplicationContext {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRainApplicationContext.class);

    /**
     * ETCD 本机注册服务根路径
     */
    private static String SERVER_BATH_PATH = "";

    /**
     * 基础服务路径
     */

    private static String BASE_PATH = "rain-server-";

    /**
     * 系统 对外提供的服务bean
     * <p>
     * key class.url:类的全路径
     * val class :类对象
     */
    private static final Map<String, Object> content = Maps.newHashMap();

    /**
     * 记录系统 服务 方法mapping 信息
     */
    private static final Map<String, Parameter[]> methodMappingContent = Maps.newHashMap();

    @Override
    public String getServerBathPath() {
        return SERVER_BATH_PATH;
    }

    @Override
    public void setServerBathPath(String serverBathPath) {
        SERVER_BATH_PATH = serverBathPath;
    }

    public  String getBasePath() {
        return BASE_PATH;
    }

    public  void setBasePath(String basePath) {
        BASE_PATH = basePath;
    }

    @Override
    public void beanMappingContent(String beanName, Object object) {
        if (content.containsKey(beanName)) {
            logger.error(" rain contenxt name {} repetition!", beanName);
            throw new RainServerException("rain contenxt name " + beanName + " repetition!");
        }
        content.put(beanName, object);
    }

    @Override
    @Deprecated
    public void beanMethodMappingContent(String beanName, String method, Parameter... parameters) {

        methodMappingContent.put(beanName + "@" + method + "@" + parameters.length, parameters);
    }

    protected Map<String, Object> getContent() {
        return content;
    }

    protected Map<String, Parameter[]> getMethodMappingContent() {
        return methodMappingContent;
    }
}
