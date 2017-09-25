package com.yongle.goku.test.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * 类 名 称：TestShiro.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年09月22日
 */
public class TestShiro {
    @Test
    public void testHelloWorld() {
        testLogin("classpath:shiro/test-hello-world.ini", "user1", "password1");
    }

    @Test
    public void testCustomRealm() {
        testLogin("classpath:shiro/shiro-realm.ini", "zhang", "123");
    }

    @Test
    public void testJdbcRealm() {
        testLogin("classpath:shiro/shiro-jdbc-realm.ini", "zhang", "123");
    }

    @Test
    public void testStrategyRealm() {
        testLogin("classpath:shiro/shiro-strategy.ini", "zhang", "123");
    }

    @Test
    public void testRole() {
        Subject subject = testLogin("classpath:shiro/shiro-role.ini",
                "user1", "password1");
        Assert.assertEquals(true, subject.hasRole("role2"));
    }

    @Test
    public void testRoleShow() {
        Subject subject = testLogin("classpath:shiro/shiro-role-show.ini",
                "user1", "password1");
        Assert.assertEquals(true, subject.isPermitted("user:create"));
    }

    public Subject testLogin(String path, String username, String password) {
        Factory<SecurityManager> f = new IniSecurityManagerFactory(path);
        SecurityManager s = f.getInstance();
        SecurityUtils.setSecurityManager(s);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        return subject;
    }
}
