package com.laoge.raining.example;

import com.laoge.raining.server.annotation.RainServer;
import org.springframework.stereotype.Service;

/**
 * rpc调用测试
 * Created by yuhou on 2017/9/12.
 */
@RainServer
@Service("hellowService")
public class HellowServiceImpl implements HellowService {
    @Override
    public String sayHello() {
        return "哈哈哈哈哈";
    }
}
