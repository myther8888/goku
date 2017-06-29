package com.yongle.goku.user.controller;

import com.yongle.goku.base.controller.BaseController;
import com.yongle.goku.base.interceptor.Signature;
import com.yongle.goku.base.service.UserInfoService;
import com.yongle.goku.vo.ReturnBean;
import com.yongle.goku.vo.UserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by weinh on 2016/5/7.
 */
@Controller
@RequestMapping("/api/user_center")
public class UserInfoController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    @Signature
    public ReturnBean register(@RequestBody UserInfoVO userInfoVo) {
        returnBean = userInfoService.register(userInfoVo);
        return returnBean;
    }
//
//    @RequestMapping(value = "/user_info/complete", method = RequestMethod.PUT)
//    @ResponseBody
//    public ReturnBean perfectInformationWithToken(@RequestBody ParamVo<JSONObject> paramVo, HttpServletRequest request) {
//        returnBean = userInfoService.perfectInformation(generateUserInfoVo(paramVo.getParam(), request));
//        return returnBean;
//    }
//
//    @RequestMapping(value = "/bind/email/req", method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnBean requestBindEmailWithToken(@RequestBody ParamVo<JSONObject> paramVo, HttpServletRequest request) {
//        returnBean = userInfoService.requestBindEmail(generateUserInfoVo(paramVo.getParam(), request));
//        return returnBean;
//    }
//
//    @RequestMapping(value = "/bind/phone/req", method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnBean requestBindPhoneWithToken(@RequestBody ParamVo<JSONObject> paramVo, HttpServletRequest request) {
//        returnBean = userInfoService.requestBindPhone(generateUserInfoVo(paramVo.getParam(), request));
//        return returnBean;
//    }
//
//    @RequestMapping(value = "/bind/confirm", method = RequestMethod.GET)
//    @ResponseBody
//    public ReturnBean bindConfirm(@RequestParam(value = "contact") String contact,
//                                  @RequestParam(value = "verify_code") String verifyCode) {
//        returnBean = userInfoService.bindConfirm(contact, verifyCode);
//        return returnBean;
//    }
//
//    private UserInfoVO generateUserInfoVo(JSONObject jsonObject, HttpServletRequest request) {
//        UserInfoVO userInfoVo = JSONObject.toJavaObject(jsonObject, UserInfoVO.class);
//        Long userId = (Long) request.getAttribute(Constants.USERID_ATTRIBUTE);
//        userInfoVo.setId(userId);
//        return userInfoVo;
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnBean login(@RequestBody ParamVo<JSONObject> paramVo) {
//        UserInfoVO userInfoVo = JSONObject.toJavaObject(paramVo.getParam(), UserInfoVO.class);
//        returnBean = userInfoService.login(userInfoVo);
//        return returnBean;
//    }

    @RequestMapping(value = "/verify_code/images/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getImageVerifyCode(@PathVariable("uuid") String uuid,
                                   @RequestParam(value = "width", required = false) Integer width,
                                   @RequestParam(value = "height", required = false) Integer height,
                                   HttpServletResponse response) {
        BufferedImage img = null;
        //userInfoService.getImageVerifyCode(uuid,
        //      SysDict.VerifyCodeType.login, width, height);
        try {
            if (img != null) {
                ImageIO.write(img, "png", response.getOutputStream());
            } else {
                log.error("验证码图片为空");
            }
        } catch (IOException e) {
            log.error("验证码图片生成失败", e);
        }
    }

//    @RequestMapping(value = "/user_info/contact", method = RequestMethod.GET)
//    @ResponseBody
//    public ReturnBean checkContact(@RequestParam(value = "contact") String contact) {
//        returnBean = ConfigUtils.generateReturnBean(userInfoService.checkContact(contact));
//        return returnBean;
//    }
}
