package com.yongle.goku.system.shiro.realm;

import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.system.service.MenuService;
import com.yongle.goku.utils.EntityUtils;
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
 * @author weinh
 */
@Component
public class TokenRealm extends AuthorizingRealm {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private MenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long id = ((UserVO) principals.getPrimaryPrincipal()).getId();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(menuService.getPermission(id));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tokenStr = new String((char[]) token.getCredentials());
        Map<Object, Object> map = redisUtils.hGetAll(RedisUtils.RedisKey.getTokenKey(tokenStr));
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        UserVO userVO = EntityUtils.hashToObject(map, UserVO.class);
        return new SimpleAuthenticationInfo(userVO, tokenStr, getName());
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        Long id = ((UserVO) principals.getPrimaryPrincipal()).getId();
        return RedisUtils.RedisKey.getUserAuthorizationKey(id);
    }
}
