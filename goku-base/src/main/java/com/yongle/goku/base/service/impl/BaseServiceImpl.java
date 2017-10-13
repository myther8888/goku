package com.yongle.goku.base.service.impl;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;

/**
 * 类 名 称：BaseServiceImpl.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月13日
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public ResultVO save(T t, UserVO currentUser) {
        return null;
    }

    @Override
    public ResultVO update(Long id, T t, UserVO currentUser) {
        return null;
    }

    @Override
    public ResultVO delete(Long id, UserVO currentUser) {
        return null;
    }

    @Override
    public ResultVO findOne(Long id, UserVO currentUser) {
        return null;
    }

    @Override
    public ResultVO findAll(T t, UserVO currentUser) {
        return null;
    }

    @Override
    public ResultVO recover(Long id, UserVO currentUser) {
        return null;
    }
}
