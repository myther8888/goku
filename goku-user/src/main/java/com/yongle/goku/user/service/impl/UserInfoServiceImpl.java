package com.yongle.goku.user.service.impl;

import com.yongle.goku.base.service.UserInfoService;
import com.yongle.goku.vo.ReturnBean;
import com.yongle.goku.vo.UserInfoVO;
import org.springframework.stereotype.Service;

/**
 * Created by weinh on 2017/2/28.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public ReturnBean register(UserInfoVO vo) {
        return null;
    }

    @Override
    public ReturnBean login(UserInfoVO vo) {
        return null;
    }

    @Override
    public ReturnBean perfectInformation(UserInfoVO userInfoVo) {
        return null;
    }

    @Override
    public ReturnBean bindConfirm(String contact, String verifyCode) {
        return null;
    }

    @Override
    public ReturnBean requestBindPhone(UserInfoVO userInfoVo) {
        return null;
    }

    @Override
    public ReturnBean requestBindEmail(UserInfoVO userInfoVo) {
        return null;
    }
}
