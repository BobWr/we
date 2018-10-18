package com.baojk.we.base;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/17
 */
public interface Error {
    /**
     * 返回的HTTP状态码
     *
     * @return HTTP Status Code
     */
    int getHttpCode();

    /**
     * 返回内部错误的状态码
     *
     * @return 内部错误状态码
     */
    String getCode();

    /**
     * 返回详细的错误说明
     *
     * @return 错误说明
     */
    String getMessage();
}
