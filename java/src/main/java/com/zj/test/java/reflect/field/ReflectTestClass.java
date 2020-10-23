package com.zj.test.java.reflect.field;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 11:32
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ReflectTestClass extends ReflectTestClassParent implements ReflectTestInterface
 {

    /*私有简单类型属性*/
    @Value("1")
    @DateTimeFormat
    private int privateInt;
    /*私有静态简单类型属性*/
    private static int privateStaticInt;
    public int publicInt;
    public static int publicStaticInt;

    protected int defaultInt;

    private Integer privateInteger;
    private static Integer privateStaticInteger;
    public Integer publicInteger;
    public static Integer publicStaticInteger;

    @Override
    public String toString() {
       return "ReflectTestClass{" +
               "privateInt=" + privateInt +
               ", publicInt=" + publicInt +
               ", defaultInt=" + defaultInt +
               ", privateInteger=" + privateInteger +
               ", publicInteger=" + publicInteger +
               '}';
    }
 }
