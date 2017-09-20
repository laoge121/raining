package com.laoge.raining.register.listener;

import mousio.client.promises.ResponsePromise;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CancellationException;

/**
 * etcd 节点监听 监听数据变动
 * 逻辑整理:如果节点监听出发了监听事件 如果没有再次进行事件的监听绑定那么事件就会失效 需要继续进行绑定监听
 * Created by yuhou on 2017/9/20.
 */
public abstract class EtcdListener implements ResponsePromise.IsSimplePromiseResponseHandler<EtcdKeysResponse> {

    //监听日志
    private final static Logger logger = LoggerFactory.getLogger(EtcdListener.class);

    private EtcdClient etcdClient;

    //监听路径
    private String listenerPath;

    public EtcdListener(EtcdClient etcdClient, String listenerPath) {

        this.etcdClient = etcdClient;
        this.listenerPath = listenerPath;
    }

    @Override
    public void onResponse(ResponsePromise<EtcdKeysResponse> response) {

        boolean isClosed = false;
        try {
            EtcdKeysResponse etcdKeysResponse = response.get();

            //监听处理
            this.listenerExecute(etcdKeysResponse);

        } catch (Exception e) {
            if (e.getCause() instanceof CancellationException) {
                logger.warn("etcd client was closed");
                isClosed = true;
                return;
            }
            logger.error("ectd listener exception {}", e);
        } finally {
            for (; true; ) {
                if (isClosed) {
                    break;
                }
                try {
                    EtcdKeysResponse etcdKeysResponse = etcdClient.get(listenerPath).send().get();
                    if (null == etcdKeysResponse) {
                        logger.warn("keys response is null!");
                        break;
                    }

                    long modifyIndex = etcdKeysResponse.etcdIndex;
                    etcdClient.get(listenerPath).recursive().waitForChange(modifyIndex).send().addListener(this);
                    break;
                } catch (Exception e) {
                    logger.error("update listener index error:{}", e);
                }
            }
        }
    }

    /**
     * 监听处理
     *
     * @param etcdKeysResponse
     */
    public void listenerExecute(EtcdKeysResponse etcdKeysResponse) {

        if (null == etcdKeysResponse) {
            return;
        }

        switch (etcdKeysResponse.action) {
            case set:
                setChangeExecute();
            case get:
                getChangeExecute();
            case create:
                createChangeExecute();
            case update:
                updateChangeExecute();
            case delete:
                deleteChangeExecute();
            case expire:
                expireChangeExecute();
            case compareAndSwap:
                compareAndSwapChangeExecute();
            case compareAndDelete:
                compareAndDeleteChangeExecute();
            default:
                logger.warn("unknown action is {}", etcdKeysResponse.action.toString());
                break;
        }
    }

    /**
     * 事件监听逻辑处理 start
     */
    public abstract void getChangeExecute();

    public abstract void setChangeExecute();

    public abstract void createChangeExecute();

    public abstract void expireChangeExecute();

    public abstract void updateChangeExecute();

    public abstract void deleteChangeExecute();

    public abstract void compareAndSwapChangeExecute();

    public abstract void compareAndDeleteChangeExecute();
    // 事件监听逻辑处理 end

}
