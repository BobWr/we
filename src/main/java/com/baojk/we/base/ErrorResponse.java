package com.baojk.we.base;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/17
 */
public class ErrorResponse extends BaseResponse {

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(Error httpError) {
        this.code = httpError.getCode();
        this.message = httpError.getMessage();
    }

    public ErrorResponse(ApiException apiException) {
        this.code = apiException.getCode();
        System.out.println(apiException.getMessage());
        this.message = apiException.getMessage();
    }
}
