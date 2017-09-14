package com.laoge.raining.register.register;

import lombok.Data;
import mousio.etcd4j.EtcdClient;

/**
 * 注册实体
 * Created by yuhou on 2017/9/14.
 */
@Data
public class EtcdRegister {
    private boolean isStart;

    private String path;

    private String key;

    private String value;

    private EtcdClient etcdClient;
}
