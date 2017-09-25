package com.laoge.raining.client.router;

import lombok.Data;

import java.io.Serializable;

/**
 * 路由地址
 * Created by yuhou on 2017/9/21.
 */
@Data
public class Node implements Serializable {

    private String ip;

    private int port;

    private int timeout = 500;

}
