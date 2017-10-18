package com.yongle.goku.system.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weinh
 */
@Component
public class PermissionAuthFilter extends RequestContextFilter {
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request,
//                                      ServletResponse response,
//                                      Object mappedValue) throws Exception {
//        //先判断带参数的权限判断
//        Subject subject = getSubject(request, response);
//        if (null != mappedValue) {
//            String[] arra = (String[]) mappedValue;
//            for (String permission : arra) {
//                if (subject.isPermitted(permission)) {
//                    return Boolean.TRUE;
//                }
//            }
//        }
//        //取到请求的uri ，进行权限判断
//        String uri = ((ShiroHttpServletRequest) request).getServletPath();
//        if (subject.isPermitted(uri)) {
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }

//    @Override
//    protected boolean onAccessDenied(ServletRequest request,
//                                     ServletResponse response) throws Exception {
//        return Boolean.FALSE;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //先判断带参数的权限判断
        Subject subject = SecurityUtils.getSubject();
        //取到请求的uri ，进行权限判断
        String uri = request.getServletPath();
        if (subject.isPermitted(uri)) {
            super.doFilterInternal(request, response, filterChain);
        }
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        //先判断带参数的权限判断
//        Subject subject = SecurityUtils.getSubject();
//        //取到请求的uri ，进行权限判断
//        String uri = ((ShiroHttpServletRequest) request).getServletPath();
//        if (subject.isPermitted(uri)) {
//            chain.doFilter(request, response);
//        }
//    }
}
