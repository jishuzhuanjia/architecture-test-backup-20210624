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
     * Integer(int value)
     * Integer(String s)                尝试将十进制字符串转换成Integer对象
     *
     * 通过构造函数获得的Integer对象是new出来的，因此和IntegerCache中的Integer对象引用永远不会相同。
     *
     * ------------------------------------
     * 测试：Integer对象进行==判断的几种情形
     *
     * 结论：
     * 1.[-128~127)内非构造方式获取的Integer对象，只要值相等,==判断为true。
     */
    @Test
    public void constructor() {

        // 1.一个new ,一个来自IntegerCache
        Integer integer1 = new Integer(11);
        Integer integer2 = 11;
        // false，因为interger1是new出来的
        TestHelper.println("integer1 == integer2", integer1 == integer2);
        // false,因为integer1FromString是new出来的
        Integer integer1FromString = new Integer("11");
        TestHelper.println("integer1FromString == integer2", integer1FromString == integer2);

        // 2.两个都是IntegerCache
        Integer integer3 = 11;
        // true,因为是IntegerCache中的相同对象
        TestHelper.println("integer3 == integer2", integer3 == integer2);

        //3.两个大于127
        Integer integer4 = 128;
        Integer integer5 = 128;
        // false,因为integer4,integer5不在[128,127)范围内，都是new出来的
        TestHelper.println("integer4 == integer5", integer4 == integer5);

        //判断127是否为临界值
        Integer integer6 = 127;
        Integer integer7 = 127;
        // true: 证明上临界值为127
        TestHelper.println("integer6 == integer7", integer6 == integer7);

        //判断下临界值是否为-128
        Integer integer8 = -128;
        Integer integer9 = -128;
        // true: 证明下临界值为-128
        TestHelper.println("integer8 == integer9", integer8 == integer9);

        // <-128
        Integer integer10 = -129;
        Integer integer11 = -129;
        // 小于-128 false: 证明了下临界值是-128
        TestHelper.println("integer10 == integer11", integer10 == integer11);
    }


    /**
     * 2.测试: public boolean equals(Object obj)
     *
     * 作用：比较两个对象的int值
     *
     * 返回：如果Obj不是Integer实例，返回false,否则返回两者init值是否相同。
     *
     * 注意：不会比较对象的引用，只是比较值(区别于==)
     */
    @Test
    public void equals() {
        Integer i1 = 200;
        Integer i2 = 200;
        TestHelper.println("i1.equals(i2)",i1.equals(i2));
    }

    /**
     * 3.public static int parseInt(String s)
     *   public static int parseInt(String s, int radix)
     * 将int类型字符串解析成int类型,如果不能解析，会抛出NumberFormatException
     *
     * 参数:
     * radix：指定字符串进制
     *
     * <p><pre>NOTE
     * s不能有多余的空白
     * 以下数据格式都不能解析，会抛出NumberFormatException
     * Integer.parseInt("11.1")
     * Integer.parseInt(" 11")
     * Integer.parseInt("11 ")
     * Integer.parseInt("1 1")
     *
     */
    @Test
    public void parseInt(){
        TestHelper.startTest("Integer parseInt测试");
        TestHelper.println("Integer.parseInt(\"11\")",Integer.parseInt("11"));

        //java.lang.NumberFormatException: For input string: "11.1"
        //TestHelper.println("Integer.parseInt(\"11.1\")",Integer.parseInt("11.1"));

        //TestHelper.println("Integer.parseInt(\"11 \")",Integer.parseInt("11 "));
        //TestHelper.println("Integer.parseInt(\" 11\")",Integer.parseInt(" 11"));
        //TestHelper.println("Integer.parseInt(\" 11\")",Integer.parseInt("1 1"));

        // 解析2进制字符串
        TestHelper.println("Integer.parseInt(\"1101\",2)", Integer.parseInt("1101",2));
        // 16进制字符串需要移除0x前缀
        //java.lang.NumberFormatException: For input string: "0xff"
        //TestHelper.println(" Integer.parseInt(\"0xff\",2)", Integer.parseInt("0xff",16));

        // 解析16进制字符串
        // 255
        TestHelper.println("Integer.parseInt(\"ff\",16)", Integer.parseInt("ff",16));
        // -255
        TestHelper.println("Integer.parseInt(\"ff\",16)", Integer.parseInt("-ff",16));

        // 解析8进制: 8进制字符串开头的0是可选的，不会报错
        // 10
        TestHelper.println("Integer.parseInt(\"ff\",16)", Integer.parseInt("012",8));
        // 10
        TestHelper.println("Integer.parseInt(\"ff\",16)", Integer.parseInt("12",8));

    }

    /**
     * 4.Integer valueOf(String s, int radix)
     * Integer valueOf(String s)
     * Integer valueOf(int i)
     *
     * 将int字符串/int转换成Integer类型，如果值在-128~127,会直接返回IntegerCache中的对象，否则new一个返回
     */
    @Test
    public void valueOf(){

        Integer i13 = Integer.valueOf("13");
        Integer i132=Integer.valueOf("13");
        // true
        TestHelper.println("i13==i132",i13==i132);
    }
}
