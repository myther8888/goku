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
 * <p>类名称：ValidateList
 * <p>描述说明：校验对象中的集合（包括里面的元素字段）
 * <p>作者单位：恒生芸擎网络有限公司
 * <p>版本号：2.0.0.0
 *
 * @author weinh
 * @date 2017-12-20 11:10
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
