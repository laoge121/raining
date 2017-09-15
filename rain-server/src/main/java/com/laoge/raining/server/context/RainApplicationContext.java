package com.laoge.raining.server.context;

import java.lang.reflect.Parameter;

/**
 * 系统的 对象存储结构体
 * Created by yuhou on 2017/9/14.
 */
public interface RainApplicationContext {

    /**
     * bean 初始化到系统的 上下文中
     *
     * @param beanName 容器中存储的 bean 名称
     * @param object   对象
     */
    void beanMappingContent(String beanName, Object object);


    /**
     * 方法映射到容器中
     *
     * @param beanName
     * @param method
     * @param parameters
     */
    void beanMethodMappingContent(String beanName, String method, Parameter... parameters);

}
