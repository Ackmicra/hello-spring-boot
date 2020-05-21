package com.dhcc.zpc.util.tkmybatis.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在持久层对象属性上加上@UpdateTime，即可给属性赋值，格式：yyyy-MM-dd HH:mm:ss.SSS
 * @author yuanshaobo
 * @date: 2020/1/7 9:27
 * @since v1.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UpdateTime {
    String value() default "";
}
