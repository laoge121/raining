package com.laoge.raining.server.context;

import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 系统初始化
 * Created by yuhou on 2017/9/14.
 */
@Component("rainApplicationContext")
public class RainApplicationContextInitializer extends AbstractRainApplicationContext {

    @Override
    public Map<String, Object> beanContent() {
        return getContent();
    }

    @Override
    public Object getBean(String beanName) {
        Map<String, Object> content = getContent();
        if (content.containsKey(beanName)) {
            return content.get(beanName);
        }
        return null;
    }

    @Override
    public boolean containsBean(String beanName) {
        return getContent().containsKey(beanName);
    }

    @Override
    public Parameter[] getMethodParameter(String beanNameMethod) {

        Map<String, Parameter[]> methodMap = getMethodMappingContent();
        if (methodMap.containsKey(beanNameMethod)) {
            return methodMap.get(beanNameMethod);
        }
        return null;
    }

    @Override
    public boolean containsMethodPatameter(String beanNameMethod) {
        return getMethodMappingContent().containsKey(beanNameMethod);
    }
}
