package com.yongle.goku.utils.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author weinh
 */
public class RedisClusterConfigurationEX extends RedisClusterConfiguration {
    final Logger log = LoggerFactory.getLogger(getClass());

    public RedisClusterConfigurationEX(String hostPortStr) {
        if (StringUtils.isBlank(hostPortStr)) {
            throw new RuntimeException("集群地址没有配置");
        }
        String[] hostPorts = hostPortStr.split(",");
        if (hostPorts.length < 3) {
            log.warn("建议配置集群的个数大于3：{}", hostPortStr);
        }
        Set<RedisNode> sentinels = new HashSet<>();
        for (String hostPort : hostPorts) {
            String[] hostAndPort = hostPort.split(":");
            if (hostAndPort.length != 2) {
                throw new RuntimeException("集群地址没有配置正确，类似ip1:port1,ip2:port2,ip3:port3");
            }
            String host = hostAndPort[0];
            String port = hostAndPort[1];
            if (!StringUtils.isNumeric(port)) {
                throw new RuntimeException("集群地址没有配置正确，端口必须是数字：" + port);
            }
            RedisNode redisNode = new RedisNode(host, Integer.parseInt(port));
            sentinels.add(redisNode);
        }
        log.info("正在通过集群连接redis：{}", sentinels);
        super.setClusterNodes(sentinels);
    }
}
