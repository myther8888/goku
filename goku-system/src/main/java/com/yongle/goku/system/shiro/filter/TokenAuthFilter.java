package com.yongle.goku.system.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.constant.Constants;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.utils.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author weinh
 */
@Component
public class TokenAuthFilter extends AccessControlFilter {

    @Resource
    private RedisUtils redisUtils;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ShiroHttpServletRequest shiroHttpServletRequest = (ShiroHttpServletRequest) request;
        String tokenStr = shiroHttpServletRequest.getHeader("token");
        if (StringUtils.isBlank(tokenStr)) {
            onAuthFail(response, ErrorEnum.ERROR_PARAM);
            return false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(tokenStr, tokenStr, null);
        try {
            getSubject(request, response).login(token);
            UserVO userVO = (UserVO) getSubject(request, response).getPrincipal();
            request.setAttribute(Constants.CURRENT_USER, userVO);
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
}
