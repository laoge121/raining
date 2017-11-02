package com.laoge.raining.client.clientpool;

import com.google.common.collect.Maps;
import com.laoge.raining.client.router.Node;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 客户端链接池获取
 * Created by yuhou on 2017/9/21.
 */
@Component("rainConnectionFactory")
public class RainConnectionFactory extends BaseKeyedPooledObjectFactory<Node, TTransport> {

    private static final Logger logger = LoggerFactory.getLogger(RainConnectionFactory.class);

    private static final Map<String, Long> forbidMap = Maps.newConcurrentMap();

    //存储 链接对象
    private static final Map<String, TTransport> ttransportMap = Maps.newConcurrentMap();

    private static final int FORBI_TIME = 5000;

    @Override
    public void destroyObject(Node key, PooledObject<TTransport> p) throws Exception {
        if (p.getObject().isOpen()) {
            p.getObject().close();
        }
    }

    @Override
    public boolean validateObject(Node key, PooledObject<TTransport> p) {
        if (p.getObject().isOpen()) {
            return true;
        }
        return false;
    }

    @Override
    public TTransport create(Node node) throws Exception {

        logger.info("connection Factory transport info :{}", node);

        if (null == node) {
            throw new RuntimeException("获取链接节点接口数据为空!");
        }
        Assert.isTrue(!StringUtils.isEmpty(node.getIp()), "ip不能为空!");
       // Assert.isTrue(node.getPort() < 1, "端口不能为空!");

        TTransport tTransport = null;
        String address = node.getIp() + ":" + node.getPort();


        if (ttransportMap.containsKey(address) && (System.currentTimeMillis() - forbidMap.get(address) < FORBI_TIME)) {
            return ttransportMap.get(address);
        } else {
            tTransport = new TFramedTransport(new TSocket(node.getIp(), node.getPort(), 0 == node.getTimeout() ? 500 : node.getTimeout()));
            tTransport.open();
            ttransportMap.put(address, tTransport);
            forbidMap.put(address, System.currentTimeMillis());
            return tTransport;
        }
    }

    @Override
    public PooledObject<TTransport> wrap(TTransport client) {
        return new DefaultPooledObject<>(client);
    }

}