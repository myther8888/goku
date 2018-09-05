package com.yongle.goku.system.filter;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * <p>类 名 称：TestFilter.java
 * <p>功能说明：
 * <p>开发时间：2018年08月20日
 *
 * @author ：weinh
 */
public class TestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (StringUtils.equals(servletRequest.getParameter("test"), "true")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            servletResponse.getWriter().print(JSONObject.toJSONString(new ResultVO<>(ErrorEnum.ERROR)));
        }
    }

    @Override
    public void destroy() {

    }
}
