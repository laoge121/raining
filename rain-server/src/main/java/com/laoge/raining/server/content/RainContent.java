package com.laoge.raining.server.content;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 系统的 对象存储结构体
 * Created by yuhou on 2017/9/14.
 */
public class RainContent {

    //系统 对外提供的服务bean
    /**
     * key class.url:类的全路径
     * val class :类对象
     */
    private final static Map<String, Object> content = Maps.newHashMap();

    public static Map<String, Object> getContent() {
        return content;
    }
}
