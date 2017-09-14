package com.laoge.raining.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 试下  BeanPostProcessor 实现当context 中的bean 实例完以后就进行 调用
 * BeanFactoryPostProcessor 功能比 BeanPostProcessor 强大能修改数据
 * Created by yuhou on 2017/9/13.
 */
@Component
public class BeanPostProcessors implements BeanPostProcessor, BeanFactoryPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        //  System.out.println("postProcessBeforeInitialization before");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {

        //  System.out.println("postProcessAfterInitialization end");
        return o;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //  System.out.println("《》《》《》《》《》《》《》 postProcessBeanFactory");

    }
}
