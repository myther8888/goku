package com.yongle.goku.base.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author weinh
 */
public class AllInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ThreadLocal<Long> time = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        time.set(System.currentTimeMillis());
        Enumeration enumeration = request.getParameterNames();
        StringBuilder stringBuilder = new StringBuilder();
        while (enumeration.hasMoreElements()) {
            String param = enumeration.nextElement().toString();
            stringBuilder.append(param).append("=").append(request.getParameter(param)).append("，");
        }
        log.info("请求路径：{}，请求参数：{}", request.getServletPath(), stringBuilder.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("{}接口请求时长time：{}ms", request.getServletPath(), System.currentTimeMillis() - time.get());
        time.remove();
    }
}
