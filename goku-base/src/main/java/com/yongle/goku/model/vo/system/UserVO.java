package com.yongle.goku.model.vo.system;

import com.yongle.goku.model.system.SysUser;
import com.yongle.goku.model.vo.Page;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * 类 名 称：UserVO.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月13日
 */
public class UserVO extends SysUser {
    private static final String[] IGNORE_PROPERTIES = {"password", "id"};
    private String token;
    private Page page;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public SysUser convert2PO() {
        SysUser user = null;
        try {
            user = new SysUser();
            BeanUtils.copyProperties(this, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void convert2VO(@NotNull SysUser user) {
        try {
            if (user != null) {
                BeanUtils.copyProperties(user, this, IGNORE_PROPERTIES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
