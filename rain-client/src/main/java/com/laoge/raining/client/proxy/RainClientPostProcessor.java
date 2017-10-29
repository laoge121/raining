package com.laoge.raining.client.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class RainClientPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,>>RainClientPostProcessor start>>");
    }
}
