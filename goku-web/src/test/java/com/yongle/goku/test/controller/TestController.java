package com.yongle.goku.test.controller;

import com.yongle.goku.user.controller.UserInfoController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by weinh on 2016/5/7.
 */
public class TestController extends JUnitControllerBase {
    @Autowired
    private UserInfoController userInfoController;

    /**
     * 测试页面使用
     */
    @Test
    public void testPage() {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//        request.setServletPath("/demo/index.html");
//        request.setMethod("GET");
//        final ModelAndView mav = executeController(request, response);
//        System.out.println(mav);
    }

    /**
     * 非页面结果使用
     *
     * @throws Exception
     */
    @Test
    public void testUrl() throws Exception {
        mockMvc.perform((post("/userInfo/login")
                .param("nickName", "abc")
                .param("password", "abc")
                .param("password2", "abc")
                .param("name", "abc")
                .param("email", "abc@cc.com"))).andDo(print());
        mockMvc.perform((post("/userInfo/check/abc"))).andDo(print());
    }

    /**
     * 直接测试方法，可以配合非页面结果测试，尽量不要使用该方法（抛出异常拦截不到会报错）
     */
    @Test
    public void testController() {
//        mockHttpServletRequest.setParameter("userName", "admin");
//        mockHttpServletRequest.setParameter("password", "2");
//        UserInfo userInfo = new UserInfo();
//        userInfo.setNickName("abc");
//        userInfo.setPassword("abc");
//        userInfo.setEmail("abc@cc.com");
//        ReturnBean returnBean = userInfoController.login(userInfo);
//        System.out.println(JSONObject.toJSONString(returnBean));
    }
}
