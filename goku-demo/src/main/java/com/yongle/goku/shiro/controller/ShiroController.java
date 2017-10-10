package com.yongle.goku.shiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类 名 称：ShiroController.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年09月29日
 */
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @RequestMapping("/login")
    public String login(String[] param1, String param2) {
        return "hello" + param1[0] + param1[1] + param2;
    }
}
