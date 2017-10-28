package com.laoge.raining.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求body
 */
@Data
public class RainResponseBody implements Serializable {

    private String body;
}
