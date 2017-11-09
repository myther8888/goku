package com.yongle.goku.system.aop;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.constant.ErrorEnum;
import com.yongle.goku.model.vo.ResultVO;
import com.yongle.goku.system.shiro.ShiroPermissionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author weinh
 */
@Aspect
@Component
public class AopUnifiedOperation {
    @Resource
    private ShiroPermissionFactory shiroPermissionFactory;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.yongle.goku.system.service.MenuService.save(..)) ||" +
            "execution(* com.yongle.goku.system.service.MenuService.update(..)) ||" +
            "execution(* com.yongle.goku.system.service.MenuService.disabled(..)) ||" +
            "execution(* com.yongle.goku.system.service.MenuService.enabled(..))")
    public void reloadFilterChainsPointcut() {
    }

    @AfterReturning(value = "reloadFilterChainsPointcut()", returning = "result")
    public void reloadFilterChains(JoinPoint joinPoint, ResultVO result) {
        JSONObject jsonObject = new JSONObject();
        if (ErrorEnum.SUCCESS.errorCode.equals(result.getErrorCode())) {
            logger.info("权限发生变更重新导入");
            shiroPermissionFactory.reloadFilterChains();
        }
    }
}
