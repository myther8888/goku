package com.yongle.goku.model.vo;

/**
 * 类 名 称：Paging.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年09月20日
 */
public class Paging {
    private long total;
    private int start;
    private int limit;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
