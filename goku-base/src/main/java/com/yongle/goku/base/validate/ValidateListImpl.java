package com.yongle.goku.base.validate;

import com.alibaba.fastjson.JSONObject;
import com.yongle.goku.base.validate.annotation.ValidateList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 类 名 称：ValidateListImpl.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年04月24日
 */
public class ValidateListImpl implements ConstraintValidator<ValidateList, List> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String[] fieldsRegExp;

    private List<Map<String, String>> maps;

    private String className;

    @Override
    public void initialize(ValidateList constraintAnnotation) {
        fieldsRegExp = constraintAnnotation.fieldsRegExp();
        className = constraintAnnotation.className();
        validateParameters();
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext context) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        try {
            for (Object obj : list) {
                if (className.equals(obj.getClass().getName())) {
                    for (Map<String, String> map : maps) {
                        if (!checkField(obj, map.get("field"), map.get("regExp"))) {
                            logger.error("集合中对象属性不合法：{}，规则：{}",
                                    JSONObject.toJSONString(obj), map);
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    private boolean checkField(Object obj, String field, String regExp)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            if (property.getName().equals(field)) {
                Method method = property.getReadMethod();
                if (method == null) {
                    return true;
                }
                Object value = method.invoke(obj);
                return value != null && Pattern.matches(regExp, String.valueOf(value));
            }
        }
        return false;
    }

    private void validateParameters() {
        if (StringUtils.isBlank(className)) {
            throw new IllegalArgumentException("ValidateList className 配置不正确");
        }
        if (ArrayUtils.isNotEmpty(fieldsRegExp)) {
            maps = new ArrayList<>(fieldsRegExp.length);
            for (String str : fieldsRegExp) {
                Map<String, String> map = new HashMap<>(2);
                String[] fieldRegExp = str.split("=");
                if (fieldRegExp.length != 2 || StringUtils.isBlank(fieldRegExp[0])
                        || StringUtils.isBlank(fieldRegExp[1])) {
                    throw new IllegalArgumentException("ValidateList fieldsRegExp " + str + " 配置不正确");
                }
                map.put("field", fieldRegExp[0]);
                map.put("regExp", fieldRegExp[1]);
                maps.add(map);
            }
        }
        if (CollectionUtils.isEmpty(maps)) {
            throw new IllegalArgumentException("ValidateList fieldsRegExp 配置不正确");
        }
    }
}
