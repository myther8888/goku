package com.yongle.goku.model.vo;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author weinh
 */
public class Page<T> {
    private long total;
    private int pageNum;
    private int pageSize;
    private List<T> list;

    public Page() {

    }

    public Page(PageInfo<T> pageInfo) {
        this.total = pageInfo.getTotal();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.list = pageInfo.getList();
    }

    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
