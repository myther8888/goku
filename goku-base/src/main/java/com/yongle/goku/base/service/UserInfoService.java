package com.yongle.goku.base.service;


import com.yongle.goku.vo.ReturnBean;
import com.yongle.goku.vo.UserInfoVO;

/**
 * Created by weinh on 2016/5/7.
 */
public interface UserInfoService {
    /**
     * 注册
     *
     * @param vo 用户视图对象
     * @return 返回结果集
     */
    ReturnBean register(UserInfoVO vo);

    /**
     * 登录
     *
     * @param vo 用户视图对象
     * @return 返回结果集
     */
    ReturnBean login(UserInfoVO vo);

    /**
     * 完善用户信息
     *
     * @param userInfoVo 用户视图对象
     * @return 返回结果集
     */
    ReturnBean perfectInformation(UserInfoVO userInfoVo);

    ReturnBean bindConfirm(String contact, String verifyCode);

    ReturnBean requestBindPhone(UserInfoVO userInfoVo);

    ReturnBean requestBindEmail(UserInfoVO userInfoVo);
}
