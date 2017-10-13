package com.yongle.goku.system.controller;

import com.yongle.goku.base.controller.BaseController;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类 名 称：UserController.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月13日
 */
@RestController("/users")
public class UserController extends BaseController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody UserVO userVO) {
        return userService.login(userVO);
    }
}
