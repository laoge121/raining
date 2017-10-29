package com.laoge.raining.client.controller;

import com.laoge.raining.api.example.NiceService;
import com.laoge.raining.client.annotation.RainClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RainClient(appname = "raining")
    private NiceService niceService;

    @RequestMapping("/1")
    @ResponseBody
    public String test(){
        return niceService.sayNice();
    }
}
