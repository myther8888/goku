package com.yongle.goku.utils.shiro.cache;

import com.yongle.goku.utils.EntityUtils;
import com.yongle.goku.utils.redis.RedisUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author weinh
 */
@Component
public class RedisCache implements Cache<String, Object> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RedisUtils redisUtils;

    @Override
    public Object get(String s) throws CacheException {
        Map<Object, Object> map = redisUtils.hGetAll(s);

        AuthorizationInfo authorizationInfo = null;
        try {
            authorizationInfo = EntityUtils.hashToObject(map, SimpleAuthorizationInfo.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return authorizationInfo;
    }

    @Override
    public Object put(String s, Object o) throws CacheException {
        redisUtils.hMSet(s, EntityUtils.objectToHash(o));
        return o;
    }

    @Override
    public Object remove(String s) throws CacheException {
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
    public Set<String> keys() {
        return null;
    }

    @Override
    public Collection<Object> values() {
        return null;
    }
}
