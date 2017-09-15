package com.laoge.raining.server.context;

import com.laoge.raining.server.annotation.RainServer;
import com.laoge.raining.server.config.RainAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

/**
 * 初始化rain bean 服务器
 * Created by yuhou on 2017/9/14.
 */
@Configuration
public class RainContextInitialization implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(RainAutoConfiguration.class);

    @Resource
    private RainApplicationContextInitializer applicationContentInitializer;

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

        applicationContentInitializer.beanMappingContent(s, o);
        logger.info("init rain content bean name:{},class:{}", s, o);

    }
}
