package com.yongle.goku.demo.controller;

import com.yongle.goku.base.controller.BaseController;
import com.yongle.goku.demo.service.DemoService;
import com.yongle.goku.model.demo.Demo;
import com.yongle.goku.model.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 类 名 称：DemoController.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年09月20日
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {
    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/test/valid", method = RequestMethod.POST)
    public ResultVO testValid(@Valid @RequestBody Demo demo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.validatorParam(bindingResult);
        }
        ResultVO<Demo> vo = new ResultVO<>();
        vo.setData(demo);
        return vo;
    }

    @RequestMapping(value = "/test/paging", method = RequestMethod.GET)
    public ResultVO testPaging() {
        return demoService.getDemos();
    }
}
