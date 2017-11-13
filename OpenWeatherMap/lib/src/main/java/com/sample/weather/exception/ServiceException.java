package com.sample.weather.exception;

/**
 * Custom exception class for service calls.
 * Created by VimalRaj on 11/12/2017.
 */

public class ServiceException extends RuntimeException {

    private int code;
    private String httpMessage;

    public ServiceException(final String errorMessage, final int code, final String httpMessage) {
        super(errorMessage);
        this.code = code;
        this.httpMessage = httpMessage;
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public int getCode() {
        return code;
    }

    public String getHttpMessage() {
        return httpMessage;
    }
}
