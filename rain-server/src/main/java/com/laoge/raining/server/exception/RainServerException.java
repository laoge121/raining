package com.laoge.raining.server.exception;

/**
 * 自定义异常
 */
public class RainServerException extends RuntimeException {

    public RainServerException(String message) {
        super(message);
    }

    public RainServerException(String message, Throwable t) {
        super(message, t);
    }

}
