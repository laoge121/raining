package com.laoge.raining.client.clientpool;

import com.laoge.raining.client.router.Node;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.apache.thrift.transport.TTransport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * rain 客户端配置
 * <p>
 * Created by yuhou on 2017/9/22.
 */
@Configuration
@EnableConfigurationProperties(RainClientPoolProperties.class)
public class RainClientConfiguration {

    @Resource
    private RainClientPoolProperties poolProperties;

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean
    public GenericKeyedObjectPool<Node, TTransport> rainClientPool() {
        GenericKeyedObjectPoolConfig genericKeyedObjectPoolConfig = new GenericKeyedObjectPoolConfig();

        genericKeyedObjectPoolConfig.setJmxEnabled(false);
        genericKeyedObjectPoolConfig.setMaxTotalPerKey(poolProperties.getPoolMaxTotalPerKey());
        genericKeyedObjectPoolConfig.setMaxIdlePerKey(poolProperties.getPoolMaxIdlePerKey());
        genericKeyedObjectPoolConfig.setMinIdlePerKey(poolProperties.getPoolMinIdlePerKey());
        genericKeyedObjectPoolConfig.setMaxWaitMillis(poolProperties.getPoolMaxWait());
        genericKeyedObjectPoolConfig.setTestOnReturn(true);//返回数据前 校验

        return new GenericKeyedObjectPool<>(new RainConnectionFactory(), genericKeyedObjectPoolConfig);
    }
}
