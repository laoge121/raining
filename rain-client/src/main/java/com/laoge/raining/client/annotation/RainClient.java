package com.laoge.raining.client.annotation;

import java.lang.annotation.*;

/**
 * 调用外部的服务接口
 * Created by yuhou on 2017/9/20.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RainClient {

    String appname();//调用的业务方app 名称

    String address() default "";

    int timeout() default 500;//单位毫秒

    int retryTimes() default 3;//重试次数
}
