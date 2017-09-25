package com.laoge.raining.client;

import com.google.common.collect.Lists;
import com.laoge.raining.common.RainConstants;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by yuhou on 2017/9/21.
 */
@ConfigurationProperties(prefix = "rain.subscribe")
public class RainServerList extends AbstractServerList<RainServer> {

    private static final Logger logger = LoggerFactory.getLogger(RainServerList.class);

    private final EtcdClient etcdClient;

    private String appName;

    //注册订阅的服务列表
    private List<String> serverList = Lists.newArrayList();

    public RainServerList(EtcdClient etcdClient, String appName) {
        this.etcdClient = etcdClient;
        this.appName = appName;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        this.appName = iClientConfig.getClientName();
    }

    @Override
    public List<RainServer> getInitialListOfServers() {
        return getServers();
    }

    @Override
    public List<RainServer> getUpdatedListOfServers() {
        return getServers();
    }

    public List<RainServer> getServers() {
        if (null == etcdClient) {
            logger.warn("no available etcdClient server !");
            return Lists.newArrayList();
        }
        if (CollectionUtils.isEmpty(serverList)) {
            logger.warn("no subscribe  server list!");
            return Lists.newArrayList();
        }

        List<RainServer> retList = Lists.newArrayList();

        //for (String appName : serverList) {
        try {
            String basePath = RainConstants.LOAD_SERVER_PATH + appName;
            EtcdKeysResponse response = etcdClient.get(basePath).send().get();

            if (CollectionUtils.isEmpty(response.getNode().getNodes())) {
                logger.info("app " + appName + "no available server !");
                return Lists.newArrayList();
            }
            //rain-server-raining/172.18.53.24:2181
            for (EtcdKeysResponse.EtcdNode node : response.getNode().getNodes()) {

                String path = node.getKey();
                path = path.replaceAll(basePath + "/", "");
                String address[] = path.split(":");
                String ip = address[0];
                String port = address[1];
                RainServer rainServer = new RainServer(appName, appName, ip, Integer.valueOf(port));
                retList.add(rainServer);
            }

        } catch (Exception e) {
            logger.error("etcd get LOAD_SERVER_PATH " + appName + " exception{}", e);
            return Lists.newArrayList();
        }

        // }
        return retList;
    }
}
