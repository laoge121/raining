package com.laoge.raining.register;

import com.laoge.raining.register.config.EtcdClientProperties;
import mousio.client.retry.RetryNTimes;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdVersionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 系统初始化 etcd 客户端
 * Created by yuhou on 2017/9/14.
 */
@Configuration
@EnableConfigurationProperties(EtcdClientProperties.class)
public class EtcdAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(EtcdAutoConfiguration.class);

    @Resource
    private EtcdClientProperties etcdClientProperties;

    @Bean
    @ConditionalOnMissingBean
    public EtcdClient etcdClient() {

        List<URI> uriList = etcdClientProperties.getUris();

        logger.error("etc 服务地址{}", uriList);

        EtcdClient etcdClient = new EtcdClient(uriList.toArray(new URI[uriList.size()]));

        etcdClient.setRetryHandler(new RetryNTimes(etcdClientProperties.getMsBeforeRetry(), etcdClientProperties.getTimesToRetry()));

        EtcdVersionResponse versionResponse = etcdClient.version();

        if (null == versionResponse) {
            logger.error("etcd client is invalid:url:{}", uriList);
        } else {
            logger.error("etcd client started:url:{}", uriList);
        }
        return etcdClient;
    }

}
