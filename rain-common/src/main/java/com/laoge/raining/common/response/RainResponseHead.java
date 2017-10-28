package com.laoge.raining.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class RainResponseHead implements Serializable {

    //类路径
    private String classRUI;

    //类名称
    private String className;

    //方法名称
    private String methodName;
}
