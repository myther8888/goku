package com.yongle.goku.base.validate.annotation;


import com.yongle.goku.base.validate.ValidateListImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 类 名 称：ValidateList.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年04月24日
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateListImpl.class)
public @interface ValidateList {
    String message() default "集合参数有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * <b>字段=正则</b>形式的数组
     *
     * @return 字段规则数组
     */
    String[] fieldsRegExp();

    /**
     * 集合元素的类型
     *
     * @return 全路径className
     */
    String className();

    /**
     * Defines several {@link ValidateList} annotations on the same element.
     *
     * @see ValidateList
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ValidateList[] value();
    }
}
