package com.laoge.raining.register.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.util.List;

/**
 * etcd 客户端配置属性
 * Created by yuhou on 2017/9/14.
 */
@Data
@ConfigurationProperties(prefix = "rain.etcd")
public class EtcdClientProperties {

    private List<URI> uris;

    private int timesToRetry = 3;//从链次数

    private int msBeforeRetry = 200;//从连接毫秒数

}
