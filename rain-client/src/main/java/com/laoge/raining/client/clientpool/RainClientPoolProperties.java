package com.laoge.raining.client.clientpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 链接池对象管理
 * Created by yuhou on 2017/9/22.
 */
@Data
@ConfigurationProperties(prefix = "rain.client.pool")
public class RainClientPoolProperties {
    private int poolMaxTotalPerKey = 200;
    private int poolMaxIdlePerKey = 40;
    private int poolMinIdlePerKey = 10;
    private long poolMaxWait = 1000;
}
