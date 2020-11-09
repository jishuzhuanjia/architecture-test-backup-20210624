package com.zj.test.java.lang;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/9 21:07
 * @description: Integer api测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class IntegerTest {

    /**
     * 1.构造函数
     * public Integer(int value)
     * Integer(String s)                尝试将十进制字符串转换成Integer对象
     *
     * 通过构造函数获得的Integer对象是new出来的，因此和IntegerCache中的Integer对象引用永远不会相同。
     */
    @Test
    public void constructor() {

        Integer integer1 = new Integer(11);
        Integer integer2 = 11;
        TestHelper.println(integer1 == integer2);// false

        // java.lang.NumberFormatException: For input string: " 11"
        Integer integer3 = new Integer("11");
        TestHelper.println("new Integer(\"11\")",integer3);
        TestHelper.println(integer3==integer2); //false
    }
}
