package com.yongle.goku.constant;

/**
 * Created by weinh on 2017/2/9.
 */
public enum ErrorEnum {
    //系统相关
    SUCCESS(0, "成功"),
    ERROR(-1, "未知错误"),
    ERROR_LOGIN(-2, "用户名或者密码错误"),
    SIGNATURE_ERROR(-10, "签名错误"),
    TIMESTAMP_ERROR(-11, "时间戳错误"),
    TIMESTAMP_EMPTY(-12, "时间戳不能为空"),
    ERROR_PARAM(-13, "参数错误"),
    ERROR_PERMISSION(-14, "权限不足"),
    LOGIN_ERROR(-1000, "登录失败"),
    TOKEN_ERROR(-1001, "token失效");
    public final Integer errorCode;
    public final String errorInfo;

    ErrorEnum(Integer errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }
}
