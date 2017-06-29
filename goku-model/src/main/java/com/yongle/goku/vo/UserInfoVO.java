package com.yongle.goku.vo;


import com.yongle.goku.model.UserInfo;
import org.springframework.beans.BeanUtils;

/**
 * Created by weinh on 2016/5/8.
 */
public class UserInfoVO extends UserInfo {
    private static final String[] IGNOREPROPERTIES = {"password", "id", "status"};

    private String password2;
    private String oldPassword;
    private String verifyCode;
    private String token;
    private String verifyCodeUuid;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVerifyCodeUuid() {
        return verifyCodeUuid;
    }

    public void setVerifyCodeUuid(String verifyCodeUuid) {
        this.verifyCodeUuid = verifyCodeUuid;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public UserInfo convert2Pojo() {
        UserInfo userInfo = null;
        try {
            userInfo = new UserInfo();
            BeanUtils.copyProperties(this, userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public UserInfoVO convert2Vo(UserInfo userInfo) {
        UserInfoVO vo = null;
        try {
            if (userInfo != null) {
                vo = new UserInfoVO();
                BeanUtils.copyProperties(userInfo, vo, IGNOREPROPERTIES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
}
