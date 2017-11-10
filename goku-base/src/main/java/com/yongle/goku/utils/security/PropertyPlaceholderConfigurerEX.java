package com.yongle.goku.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author weinh
 */
public class PropertyPlaceholderConfigurerEX extends PropertyPlaceholderConfigurer {

    private static final String KEY = "KWKdMIgFriwOkygqRlPUdQ==";
    private static Logger log = LoggerFactory.getLogger(PropertyPlaceholderConfigurerEX.class);
    private boolean secutiry = false;

    public static void main(String[] args) throws Exception {
        System.out.println(AESCodec.encrypt("123456", KEY));
        System.out.println(AESCodec.decrypt("jCVDnzq3StvOTmNiAjJTqQ==", KEY));
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        String placeholderValue = props.getProperty(placeholder);
        if (this.secutiry && "jdbc.password".equals(placeholder)) {
            log.debug("解密的属性：{}", placeholder);
            log.debug("解密前的字符串：{}", placeholderValue);
            try {
                placeholderValue = AESCodec.decrypt(placeholderValue, KEY);
            } catch (Exception e) {
                log.error(e.getMessage() + "，解密失败", e);
            }
            log.debug("解密后的字符串：{}", placeholderValue);
        }
        return placeholderValue;
    }

    public boolean isSecutiry() {
        return secutiry;
    }

    public void setSecutiry(boolean secutiry) {
        this.secutiry = secutiry;
    }

}
