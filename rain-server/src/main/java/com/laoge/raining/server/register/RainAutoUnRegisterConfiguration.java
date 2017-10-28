package com.laoge.raining.server.register;

import com.laoge.raining.server.context.RainApplicationContext;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdAuthenticationException;
import mousio.etcd4j.responses.EtcdException;
import mousio.etcd4j.responses.EtcdKeyAction;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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

        logger.info("RainAutoUnRegisterConfiguration destory!");

        EtcdKeysResponse r = etcdClient.getDir(rainApplicationContext.getBasePath()).send().get();

        if (StringUtils.isEmpty(r.getNode().getKey())) {
            logger.error("base path is empty error!");
            return;
        }

        //清空 当前路径下的 所有数据
        for (Map.Entry<String, String> entry : rainApplicationContext.beanContent().entrySet()) {
            String path = rainApplicationContext.getBasePath() + "/" + entry.getKey();
            EtcdKeysResponse response = null;
            String data = "";
            try {
                response = etcdClient.get(path).send().get();
                data = response.getNode().getValue();
                if (StringUtils.isEmpty(data)) {
                    etcdClient.delete(path).recursive().send().get();
                }
            } catch (EtcdException e) {
                logger.error("etcd get server path:{} info etcdException:{}", path, e);
            } catch (Exception e) {
                throw new Exception(e);
            }

            if (!StringUtils.isEmpty(data) && data.contains(rainApplicationContext.getServerBathPath())) {
                data = data.replaceAll(rainApplicationContext.getServerBathPath() + ";", "");
                if (StringUtils.isEmpty(data)) {
                    etcdClient.delete(path).recursive().send().get();
                } else {
                    etcdClient.put(path, data).send().get();
                }
            }


        }
        // r = etcdClient.deleteDir(rainApplicationContext.getServerBathPath()).recursive().send().get();
        // if (r.action == EtcdKeyAction.delete) {
        //     logger.info("server destory success!");
        // } else {
        //    etcdClient.refresh(rainApplicationContext.getBasePath(), 1).send().get();
        // }
    }
}
