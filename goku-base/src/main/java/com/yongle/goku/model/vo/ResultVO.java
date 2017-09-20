package com.yongle.goku.model.vo;

import com.yongle.goku.constant.ErrorEnum;

/**
 * Created by weinh on 2016/5/5.
 */
public class ResultVO<T> {
    private Integer errorCode;
    private String errorInfo;
    private String description;
    private T data;
    private Paging paging;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
