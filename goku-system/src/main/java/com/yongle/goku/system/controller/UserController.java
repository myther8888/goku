package com.yongle.goku.system.controller;

import com.yongle.goku.base.controller.BaseController;
import com.yongle.goku.model.vo.Page;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 类 名 称：UserController.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月13日
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody UserVO userVO) {
        return userService.login(userVO);
    }

    @GetMapping("")
    public ResultVO findAll(@RequestParam(name = "page_num", defaultValue = "1") int pageNum,
                            @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
                            HttpServletRequest request) {
        UserVO userVO = new UserVO();
        Page page = new Page(pageNum, pageSize);
        userVO.setPage(page);
        return userService.findAll(userVO, getCurrentUser(request));
    }
}
