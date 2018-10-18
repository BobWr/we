package com.baojk.we.base;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/10
 */
public enum SuccessConstants {

    SUCCESS("0", "操作成功");

    private String code;

    private String message;

    SuccessConstants(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
