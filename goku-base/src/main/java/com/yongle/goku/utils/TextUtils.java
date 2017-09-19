package com.yongle.goku.utils;

import com.yongle.goku.utils.constant.SystemParamConst;
import com.yongle.goku.utils.security.MD5;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yongl on 2016/5/8.
 */
public class TextUtils extends StringUtils {
    private static final Logger log = LoggerFactory.getLogger(TextUtils.class);

    private static final String EMAIL_PATTERN = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String PHONECODE_PATTERN = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return matcher(email, EMAIL_PATTERN);
    }

    /**
     * 验证手机号码
     *
     * @param phoneCode
     * @return
     */
    public static boolean isPhoneCode(String phoneCode) {
        return matcher(phoneCode, PHONECODE_PATTERN);
    }

    private static boolean matcher(String input, String regex) {
        boolean flag;
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(input);
            flag = m.matches();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            flag = false;
        }
        return flag;
    }

    /**
     * 生成密码
     *
     * @param name     用户名
     * @param password 明文密码
     * @return MD5加密密码
     */
    public static String generatePassword(final String name, final String password) {
        return MD5.GetMD5Code(name + SystemParamConst.PASSWORDSYMBOL
                + password);
    }

    /**
     * 模板替换模型
     *
     * @param verifyCode 验证码
     * @return
     */
    public static Map<String, Object> generateModel(String verifyCode) {
        Map<String, Object> model = new HashMap<>();
        model.put("verificationCode", verifyCode);
        model.put("expirationTime", ConfigUtils.getProperty("expiration_time"));
        return model;
    }
}
