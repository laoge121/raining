package com.laoge.raining.client;

import com.netflix.loadbalancer.Server;

/**
 * 服务端信息
 * Created by yuhou on 2017/9/21.
 */
public class RainServer extends Server {


    private final MetaInfo metaInfo;

    public RainServer(final String sGroup, final String appName, String host, int port) {
        super(host, port);

        metaInfo = new MetaInfo() {
            @Override
            public String getAppName() {
                return appName;
            }

            @Override
            public String getServerGroup() {
                return sGroup;
            }

            @Override
            public String getServiceIdForDiscovery() {
                return null;
            }

            @Override
            public String getInstanceId() {
                return null;
            }
        };
    }


    @Override
    public MetaInfo getMetaInfo() {
        return metaInfo;
    }
}
