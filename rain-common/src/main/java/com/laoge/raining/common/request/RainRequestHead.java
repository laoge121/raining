package com.laoge.raining.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求头数据处理
 */
@Data
public class RainRequestHead implements Serializable {

    //类路径
    private String classRUI;

    //类名称
    private String className;

    //方法名称
    private String methodName;

    //链路
    private String link;

}
