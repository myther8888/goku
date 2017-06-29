package com.yongle.goku.base.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.base.service.UserInfoService;
import com.yongle.goku.utils.*;
import com.yongle.goku.vo.ReturnBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Created by weinh on 2016/5/25.
 */
@Component
public class TokenInterceptor {

    private static Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserInfoService userInfoService;

    public ReturnBean checkToken(ProceedingJoinPoint pjp) throws Exception {
        ReturnBean returnBean = ConfigUtils.generateReturnBean(ReturnCode.TOKEN_ERROR);
        try {
            Object[] args = pjp.getArgs();
            HttpServletRequest request = null;
            JSONObject jsonObject = null;
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    request = (HttpServletRequest) arg;
                }
//                if (arg instanceof ParamVo) {
//                    jsonObject = (JSONObject) JSONObject.toJSON(arg);
//                }
            }
            if (request != null && jsonObject != null && jsonObject.containsKey(Constants.TOKEN)) {
                String userId = redisUtils.get(RedisUtils.RedisKey.getTokenKey(jsonObject.getString(Constants.TOKEN)));
                if (TextUtils.isNumeric(userId)) {
                    redisUtils.expire(RedisUtils.RedisKey.getTokenKey(jsonObject.getString(Constants.TOKEN)),
                            Constants.TOKEN_EXPIRE_DAY, TimeUnit.DAYS);//每次检查token成功，延长token有效期7天
                    request.setAttribute(Constants.USERID_ATTRIBUTE, Long.parseLong(userId));
                    returnBean = (ReturnBean) pjp.proceed();
                }
            }
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            returnBean.setDescription(throwable.getLocalizedMessage());
        }
        return returnBean;
    }
}
