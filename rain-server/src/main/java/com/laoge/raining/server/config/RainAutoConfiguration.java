package com.laoge.raining.server.config;

import com.laoge.raining.common.RainThreadFactory;
import com.laoge.raining.common.route.RouteService;
import com.laoge.raining.server.exception.RainServerException;
import com.laoge.raining.server.invoke.InvokeService;
import com.laoge.raining.server.invoke.InvokeServiceImpl;
import com.laoge.raining.server.route.RouteServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 初始化 thrift 环境 process 等
 * Created by yuhou on 2017/9/12.
 */
@Configuration
@EnableConfigurationProperties(RainServerProperties.class)
public class RainAutoConfiguration implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(RainAutoConfiguration.class);

    @Resource
    private RainServerProperties rainServerProperties;

    //spring上下文
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //协议默认采用二进制协议
    public TProtocolFactory protocolFactory() {
        return new TBinaryProtocol.Factory(true, true);
    }

    public TNonblockingServerTransport tNonblockingServerTransport() throws TTransportException {
        TNonblockingServerTransport tNonblockingServerTransport = null;

        try {
            tNonblockingServerTransport = new TNonblockingServerSocket(rainServerProperties.getPort());
        } catch (TTransportException e) {
            logger.error("TNonblockingServerTransport create error!", e);
            throw e;
        }
        return tNonblockingServerTransport;
    }

    public TProcessor processor() {
        RouteServiceImpl teslaService = (RouteServiceImpl) this.applicationContext.getBean("routeService");
        RouteService.Processor<RouteService.Iface> processor = new RouteService.Processor(teslaService);
        return processor;
    }


    public THsHaServer.Args hsHaServerArgs() throws TTransportException {

        THsHaServer.Args args;

        args = new THsHaServer.Args(tNonblockingServerTransport());

        args.protocolFactory(protocolFactory());

        args.processor(processor());

        args.executorService(createInvokerPool());

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) args.getExecutorService();

        threadPoolExecutor.getQueue();

        return args;
    }

    public ExecutorService createInvokerPool() {
        ExecutorService executorService = new ThreadPoolExecutor(rainServerProperties.getMaxWorker(), rainServerProperties.getMaxWorker(),
                60, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(rainServerProperties.getWorkQueueCapacity()), new RainThreadFactory("rain-server"));

        return executorService;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "rain.server.port", matchIfMissing = false)
    public TServer tServer() {
        try {
            THsHaServer.Args args = hsHaServerArgs();
            return new THsHaServer(args);
        } catch (Exception e) {
            logger.error("THsHaServer init error", e);
            throw new RainServerException(" ThshaServer init error");
        }

    }
}