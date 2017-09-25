package com.laoge.raining.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 * Created by yuhou on 2017/9/12.
 */
public class RainThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private String namePrefix;
    private Boolean daemon = false;//设置是否守护线程

    public RainThreadFactory(String namePrefix) {
        this(namePrefix, false);
    }

    public RainThreadFactory(String name, Boolean daemon) {
        SecurityManager s = System.getSecurityManager();

        this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();

        this.namePrefix = name + "-pool-" + poolNumber.getAndIncrement() + "-thread-";

        this.daemon = daemon;
    }


    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (getDaemon())
            t.setDaemon(true);
        else {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

    public Boolean getDaemon() {
        return daemon;
    }

    public void setDaemon(Boolean daemon) {
        this.daemon = daemon;
    }
}
