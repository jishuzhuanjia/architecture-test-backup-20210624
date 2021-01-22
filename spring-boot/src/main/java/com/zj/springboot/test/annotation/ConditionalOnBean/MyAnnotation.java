package com.zj.springboot.test.annotation.ConditionalOnBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 21:48
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
}
