package com.yongle.goku.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统名称：U-OBS-MONITOR
 * 系统版本：V5.0.1
 * 模块名称：监控模块
 * 类 名 称：EntityUtils.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2017年03月13日
 * 审核人员：
 * 相关文档：
 * 修改记录：修改日期 修改人员 修改说明
 */
public class EntityUtils {
    static {
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new ByteConverter(null), Byte.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new DateConverter(null), Date.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
    }

    public static Map<String, Object> objectToHash(Object obj) {
        try {
            Map<String, Object> map = new HashMap<>();
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                if (!property.getName().equals("class")) {
                    Method method = property.getReadMethod();
                    if (method != null) {
                        Object value = method.invoke(obj);
                        if (value != null) {
                            map.put(property.getName(), value);
                        }
                    }
                }
            }
            return map;
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void hashToObject(Map<?, ?> map, Object obj) {
        try {
            BeanUtils.populate(obj, (Map) map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T hashToObject(Map<?, ?> map, Class c) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            Object obj = c.newInstance();
            hashToObject(map, obj);
            return (T) obj;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
