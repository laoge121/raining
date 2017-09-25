package com.laoge.raining.client;

import com.laoge.raining.client.router.RouterAlgorithm;
import lombok.Data;

import java.lang.reflect.Constructor;

/**
 * 客户端调用对象
 * Created by yuhou on 2017/9/21.
 */
@Data
public class RainClientBean {

    private RouterAlgorithm router;

    private int timeOut;

    private int retryTiems;

}
