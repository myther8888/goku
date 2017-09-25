package com.yongle.goku.demo.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 类 名 称：MyRealm3.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年09月22日
 */
public class MyRealm3 implements Realm {

    @Override
    public String getName() {
        return "myRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) (token.getCredentials()));
        if (!"zhang@163.com".equals(username)) {
            throw new UnknownAccountException("账户异常");
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码异常");
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
