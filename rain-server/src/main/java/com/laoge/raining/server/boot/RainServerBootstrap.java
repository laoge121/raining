package com.laoge.raining.server.boot;

import com.laoge.raining.server.config.RainThreadFactory;
import org.apache.thrift.server.TServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Configuration;

/**
 * 系统启动加载项
 * Created by yuhou on 2017/9/12.
 */
@Configuration
@ConditionalOnBean(TServer.class)
public class RainServerBootstrap implements SmartLifecycle {

    private final static Logger logger = LoggerFactory.getLogger(RainServerBootstrap.class);

    private RainThreadFactory rainFactory = new RainThreadFactory("thrift-sever-start");

    @Autowired
    private TServer tServer;

    private int phase = Integer.MAX_VALUE;


    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        if (isRunning()) {
            tServer.setShouldStop(true);
            tServer.stop();
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override
    public void start() {
        if (tServer == null) {
            return;
        }

        rainFactory.newThread(new ThriftServer(tServer)).start();
    }

    @Override
    public void stop() {
        stop(null);
    }

    @Override
    public boolean isRunning() {
        if (tServer != null) {

            return tServer.isServing();
        }
        return false;
    }

    @Override
    public int getPhase() {
        return this.phase;
    }

    class ThriftServer implements Runnable {

        private TServer server;

        public ThriftServer(TServer server) {
            this.server = server;
        }

        @Override
        public void run() {
            if (server != null) {
                logger.info("thrift server started!");
                server.serve();
            }
        }
    }
}
