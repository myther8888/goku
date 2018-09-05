package com.yongle.goku.base.service;

import com.yongle.goku.model.vo.Page;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;

import java.util.List;

/**
 * 类 名 称：BaseService.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月13日
 */
public interface BaseService<T> {
    /**
     * 保存对象
     *
     * @param t           对象
     * @param currentUser 操作者
     * @return 返回结果
     */
    ResultVO save(T t, UserVO currentUser);

    /**
     * 修改对象
     *
     * @param id          修改id
     * @param t           对象
     * @param currentUser 操作者
     * @return 返回结果
     */
    ResultVO update(Long id, T t, UserVO currentUser);

    /**
     * 获取一个对象
     *
     * @param id          对象id
     * @param currentUser 操作者
     * @return 返回结果
     */
    ResultVO<T> findOne(Long id, UserVO currentUser);

    /**
     * 分页获取对象
     *
     * @param t           查询条件
     * @param page        分页对象
     * @param currentUser 操作者
     * @return 返回结果
     */
    ResultVO<Page<T>> findByPage(T t, Page page, UserVO currentUser);

    /**
     * 获取所有有效对象
     *
     * @param t           查询条件
     * @param currentUser 操作者
     * @return 返回结果
     */
    ResultVO<List<T>> findAll(T t, UserVO currentUser);

    /**
     * 禁用对象
     *
     * @param id          对象id
     * @param currentUser 操作者
     * @return 返回结果
     */
    ResultVO disabled(Long id, UserVO currentUser);

    /**
     * 启用对象
     *
     * @param id          对象id
     * @param currentUser 操作者
     * @return 返回结果
     */
    ResultVO enabled(Long id, UserVO currentUser);
}
