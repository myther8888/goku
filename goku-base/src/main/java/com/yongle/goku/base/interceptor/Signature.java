package com.yongle.goku.base.interceptor;

import com.yongle.goku.utils.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by weinh on 2017/3/2.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Signature {
    String type() default Constants.REQ_JSON;
}
