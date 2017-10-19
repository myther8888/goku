package com.yongle.goku.system.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.vo.ResultVO;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author weinh
 */
@Component
public class RoleAuthFilter extends RolesAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        ResultVO resultVO = new ResultVO(ErrorEnum.ERROR_PERMISSION);
        response.setContentType("application/json");
        response.getWriter().println(JSONObject.toJSONString(resultVO));
        return false;
    }
}
