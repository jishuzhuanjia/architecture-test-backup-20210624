package com.zj.test.java;

import com.zj.test.util.TestHelper;

/**
 * @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/26 14:34
 * @description: 动态代码块测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月26日 14:55:44
 *                  2021-6-8 13:27:14
 */
public class DynamicCodeBlockTest {

    /**
     * 代码块前置赋值
     */
    {
        TestHelper.println("dynamic code block-before");

        // 变量前置代码块中可以对成员变量进行赋值，但是如果变量定义的同时进行初始化，则此处的赋值会被覆盖。
        i = 2;      //①

        // 前置动态代码块不能引用变量：
        // Illegal forward reference
        //TestHelper.println("i",i);
    }

    int i=1;          //②

    /**
     * 动态代码块后置赋值
     */
    {
        i = 3;      //③
    }


    /**
     * 测试1：几处代码的赋值顺序
     * 【结论】
     * 注：① -> ② -> ③ -> ④，后置会覆盖前者的赋值。
     */
    public static void main(String[] args) {
        // 10000
        System.out.println(new DynamicCodeBlockTest().i);
    }

    public DynamicCodeBlockTest() {
        i = 10000;  //④
    }
}