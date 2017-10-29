package com.laoge.raining.client.controller;

import com.laoge.raining.api.example.NiceService;
import com.laoge.raining.client.annotation.RainClient;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/nice")
    public String nice() {
        String str = niceService.sayNice();
        return str;
    }
}
