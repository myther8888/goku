package com.yongle.goku.system.service;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;

/**
 * 类 名 称：UserService.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月13日
 */
public interface UserService extends BaseService<UserVO> {
    ResultVO login(UserVO userVO);

    String getAbc(String id);

    UserVO getUser(Long id);
}
