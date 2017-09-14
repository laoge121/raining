package com.laoge.raining.server.register;

import com.laoge.raining.server.annotation.RainServer;
import com.laoge.raining.server.config.RainAutoConfiguration;
import com.laoge.raining.server.config.RainServerProperties;
import com.laoge.raining.server.content.RainContent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;

/**
 * 自动注册服务
 * 扫描系统 支持的 bean 如果bean需要对外提供接口服务 那么就将
 * Created by yuhou on 2017/9/14.
 */
@Configuration
@EnableConfigurationProperties(RainServerProperties.class)
@AutoConfigureAfter(RainAutoConfiguration.class)
public class RainAutoRegisterConfiguration implements BeanPostProcessor {

    /**
     * 在容器bean 加载前判断 对象是否注解对外提供服务 如果注解对外提供服务 那就进行 内容存储
     *
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {

        //判断 对象内容实体对外提供 服务
        Annotation annotation = o.getClass().getDeclaredAnnotation(RainServer.class);
        if (null == annotation) {
            return o;
        }

        //初始化系统bean
        rainContentInitinalication(o, s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }

    /**
     * 将bean 加入到容器中
     *
     * @param o
     * @param s
     */
    public void rainContentInitinalication(Object o, String s) {
        System.out.print(s);
        System.out.println(o);
        RainContent.getContent().put(s, o);
    }

    public void rainContentRegister() {

    }
}
