package com.yongle.goku.system.controller;

import com.yongle.goku.base.controller.BaseController;
import com.yongle.goku.constant.Constants;
import com.yongle.goku.model.vo.Page;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author weinh
 */
@RestController
@RequestMapping("/system/users")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody UserVO userVO) {
        return userService.login(userVO);
    }

    @GetMapping("/logout")
    public ResultVO logout(HttpServletRequest request) {
        return userService.logout(getCurrentUser(request));
    }
}
