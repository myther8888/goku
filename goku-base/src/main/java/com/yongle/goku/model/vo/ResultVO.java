package com.yongle.goku.model.vo;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.constant.ErrorEnum;

/**
 * @author weinh
 */
public class ResultVO<T> {
    private Integer errorCode;
    private String errorInfo;
    private T data;

    public ResultVO() {
        this.errorCode = ErrorEnum.SUCCESS.errorCode;
        this.errorInfo = ErrorEnum.SUCCESS.errorInfo;
    }

    public ResultVO(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.errorCode;
        this.errorInfo = errorEnum.errorInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
