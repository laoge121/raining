package com.laoge.raining.example;

import com.laoge.raining.entity.Person;

/**
 * Created by yuhou on 2017/9/18.
 */
public interface NiceService {

    public String sayNice();

    public String sayNice(Person person);

    public String sayNice(Person person, String hello);

    public String syaNice(Person person, int num);
}
