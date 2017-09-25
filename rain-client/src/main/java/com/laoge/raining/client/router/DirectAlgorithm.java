package com.laoge.raining.client.router;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 用户直连服务
 * Created by yuhou on 2017/9/22.
 */
public class DirectAlgorithm implements RouterAlgorithm {


    private final String address;

    //用于存储直连的数据
    private Node nodes;


    public DirectAlgorithm(String address) {
        this.address = address;
        this.init();
    }

    @Override
    public void init() {
        if (null != nodes) {
            return;
        }
        String[] addr = address.split(":");
        nodes = new Node();

        nodes.setIp(addr[0]);
        nodes.setPort(Integer.valueOf(addr[1]));

    }

    @Override
    public Node getClientNode() {
        return nodes;
    }
}
