package com.yongle.goku.utils;

import com.yongle.goku.vo.ReturnBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by weinh on 2016/5/5.
 */
public class ConfigUtils {

    private static Logger log = LoggerFactory.getLogger(ConfigUtils.class);
    private static Properties properties = new Properties();
    private static Element root = null;

    static {
        try {
            properties.load(ConfigUtils.class.getResourceAsStream("/config.properties"));
            initErrorCode();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        }
    }

    private static void initErrorCode() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(ConfigUtils.class.getResourceAsStream("/error_code.xml"));
        root = document.getRootElement();
    }

    /**
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        String value = TextUtils.defaultIfEmpty((String) properties.get(key), "");
        try {
            value = new String(value.getBytes("iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 获取错误信息的中文描述
     *
     * @param errorCode
     * @return
     */
    private static String getErrorMsg(Integer errorCode) {
        String code = "code" + errorCode;
        Element element = root.element(code);
        String errorMsg = "未定义错误" + errorCode;
        if (element != null) {
            errorMsg = TextUtils.defaultIfEmpty(element.getTextTrim(), errorMsg);
        }
        return errorMsg;
    }

    /**
     * 生成返回信息
     *
     * @param errorCode
     * @return
     */
    public static ReturnBean generateReturnBean(int errorCode) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setErrorCode(errorCode);
        returnBean.setErrorMsg(getErrorMsg(errorCode));
        return returnBean;
    }
}
