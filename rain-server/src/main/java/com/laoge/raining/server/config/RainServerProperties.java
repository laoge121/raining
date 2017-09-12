package com.laoge.raining.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统环境参数配置
 * Created by yuhou on 2017/9/12.
 */
@Data
@ConfigurationProperties(prefix = "rain.server")
public class RainServerProperties {

    //服务监听端口
    private int port = 2181;

    //进程工作最小值默认系统线程数
    private int minWorker = Runtime.getRuntime().availableProcessors();

    //进程工作最大值 默认两倍
    private int maxWorker = Runtime.getRuntime().availableProcessors() * 2;

    //队列大小
    private int workQueueCapacity = 1024;

    //服务名称
    private String serverName;

    //线程类型
    private String workThread;

    //协议 默认采用 二进制协议
    private String protocol = "org.apache.thrift.protocol.TBinaryProtocol";
}
