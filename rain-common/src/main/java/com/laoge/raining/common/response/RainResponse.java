package com.laoge.raining.common.response;

import com.laoge.raining.common.request.RainRequestBody;
import com.laoge.raining.common.request.RainRequestHead;
import lombok.Data;

import java.io.Serializable;

/**
 * 相应实体
 */
@Data
public class RainResponse implements Serializable {

    private RainResponseHead responseHead;

    private RainResponseBody responseBody;
}
