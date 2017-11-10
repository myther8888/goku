package com.yongle.goku.system.service;

import com.yongle.goku.base.service.BaseService;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;

/**
 * @author weinh
 */
public interface UserService extends BaseService<UserVO> {
    /**
     * 登录接口
     *
     * @param userVO 登录对象
     * @return 返回结果
     */
    ResultVO login(UserVO userVO);

    /**
     * 登出接口
     *
     * @param userVO 登出对象
     * @return 返回结果
     */
    ResultVO logout(UserVO userVO);
}
