package com.zj.test.spring.spring_beans;

import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 21:09
 * @description: BeanUtils其他方法测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class BeanUtilsOtherTests {

    static class C1 {
        int i;
    }

    /**
     * 1.public static boolean isSimpleValueType(Class<?> type)
     * <p><pre>
     * 作用：
     * 判断type类型是否是简单类型，简单类型有以下：
     * byte Byte
     * short Short
     * int Integer
     * long Long
     * float Float
     * double Double
     * char Character
     * boolean Boolean
     *
     * String
     * </pre></p>
     *
     * @return 如果是简单类型，就返回true，否则返回false。注意，数组类型不是简单类型。
     */
    @Test
    public void isSimpleValueType() {

        TestHelper.startTest("BeanUtils.isSimpleValueType");
        TestHelper.startTest("基础类型");
        //基础类型
        TestHelper.println("isSimpleValueType(byte.class)", BeanUtils.isSimpleValueType(byte.class));
        TestHelper.println("isSimpleValueType(short.class)", BeanUtils.isSimpleValueType(short.class));
        TestHelper.println("isSimpleValueType(int.class)", BeanUtils.isSimpleValueType(int.class));
        TestHelper.println("isSimpleValueType(long.class)", BeanUtils.isSimpleValueType(long.class));
        TestHelper.println("isSimpleValueType(float.class)", BeanUtils.isSimpleValueType(float.class));
        TestHelper.println("isSimpleValueType(double.class)", BeanUtils.isSimpleValueType(double.class));
        TestHelper.println("isSimpleValueType(char.class)", BeanUtils.isSimpleValueType(char.class));
        TestHelper.println("isSimpleValueType(boolean.class)", BeanUtils.isSimpleValueType(boolean.class));

        TestHelper.startTest("包装类");
        // 包装类
        TestHelper.println("isSimpleValueType(Byte.class)", BeanUtils.isSimpleValueType(Byte.class));
        TestHelper.println("isSimpleValueType(Short.class)", BeanUtils.isSimpleValueType(Short.class));
        TestHelper.println("isSimpleValueType(Integer.class)", BeanUtils.isSimpleValueType(Integer.class));
        TestHelper.println("isSimpleValueType(Long.class)", BeanUtils.isSimpleValueType(Long.class));
        TestHelper.println("isSimpleValueType(Float.class)", BeanUtils.isSimpleValueType(Float.class));
        TestHelper.println("isSimpleValueType(Double.class)", BeanUtils.isSimpleValueType(Double.class));
        TestHelper.println("isSimpleValueType(Character.class)", BeanUtils.isSimpleValueType(Character.class));
        TestHelper.println("isSimpleValueType(Boolean.class)", BeanUtils.isSimpleValueType(Boolean.class));

        //数组
        TestHelper.startTest("数组类型");
        TestHelper.println("isSimpleValueType(int[].class)", BeanUtils.isSimpleValueType(int[].class));
        TestHelper.println("isSimpleValueType(Integer[].class)", BeanUtils.isSimpleValueType(Integer[].class));
        TestHelper.println("isSimpleValueType(String[].class)", BeanUtils.isSimpleValueType(String[].class));
        TestHelper.println("isSimpleValueType(C1[].class)", BeanUtils.isSimpleValueType(C1[].class));

        // String类型
        TestHelper.startTest("String类型");
        TestHelper.println("isSimpleProperty(String.class)", BeanUtils.isSimpleValueType(String.class));

        // 自定义类型
        TestHelper.startTest("自定义类型C1");
        TestHelper.println("isSimpleProperty(C1.class)", BeanUtils.isSimpleValueType(C1.class));
    }


    /**
     * 1.public static boolean isSimpleProperty(Class<?> type)
     * <p><pre>
     * 作用：
     * 判断type类型是否是简单类型，简单类型有以下：
     * byte Byte
     * short Short
     * int Integer
     * long Long
     * float Float
     * double Double
     * char Character
     * boolean Boolean
     *
     * String
     *
     * 需要注意的是，除了以上的简单类型，还包括上述类型的数组类型，如：
     * isSimpleProperty(int[].class): true
     * isSimpleProperty(Integer[].class): true
     * isSimpleProperty(String[].class): true
     * isSimpleProperty(C1[].class): false
     * </pre></p>
     *
     * 与isSimpleValueType的区别：
     * 比isSimpleValueType简单类型更广，包括简单类型的数组类型
     *
     * @return 如果是简单类型，就返回true，否则返回false。
     */
    @Test
    public void isSimpleProperty() {
        TestHelper.startTest("BeanUtils.isSimpleProperty");
        TestHelper.startTest("基础类型");
        //基础类型
        TestHelper.println("isSimpleProperty(byte.class)", BeanUtils.isSimpleProperty(byte.class));
        TestHelper.println("isSimpleProperty(short.class)", BeanUtils.isSimpleProperty(short.class));
        TestHelper.println("isSimpleProperty(int.class)", BeanUtils.isSimpleProperty(int.class));
        TestHelper.println("isSimpleProperty(long.class)", BeanUtils.isSimpleProperty(long.class));
        TestHelper.println("isSimpleProperty(float.class)", BeanUtils.isSimpleProperty(float.class));
        TestHelper.println("isSimpleProperty(double.class)", BeanUtils.isSimpleProperty(double.class));
        TestHelper.println("isSimpleProperty(char.class)", BeanUtils.isSimpleProperty(char.class));
        TestHelper.println("isSimpleProperty(boolean.class)", BeanUtils.isSimpleProperty(boolean.class));

        TestHelper.startTest("包装类");
        // 包装类
        TestHelper.println("isSimpleProperty(Byte.class)", BeanUtils.isSimpleProperty(Byte.class));
        TestHelper.println("isSimpleProperty(Short.class)", BeanUtils.isSimpleProperty(Short.class));
        TestHelper.println("isSimpleProperty(Integer.class)", BeanUtils.isSimpleProperty(Integer.class));
        TestHelper.println("isSimpleProperty(Long.class)", BeanUtils.isSimpleProperty(Long.class));
        TestHelper.println("isSimpleProperty(Float.class)", BeanUtils.isSimpleProperty(Float.class));
        TestHelper.println("isSimpleProperty(Double.class)", BeanUtils.isSimpleProperty(Double.class));
        TestHelper.println("isSimpleProperty(Character.class)", BeanUtils.isSimpleProperty(Character.class));
        TestHelper.println("isSimpleProperty(Boolean.class)", BeanUtils.isSimpleProperty(Boolean.class));

        // String类型
        TestHelper.startTest("String类型");
        TestHelper.println("isSimpleProperty(String.class)", BeanUtils.isSimpleProperty(String.class));

        //数组
        TestHelper.startTest("数组类型");
        TestHelper.println("isSimpleProperty(int[].class)", BeanUtils.isSimpleProperty(int[].class));
        TestHelper.println("isSimpleProperty(Integer[].class)", BeanUtils.isSimpleProperty(Integer[].class));
        TestHelper.println("isSimpleProperty(String[].class)", BeanUtils.isSimpleProperty(String[].class));
        TestHelper.println("isSimpleProperty(C1[].class)", BeanUtils.isSimpleProperty(C1[].class));

        // 自定义类型
        TestHelper.startTest("自定义类型C1");
        TestHelper.println("isSimpleProperty(C1.class)", BeanUtils.isSimpleProperty(C1.class));
    }
}
