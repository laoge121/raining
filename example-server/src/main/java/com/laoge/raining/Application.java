package com.laoge.raining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by yuhou on 2017/9/12.
 */
@SpringBootApplication
@ComponentScan("com.laoge.raining")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        while (true) {
        }
    }

}