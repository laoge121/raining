package com.laoge.raining.common.exception;

import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class RainingException extends RuntimeException {

    private int code;

    private String mesage;

    public RainingException(String message, int code) {
        super(message);
        this.code = code;
        this.mesage = message;
    }
}
