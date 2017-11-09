package com.yongle.goku.utils.shiro.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author weinh
 */
@Component
public class CacheManagerImpl extends AbstractCacheManager {

    @Resource
    private RedisCache redisCache;

    @Override
    protected Cache createCache(String s) throws CacheException {
        return redisCache;
    }
}
