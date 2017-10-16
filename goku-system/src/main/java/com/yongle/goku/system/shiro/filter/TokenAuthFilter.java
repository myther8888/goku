package com.yongle.goku.system.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 类 名 称：TokenAuthFilter.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月16日
 */
public class TokenAuthFilter extends AccessControlFilter {
    private List<String> excludeUrl;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ShiroHttpServletRequest shiroHttpServletRequest = (ShiroHttpServletRequest) request;
        if (excludeUrl.contains(shiroHttpServletRequest.getRequestURI().replace(
                shiroHttpServletRequest.getContextPath(), ""))) {
            return true;
        }
        String tokenStr = shiroHttpServletRequest.getHeader("token");
        if (StringUtils.isBlank(tokenStr)) {
            onAuthFail(response, ErrorEnum.ERROR_PARAM);
            return false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken("", tokenStr, null);
        try {
            getSubject(request, response).login(token);
        } catch (Exception e) {
            onAuthFail(response, ErrorEnum.TOKEN_ERROR);
            return false;
        }
        return true;
    }

    private void onAuthFail(ServletResponse response, ErrorEnum errorEnum) throws IOException {
        ResultVO resultVO = new ResultVO(errorEnum);
        response.setContentType("application/json");
        response.getWriter().println(JSONObject.toJSONString(resultVO));
    }

    public List<String> getExcludeUrl() {
        return excludeUrl;
    }

    public void setExcludeUrl(List<String> excludeUrl) {
        this.excludeUrl = excludeUrl;
    }
}
