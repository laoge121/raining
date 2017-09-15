package com.laoge.raining.server.context;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 定义系统的 基础操作
 * Created by yuhou on 2017/9/14.
 */
@Component
public abstract class AbstractRainApplicationContext implements RainApplicationContext {


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
    private static final Map<String, Object> methodMappingContent = Maps.newHashMap();


    @Override
    public void beanMappingContent(String beanName, Object object) {
        content.put(beanName, object);
    }

    @Override
    public void beanMethodMappingContent(String beanName, String method, Parameter... parameters) {
        methodMappingContent.put(beanName + "@" + method + "@" + parameters.length, parameters);
    }

    public static Map<String, Object> getContent() {
        return content;
    }

    public static Map<String, Object> getMethodMappingContent() {
        return methodMappingContent;
    }
}
