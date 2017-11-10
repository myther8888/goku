package com.yongle.goku.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author weinh
 */
public class ConfigUtils {

    private final static Logger log = LoggerFactory.getLogger(ConfigUtils.class);
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigUtils.class.getResourceAsStream("/config.properties"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        String value = TextUtils.defaultIfEmpty((String) properties.get(key), "");
        try {
            value = new String(value.getBytes("iso-8859-1"));
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
        return value;
    }
}
