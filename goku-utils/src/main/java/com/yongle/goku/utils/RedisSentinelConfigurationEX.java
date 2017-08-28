package com.yongle.goku.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;

import java.util.HashSet;
import java.util.Set;

/**
 * 类 名 称：RedisSentinelConfigurationEX.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年08月24日
 */
public class RedisSentinelConfigurationEX extends RedisSentinelConfiguration {
    final Logger log = LoggerFactory.getLogger(getClass());

    public RedisSentinelConfigurationEX(String hostPortStr) {
        if (StringUtils.isBlank(hostPortStr)) {
            throw new RuntimeException("哨兵地址没有配置");
        }
        String hostPorts[] = hostPortStr.split(",");
        if (hostPorts.length % 2 == 0) {
            log.warn("建议配置哨兵的个数为奇数个：{}", hostPortStr);
        }
        Set<RedisNode> sentinels = new HashSet<>();
        for (String hostPort : hostPorts) {
            String hostAndPort[] = hostPort.split(":");
            if (hostAndPort.length != 2) {
                throw new RuntimeException("哨兵地址没有配置正确，类似ip1:port1,ip2:port2,ip3:port3");
            }
            String host = hostAndPort[0];
            String port = hostAndPort[1];
            if (!StringUtils.isNumeric(port)) {
                throw new RuntimeException("哨兵地址没有配置正确，端口必须是数字：" + port);
            }
            RedisNode redisNode = new RedisNode(host, Integer.parseInt(port));
            sentinels.add(redisNode);
        }
        log.info("正在通过哨兵连接redis：{}", sentinels);
        super.setSentinels(sentinels);
    }
}
