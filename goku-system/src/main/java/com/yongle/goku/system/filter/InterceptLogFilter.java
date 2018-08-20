package com.yongle.goku.system.filter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>类 名 称：InterceptLogFilter.java
 * <p>功能说明：
 * <p>开发时间：2018年08月20日
 *
 * @author ：weinh
 */
public class InterceptLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(InterceptLogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long t1 = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        BodyReaderHttpServletResponseWrapper responseWrapper = new BodyReaderHttpServletResponseWrapper(response);
        String json = HttpHelper.getBodyString(requestWrapper);
        logger.info("开始调用接口：{},{}，调用接口body参数：{}，url参数：{}",
                request.getMethod(), request.getRequestURI(), json, JSONObject.toJSONString(request.getParameterMap()));
        filterChain.doFilter(requestWrapper, servletResponse);
        logger.info("调用接口结束：{}，耗时：{}ms，{}", request.getRequestURI(), System.currentTimeMillis() - t1);
    }

    @Override
    public void destroy() {

    }
}
