package com.yongle.goku.system.controller;

import com.yongle.goku.base.controller.BaseController;
import com.yongle.goku.constant.Constants;
import com.yongle.goku.model.vo.Page;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.MenuVO;
import com.yongle.goku.system.service.MenuService;
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
@RequestMapping("/system/menus")
public class MenuController extends BaseController {

    @Resource
    private MenuService menuService;

    @PostMapping
    public ResultVO save(@Valid @RequestBody MenuVO menuVO,
                         HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validatorParam(bindingResult);
        }
        return menuService.save(menuVO, getCurrentUser(request));
    }

    @PutMapping("/{id}")
    public ResultVO update(@PathVariable(name = "id") Long id,
                           @Valid @RequestBody MenuVO menuVO,
                           BindingResult bindingResult,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return validatorParam(bindingResult);
        }
        return menuService.update(id, menuVO, getCurrentUser(request));
    }

    @GetMapping("/{id}")
    public ResultVO findOne(@PathVariable(name = "id") Long id,
                            HttpServletRequest request) {
        return menuService.findOne(id, getCurrentUser(request));
    }

    @GetMapping()
    public ResultVO findList(@RequestParam(name = "page_num", defaultValue = Constants.DEFAULT_PAGE_NUM) int pageNum,
                             @RequestParam(name = "page_size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
                             HttpServletRequest request) {
        MenuVO menuVO = new MenuVO();
        Page page = new Page(pageNum, pageSize);
        return menuService.findList(menuVO, page, getCurrentUser(request));
    }

    @DeleteMapping("/{id}")
    public ResultVO disabled(@PathVariable(name = "id") Long id,
                             HttpServletRequest request) {
        return menuService.disabled(id, getCurrentUser(request));
    }


    @PostMapping("/{id}")
    public ResultVO enabled(@PathVariable(name = "id") Long id,
                            HttpServletRequest request) {
        return menuService.enabled(id, getCurrentUser(request));
    }

}
