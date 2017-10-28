package com.laoge.raining.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求body
 */
@Data
public class RainRequestBody implements Serializable {

    private String body;
}
