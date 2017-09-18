package com.laoge.raining.example;

import com.laoge.raining.entity.Person;
import com.laoge.raining.server.annotation.RainServer;
import org.springframework.stereotype.Service;

/**
 * Created by yuhou on 2017/9/18.
 */
@RainServer
@Service("niceService")
public class NiceServiceImpl implements NiceService {
    @Override
    public String sayNice() {
        return "nice to meet you !";
    }

    @Override
    public String sayNice(Person person) {
        return "nice to meet you ! " + person.toString();
    }

    @Override
    public String sayNice(Person person, String hello) {
        return hello + "nice to meet you ! " + person.toString();
    }

    @Override
    public String syaNice(Person person, int num) {
        return num + "nice to meet you ! " + person.toString();

    }
}
