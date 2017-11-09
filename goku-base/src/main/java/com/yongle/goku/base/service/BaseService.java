package com.yongle.goku.base.service;

import com.yongle.goku.model.vo.Page;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;

/**
 * 类 名 称：BaseService.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月13日
 */
public interface BaseService<T> {
    ResultVO save(T t, UserVO currentUser);

    ResultVO update(Long id, T t, UserVO currentUser);

    ResultVO findOne(Long id, UserVO currentUser);

    ResultVO findList(T t, Page page, UserVO currentUser);

    ResultVO findAll(T t, UserVO currentUser);

    ResultVO disabled(Long id, UserVO currentUser);

    ResultVO enabled(Long id, UserVO currentUser);
}
