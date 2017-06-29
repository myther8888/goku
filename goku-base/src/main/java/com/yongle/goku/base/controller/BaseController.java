package com.yongle.goku.base.controller;

import com.yongle.goku.utils.ConfigUtils;
import com.yongle.goku.utils.ReturnCode;
import com.yongle.goku.vo.ReturnBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {
    private final static Logger log = LoggerFactory.getLogger(BaseController.class);
    protected ReturnBean returnBean;

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ReturnBean exception(Exception e) {
        returnBean = ConfigUtils.generateReturnBean(ReturnCode.ERROR);
        if (ConfigUtils.getProperty("debug").equals("true")) {
            returnBean.setDescription(e.getLocalizedMessage());
        }
        log.error(e.getMessage(), e);
        return returnBean;
    }

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }
}
