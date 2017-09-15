package com.laoge.raining.server.register;

import com.laoge.raining.server.config.RainAutoConfiguration;
import com.laoge.raining.server.config.RainServerProperties;
import mousio.etcd4j.EtcdClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 在 rpc 服务启动后将系统的 类路径注入到 etcd 中
 * Created by yuhou on 2017/9/14.
 */
@Configuration
@EnableConfigurationProperties(RainServerProperties.class)
@AutoConfigureAfter(RainAutoConfiguration.class)
public class RainAutoRegisterConfiguration implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(RainAutoRegisterConfiguration.class);


    @Resource
    private EtcdClient etcdClient;


    @Override
    public void afterPropertiesSet() throws Exception {

        //将rainContent 中的 对象 信息进行 etcd 注册


    }
}
