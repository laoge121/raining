package com.laoge.raining.client.router;

import com.laoge.raining.common.RainConstants;
import com.laoge.raining.common.RainThreadFactory;
import com.laoge.raining.register.listener.EtcdListener;
import com.netflix.loadbalancer.ServerListUpdater;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 路由更新
 * Created by yuhou on 2017/9/21.
 */
public class EtcdNotificationUpdater implements ServerListUpdater {

    private static final Logger logger = LoggerFactory.getLogger(EtcdNotificationUpdater.class);

    private static class LazyHolder {

        private static final ExecutorService DEFAULT_SERVER_LIST_UPDATE_EXECUTOR = Executors.newCachedThreadPool(new RainThreadFactory("EtcdNotificationUpdater-thread", true));

        private static final Thread SHUTDOWN_THREAD = new RainThreadFactory("EtcdNotificationUpdater-shutdown").newThread(new Runnable() {
            @Override
            public void run() {
                try {
                    DEFAULT_SERVER_LIST_UPDATE_EXECUTOR.shutdownNow();
                    Runtime.getRuntime().removeShutdownHook(SHUTDOWN_THREAD);
                } catch (Exception e) {
                    logger.error("thread shutdown exception", e);
                }
            }
        });

        static {
            //添加系统关闭线程
            Runtime.getRuntime().addShutdownHook(SHUTDOWN_THREAD);
        }
    }

    private final AtomicBoolean isActive = new AtomicBoolean(false);

    private final EtcdClient etcdClient;

    private final String listenPath;

    private final ExecutorService refreshExecutor;

    private final AtomicLong lastUpdated = new AtomicLong(System.currentTimeMillis());


    public EtcdNotificationUpdater(EtcdClient etcdClient, String listenPath) {
        this(etcdClient, listenPath, LazyHolder.DEFAULT_SERVER_LIST_UPDATE_EXECUTOR);
    }

    public EtcdNotificationUpdater(EtcdClient etcdClient, String listenPath, ExecutorService refreshExecutor) {
        this.refreshExecutor = refreshExecutor;
        this.listenPath = RainConstants.LOAD_SERVER_PATH + "/" + listenPath;
        this.etcdClient = etcdClient;
    }

    @Override
    public void start(UpdateAction updateAction) {

        //如果不是活动开始那么就直接返回
        if (!isActive.compareAndSet(false, true)) {
            return;
        }

        try {
            EtcdResponsePromise response = etcdClient.get(listenPath).recursive().waitForChange().send();
            response.addListener(new EtcdListener(etcdClient, listenPath) {
                @Override
                public void getChangeExecute() {
                    return;
                }

                @Override
                public void setChangeExecute() {

                }

                @Override
                public void createChangeExecute() {

                }

                @Override
                public void expireChangeExecute() {
                    getRefreshExecutor().submit(new Runnable() {
                        @Override
                        public void run() {
                            updateAction.doUpdate();
                            lastUpdated.set(System.currentTimeMillis());
                        }
                    });
                }

                @Override
                public void updateChangeExecute() {

                }

                @Override
                public void deleteChangeExecute() {

                }

                @Override
                public void compareAndSwapChangeExecute() {

                }

                @Override
                public void compareAndDeleteChangeExecute() {

                }
            });


        } catch (IOException e) {
            logger.error("etcd listener exception,", e);
        }

    }

    @Override
    public void stop() {
        if (isActive.compareAndSet(true, false)) {
            logger.info("ribbon updator stoped");
        }
    }

    @Override
    public String getLastUpdate() {
        return new Date(lastUpdated.get()).toString();
    }

    @Override
    public long getDurationSinceLastUpdateMs() {
        return System.currentTimeMillis() - lastUpdated.get();
    }

    @Override
    public int getNumberMissedCycles() {
        return 0;
    }

    @Override
    public int getCoreThreads() {

        if (isActive.get()) {
            if (null != getRefreshExecutor() && getRefreshExecutor() instanceof ThreadPoolExecutor) {
                return ((ThreadPoolExecutor) getRefreshExecutor()).getCorePoolSize();
            }
        }
        return 0;
    }

    public ExecutorService getRefreshExecutor() {
        return refreshExecutor;
    }
}
