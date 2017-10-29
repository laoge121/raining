package com.laoge.raining.client.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class RainClientScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println(">>>>>>>>>>>>>>>>>>postProcessBeanDefinitionRegistry>>>>>>>>>>>>>");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(">>>>>>>>>>>>>>>>>>postProcessBeanFactory>>>>>>>>>>>>>");

    }
}
