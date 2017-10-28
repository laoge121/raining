package com.laoge.raining.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring 上下文获取bean工具类
 * Created by yuhou on 2017/9/12.
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    //spring上下文
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获得bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        logger.info("bean util init beanName:{}",name);
        return applicationContext.getBean(name);
    }
}
