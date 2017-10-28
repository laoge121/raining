package com.laoge.raining.server.context;

import java.lang.reflect.Parameter;
import java.util.Map;

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
    void beanMappingContent(String beanName, String object);


    /**
     * 方法映射到容器中
     *
     * @param beanName
     * @param method
     * @param parameters
     */
    void beanMethodMappingContent(String beanName, String method, Parameter... parameters);

    /**
     * bean 容器内容
     *
     * @return
     */
    Map<String, String> beanContent();

    /**
     * 获取bean 名称
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);


    /**
     * 是否存在bean
     *
     * @param beanName
     * @return
     */
    boolean containsBean(String beanName);

    /**
     * 获取方法的参数列表
     *
     * @param beanNameMethod
     * @return
     */
    Parameter[] getMethodParameter(String beanNameMethod);

    /**
     * 是否存在方法参数
     *
     * @param beanNameMethod
     * @return
     */
    boolean containsMethodPatameter(String beanNameMethod);


    String getServerBathPath();

    void setServerBathPath(String serverBathPath);

    String getBasePath();

    void setBasePath(String basePath);

}
