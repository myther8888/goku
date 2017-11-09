package com.yongle.goku.system.service;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;

/**
 * @author weinh
 */
public interface UserService extends BaseService<UserVO> {
    ResultVO login(UserVO userVO);

    ResultVO logout(UserVO userVO);
}
