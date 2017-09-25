package com.laoge.raining.client.router;

import com.laoge.raining.client.RainServer;
import com.laoge.raining.client.RainServerList;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.loadbalancer.*;
import mousio.etcd4j.EtcdClient;

/**
 * ribbon 路由算法
 * Created by yuhou on 2017/9/21.
 */
public class RibbonAlgorithm implements RouterAlgorithm {

    private final String appName;

    private final EtcdClient etcdClient;

    private DynamicServerListLoadBalancer<RainServer> loadBalancer;

    public RibbonAlgorithm(String appName, EtcdClient etcdClient) {
        this.appName = appName;
        this.etcdClient = etcdClient;
        init();
    }

    @Override
    public void init() {
        DefaultClientConfigImpl defaultClientConfig = DefaultClientConfigImpl.getClientConfigWithDefaultValues();
        defaultClientConfig.setProperty(CommonClientConfigKey.ServerListUpdaterClassName, EtcdNotificationUpdater.class);
        loadBalancer = new DynamicServerListLoadBalancer<>(defaultClientConfig, new AvailabilityFilteringRule(), new DummyPing(), new RainServerList(etcdClient, appName), new ZoneAffinityServerListFilter<>(), new EtcdNotificationUpdater(etcdClient, appName));
    }

    @Override
    public Node getClientNode() {
        Server server = loadBalancer.chooseServer();
        if (null == server) {
            return null;
        }

        Node node = new Node();
        node.setIp(server.getHost());
        node.setPort(server.getPort());
        return node;
    }

    public DynamicServerListLoadBalancer<RainServer> getLoadBalancer() {
        return loadBalancer;
    }
}
