package com.laoge.raining.server.invoke;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * 业务接口请求路由业务
 * Created by yuhou on 2017/9/12.
 */
@Service("rainInvokeService")
public class InvokeServiceImpl implements InvokeService.Iface {

    @Override
    public String invoke(long callTime, String code, String path, String method, String paramJson) throws TException {
        return null;
    }
}
