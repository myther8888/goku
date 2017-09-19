package com.yongle.goku.utils.datasource;

/**
 * 类 名 称：DynamicDataSourceHolder.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年09月19日
 */
public class DynamicDataSourceHolder {
    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<>();

    private DynamicDataSourceHolder() {
        //
    }

    public static void putDataSource(DynamicDataSourceGlobal dataSource) {
        holder.set(dataSource);
    }

    public static DynamicDataSourceGlobal getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }
}
