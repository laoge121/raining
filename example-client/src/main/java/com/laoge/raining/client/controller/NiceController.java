package com.laoge.raining.client.controller;

import com.laoge.raining.api.entity.Person;
import com.laoge.raining.api.example.HellowService;
import com.laoge.raining.api.example.NiceService;
import com.laoge.raining.client.annotation.RainClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 服务测试
 */
@RestController
@RequestMapping("/")
public class NiceController {

    @RainClient(appname = "raining")
    private NiceService niceService;

    @RainClient
    private HellowService hellowService;

    @RequestMapping("/nice")
    @ResponseBody
    public String nice() {
        Person person  = new Person();
        person.setName("ketty");
        String str = niceService.sayNice(person);
        return str;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
      return   hellowService.sayHello();
    }

    @RequestMapping("/hello1")
    @ResponseBody
    public String hello1(){
        return   hellowService.sayHello(new Person()).toString();
    }
}
