package com.zj.test.java.lang;

import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/26 14:57
 * @description: 抽象类测试
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月26日 15:05:18
 */
public abstract class AbstractClassTest {
    /**
     * 1.没有抽象方法的abstract类可以实例化吗?
     * <p>
     * 结果: 不能
     */
    @Test
    public void test1() {
        /**
         * 'AbstractClassTest' is abstract; cannot be instantiated
         */
        //AbstractClassTest abstractClassTest = new AbstractClassTest();
    }


}
