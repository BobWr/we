package com.baojk.we.base;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/17
 */
public class ApiException extends Exception {
    private int httpCode;
    private String code;
    private String message;

    public ApiException(Error error) {
        this.httpCode = error.getHttpCode();
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
