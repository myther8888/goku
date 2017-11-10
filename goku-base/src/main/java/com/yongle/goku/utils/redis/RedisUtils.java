package com.yongle.goku.utils.redis;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author weinh
 */
@Service
public class RedisUtils {
    private Logger log = LoggerFactory.getLogger(getClass());

    public static class RedisKey {
        public static String getLoginRepeatKey(String source) {
            return ("loginRepeat:" + source).toUpperCase();
        }

        public static String getUserTokensKey(Long userId) {
            return ("tokens:" + userId).toUpperCase();
        }

        public static String getMaleThrowBottlesKey() {
            return "BOTTLE:THROW:MALE";
        }

        public static String getFemaleThrowBottlesKey() {
            return "BOTTLE:THROW:FEMALE";
        }

        public static String getThrowBottleTimesKey(Long userId) {
            return ("bottle:throw:times:" + userId).toUpperCase();
        }

        public static String getPickBottleTimesKey(Long userId) {
            return ("bottle:pick:times:" + userId).toUpperCase();
        }

        public static String getMyPickBottlesKey(Long userId) {
            return ("bottle:pick:" + userId).toUpperCase();
        }

        public static String getPickBottleUsersKey() {
            return ("bottle:pick:users").toUpperCase();
        }

        public static String getUserAuthorizationKey(Long userId) {
            return ("user:authorization:" + userId).toUpperCase();
        }

        public static String getUserTokenKey(String token) {
            return ("user:token:" + token).toUpperCase();
        }

        public static String getUserAuthenticationKey(String token) {
            return ("user:authentication:" + token).toUpperCase();
        }
    }

    @Resource
    private RedisTemplate<String, Object> stringRedisTemplate;

    /**
     * 自增
     *
     * @param key   key
     * @param value 步长
     * @return
     */
    public Long increment(String key, Long value) {
        if (key != null) {
            return stringRedisTemplate.opsForValue().increment(key, value);
        }
        return null;
    }

    public void hMSet(String key, Map<String, Object> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    public Map<Object, Object> hGetAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * get操作
     *
     * @param key key
     * @return
     */
    public Object get(String key) {
        if (key != null) {
            return stringRedisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * set操作
     *
     * @param key     key
     * @param value   值
     * @param timeout 过期时间
     * @param unit    过期时间单位
     */
    public void set(String key, Object value, Long timeout, TimeUnit unit) {
        if (key != null) {
            if (timeout != null && unit != null) {
                stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
            } else {
                stringRedisTemplate.opsForValue().set(key, value);
            }

        }
    }

    /**
     * 添加到集合中
     *
     * @param key   key
     * @param value 值
     */
    public void add(String key, String value) {
        if (key != null) {
            stringRedisTemplate.opsForSet().add(key, value);
        }
    }

    /**
     * 从集合中随机取一个
     *
     * @param key key
     * @return
     */
    public Object randMember(String key) {
        if (key != null) {
            return stringRedisTemplate.opsForSet().randomMember(key);
        }
        return null;
    }

    /**
     * 从集合中移除
     *
     * @param key    key
     * @param values 支持多个值同时移除
     */
    public void remove(String key, String[] values) {
        if (key != null) {
            stringRedisTemplate.opsForSet().remove(key, values);
        }
    }

    /**
     * 获取集合中所有内容
     *
     * @param key key前缀
     * @return 成员集合
     */
    public Set<Object> members(String key) {
        if (key != null) {
            return stringRedisTemplate.opsForSet().members(key);
        }
        return null;
    }

    /**
     * 删除key
     *
     * @param keys 支持多个key同时移除
     */
    public void del(List<String> keys) {
        if (CollectionUtils.isNotEmpty(keys)) {
            stringRedisTemplate.delete(keys);
        }
    }

    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 设置过期时间
     *
     * @param key     key
     * @param timeout 过期时间
     * @param unit    过期时间单位
     * @return
     */
    public Boolean expire(String key, final long timeout, final TimeUnit unit) {
        if (key != null) {
            return stringRedisTemplate.expire(key, timeout, unit);
        }
        return false;
    }

    /**
     * 设置何时过期
     *
     * @param key  key
     * @param date 过期时间
     * @return
     */
    public Boolean expireAt(String key, final Date date) {
        if (key != null) {
            return stringRedisTemplate.expireAt(key, date);
        }
        return false;
    }

    /**
     * 有序集合中添加元素
     *
     * @param key   key
     * @param value 值
     * @param score 权重
     */
    public void add(String key, String value, Double score) {
        if (key != null) {
            stringRedisTemplate.opsForZSet().add(key, value, score);
        }
    }

    /**
     * 根据权重从有序集合中移除
     *
     * @param key key
     * @param min 最小权重
     * @param max 最大权重
     */
    public void removeRangeByScore(String key, Double min, Double max) {
        if (key != null) {
            stringRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
        }
    }

    /**
     * 检查有序集合中是否存在值
     *
     * @param key   key
     * @param value 值
     * @return 存在返回权重，不存在返回null
     */
    public Long rank(String key, String value) {
        if (key != null) {
            return stringRedisTemplate.opsForZSet().rank(key, value);
        }
        return null;
    }

    /**
     * 加入到列表左边
     *
     * @param key   key
     * @param value 值
     * @return 列表长度
     */
    public Long leftPush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 从列表右边移除
     *
     * @param key key
     * @return 被移除内容
     */
    public Object rightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }
}
