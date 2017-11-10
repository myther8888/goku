package com.yongle.goku.utils.shiro.cache;

import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.utils.SpringUtils;
import com.yongle.goku.utils.redis.RedisUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author weinh
 */
public class RedisCache<K, V> implements Cache<K, V> {
    private static RedisUtils redisUtils;

    public RedisUtils getRedisUtils() {
        if (redisUtils == null) {
            redisUtils = SpringUtils.getBean(RedisUtils.class);
        }
        return redisUtils;
    }

    private String getKey(K k) {
        if (k instanceof PrincipalCollection) {
            //这种情况是权限检查
            Long id = ((UserVO) ((PrincipalCollection) k).getPrimaryPrincipal()).getId();
            return RedisUtils.RedisKey.getUserAuthorizationKey(id);
        }
        if (k instanceof String) {
            //这种情况是登录的token
            return RedisUtils.RedisKey.getUserAuthenticationKey((String) k);
        }
        if (k instanceof UserVO) {
            //这种情况是登出的用户实例
            return RedisUtils.RedisKey.getUserAuthenticationKey(((UserVO) k).getToken());
        }
        return null;
    }

    @Override
    public V get(K k) throws CacheException {
        V v = (V) getRedisUtils().get(getKey(k));
        if (v != null) {
            getRedisUtils().expire(getKey(k), 1L, TimeUnit.DAYS);
        }
        return v;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        getRedisUtils().set(getKey(k), v, 1L, TimeUnit.DAYS);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        getRedisUtils().del(getKey(k));
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }


}
