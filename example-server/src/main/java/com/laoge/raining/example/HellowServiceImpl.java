package com.laoge.raining.example;

import com.laoge.raining.entity.Person;
import com.laoge.raining.server.annotation.RainServer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Person sayHello(Person person) {
        System.out.println(person.toString());
        person.setName("你好");
        Map<String, String> rets = new HashMap<String, String>();
        rets.put("aaa", "额呢");
        person.setRet(rets);
        person.setId(2);
        return person;
    }

}
