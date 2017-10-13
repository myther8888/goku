package com.yongle.goku.system.service.impl;

import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 类 名 称：UserServiceImpl.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月13日
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResultVO save(UserVO userVO, UserVO currentUser) {
        return null;
    }

    @Override
    public ResultVO update(Long id, UserVO userVO, UserVO currentUser) {
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
    public ResultVO findAll(UserVO userVO, UserVO currentUser) {
        return null;
    }

    @Override
    public ResultVO recover(Long id, UserVO currentUser) {
        return null;
    }

    @Override
    public ResultVO login(UserVO userVO) {
        return null;
    }
}
