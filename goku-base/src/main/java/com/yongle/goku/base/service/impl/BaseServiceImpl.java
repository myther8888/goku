package com.yongle.goku.base.service.impl;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.utils.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author weinh
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected RedisUtils redisUtils;

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
