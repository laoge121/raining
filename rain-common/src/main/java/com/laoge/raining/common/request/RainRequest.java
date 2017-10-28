package com.laoge.raining.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求实体
 */
@Data
public class RainRequest implements Serializable {

    private RainRequestHead requestHead;

    private RainRequestBody requestBody;
}
