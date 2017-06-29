package com.yongle.goku.test.controller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by weinh on 2016/5/7.
 * 类上面的注解由非晶态setup方法使用，通过mockMvc开启单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/applicationContext-*.xml",
        "classpath:spring/springDispatcher-servlet.xml"})
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JUnitControllerBase {
    private static HandlerMapping handlerMapping;
    private static HandlerAdapter handlerAdapter;
    protected MockHttpServletRequest mockHttpServletRequest;
    protected MockHttpServletResponse mockHttpServletResponse;

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    /**
     * 初始化直接调用方法或者测试非页面结果
     */
    @Before
    public void setup() {
        //非页面结果使用
        this.mockMvc = webAppContextSetup(this.wac).build();

        //直接调用方法使用
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setCharacterEncoding("UTF-8");
        mockHttpServletResponse = new MockHttpServletResponse();
    }

    /**
     * 读取配置文件
     */
    @BeforeClass
    public static void setUp() {
        if (handlerMapping == null) {
            String[] configs = {"classpath:spring/applicationContext-*.xml",
                    "classpath:spring/springDispatcher-servlet.xml"};
            XmlWebApplicationContext context = new XmlWebApplicationContext();
            context.setConfigLocations(configs);
            MockServletContext msc = new MockServletContext();
            context.setServletContext(msc);
            context.refresh();
            msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
            handlerMapping = (HandlerMapping) context.getBean(DefaultAnnotationHandlerMapping.class);
            handlerAdapter = (HandlerAdapter) context.getBean(context.getBeanNamesForType(
                    AnnotationMethodHandlerAdapter.class)[0]);
        }

    }

    /**
     * 页面模型
     *
     * @param request
     * @param response
     * @return
     */
    protected ModelAndView executeController(HttpServletRequest request,
                                             HttpServletResponse response) {
        // 这里需要声明request的实际类型，否则会报错
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        ModelAndView model = null;
        try {
            HandlerExecutionChain chain = handlerMapping.getHandler(request);
            model = handlerAdapter.handle(request, response, chain.getHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
