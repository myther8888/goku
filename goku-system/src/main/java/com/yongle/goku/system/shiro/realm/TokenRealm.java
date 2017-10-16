package com.yongle.goku.system.shiro.realm;

import com.yongle.goku.system.service.RoleUserService;
import com.yongle.goku.utils.redis.RedisUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 类 名 称：TokenRealm.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年10月16日
 */
@Component
public class TokenRealm extends AuthorizingRealm {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private RoleUserService roleUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long id = (Long) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleUserService.findRoles(id));
//        authorizationInfo.setStringPermissions(userService.findPermissions(id));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tokenStr = new String((char[]) token.getCredentials());
        Map<Object, Object> map = redisUtils.hGetAll(RedisUtils.RedisKey.getTokenKey(tokenStr));
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        return new SimpleAuthenticationInfo(map.get("id"), tokenStr, getName());
    }
}
