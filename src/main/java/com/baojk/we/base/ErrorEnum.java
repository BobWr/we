package com.baojk.we.base;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/17
 */
public enum ErrorEnum implements Error {

    SYS__ERROR(200, "1000", "系统错误"),
    INCORRECT_INPUT_ERROR(200, "1001", "用户名或密码错误"),
    NO_USER_ERROR(200, "1002", "用户不存在"),
    USER_NOT_LOGIN_ERROR(200, "1003", "用户未登录"),
    NO_ARTICLE_ERROR(200, "1004", "无此文章"),
    IMG_UPLOAD_FAILED(200, "1005", "图片上传失败"),;

    /**
     * 业务的错误码
     */
    private String code;

    /**
     * HTTP status code
     */
    private int httpCode;

    /**
     * 业务的错误信息说明
     */
    private String message;

    ErrorEnum(int httpCode, String code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    @Override
    public int getHttpCode() {
        return this.httpCode;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
