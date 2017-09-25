package com.laoge.raining.client.router;

/**
 * 路由算法
 * Created by yuhou on 2017/9/21.
 */
public interface RouterAlgorithm {

    /**
     * 初始话
     */
    void init();

    /**
     * 获取链接客户端
     *
     * @return
     */
    Node getClientNode();
}


