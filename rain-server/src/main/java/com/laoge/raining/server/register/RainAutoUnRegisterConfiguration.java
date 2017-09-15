package com.laoge.raining.server.register;

import com.laoge.raining.server.context.RainApplicationContext;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdKeyAction;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 服务停止 清空rain 上下文的内容 取消etcd 的注册信息
 * Created by yuhou on 2017/9/15.
 */
@Component
public class RainAutoUnRegisterConfiguration implements DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(RainAutoUnRegisterConfiguration.class);

    @Resource
    private EtcdClient etcdClient;

    @Resource
    private RainApplicationContext rainApplicationContext;

    @Override
    public void destroy() throws Exception {

        EtcdKeysResponse r = etcdClient.getDir(rainApplicationContext.getServerBathPath()).send().get();

        if (StringUtils.isEmpty(r.getNode().getKey())) {
            return;
        }

        //清空 当前路径下的 所有数据
        r = etcdClient.deleteDir(rainApplicationContext.getServerBathPath()).recursive().send().get();
        if (r.action == EtcdKeyAction.delete) {
            logger.info("server destory success!");
        } else {
            logger.error("server destory error!");
            etcdClient.refresh(rainApplicationContext.getServerBathPath(), 1).send().get();
        }
    }
}
