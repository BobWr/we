package com.baojk.we.base;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/10
 */
public class BaseResponse<T> implements Response {

    /**
     * 返回的Code
     */
    protected String code;
    /**
     * 返回信息的详细描述
     */
    protected String message;
    /**
     * 返回的数据域
     */
    private T data;

    /**
     * Default constructor
     */
    public BaseResponse() {

    }

    /**
     * 基于成功消息构建的返回结果
     *
     * @param success
     */
    public BaseResponse(SuccessConstants success) {
        this.code = success.getCode();
        this.message = success.getMessage();
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
