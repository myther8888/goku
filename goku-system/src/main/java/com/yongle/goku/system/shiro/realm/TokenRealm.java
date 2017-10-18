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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author weinh
 */
@Component
public class TokenRealm extends AuthorizingRealm {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private RoleUserService roleUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer id = (Integer) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleUserService.findRoles(id.longValue()));
        Set<String> p = new HashSet<>();
        p.add("/users");
        p.add("/users/user/*");
        authorizationInfo.setStringPermissions(p);
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
