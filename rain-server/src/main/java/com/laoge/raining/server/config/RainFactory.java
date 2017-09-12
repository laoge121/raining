package com.laoge.raining.server.config;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 * Created by yuhou on 2017/9/12.
 */
public class RainFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    RainFactory(String name) {
        SecurityManager s = System.getSecurityManager();

        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();

        namePrefix = name + "-pool-" + poolNumber.getAndIncrement() + "-thread-";

    }


    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

}
