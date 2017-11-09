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

    @GetMapping()
    public ResultVO findList(@RequestParam(name = "page_num", defaultValue = Constants.DEFAULT_PAGE_NUM) int pageNum,
                             @RequestParam(name = "page_size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
                             HttpServletRequest request) {
        UserVO userVO = new UserVO();
        Page page = new Page(pageNum, pageSize);
        return userService.findList(userVO, page, getCurrentUser(request));
    }

    @GetMapping("/user/{id}")
    public ResultVO findOne(@PathVariable(name = "id") Long id,
                            HttpServletRequest request) {
        return userService.findOne(id, getCurrentUser(request));
    }

    @PutMapping("/user/{id}")
    public ResultVO update(@PathVariable(name = "id") Long id,
                           @Valid @RequestBody UserVO userVO,
                           BindingResult bindingResult,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return validatorParam(bindingResult);
        }
        return userService.update(id, userVO, getCurrentUser(request));
    }

    @DeleteMapping("/user/{id}")
    public ResultVO disabled(@PathVariable(name = "id") Long id,
                             HttpServletRequest request) {
        return userService.disabled(id, getCurrentUser(request));
    }

    @PostMapping("/user")
    public ResultVO save(@Valid @RequestBody UserVO userVO,
                         HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validatorParam(bindingResult);
        }
        return userService.save(userVO, getCurrentUser(request));
    }
}
