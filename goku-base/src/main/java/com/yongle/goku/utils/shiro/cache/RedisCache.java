package com.yongle.goku.utils.shiro.cache;

import com.yongle.goku.model.vo.system.UserVO;
import com.yongle.goku.utils.EntityUtils;
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
import java.util.Map;
import java.util.Set;

/**
 * @author weinh
 */
public class RedisCache<K, V> implements Cache<K, V> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static RedisUtils redisUtils;
    private String prefix;

    public RedisCache(String prefix) {
        this.prefix = prefix;
    }

    public RedisUtils getRedisUtils() {
        if (redisUtils == null) {
            redisUtils = SpringUtils.getBean(RedisUtils.class);
        }
        return redisUtils;
    }

    private String getKey(K k) {
        if (k instanceof PrincipalCollection) {
            Long id = ((UserVO) ((PrincipalCollection) k).getPrimaryPrincipal()).getId();
            return RedisUtils.RedisKey.getUserAuthorizationKey(id);
        }
        if (k instanceof String) {
            return RedisUtils.RedisKey.getUserAuthenticationKey((String) k);
        }
        if (k instanceof UserVO) {
            return RedisUtils.RedisKey.getUserAuthenticationKey(((UserVO) k).getToken());
        }
        return null;
    }

    @Override
    public V get(K k) throws CacheException {
        Map<Object, Object> map = getRedisUtils().hGetAll(getKey(k));
        return EntityUtils.hashToObject(map, getType(k));
    }

    private Class getType(K k) {
        if (k instanceof PrincipalCollection) {
            return SimpleAuthorizationInfo.class;
        }
        if (k instanceof String) {
            return SimpleAuthenticationInfo.class;
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        getRedisUtils().hMSet(getKey(k), EntityUtils.objectToHash(v));
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
