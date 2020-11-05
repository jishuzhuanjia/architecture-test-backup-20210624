package com.zj.test.java.lang.data_type;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/4 16:18
 * @description: 类型转换测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月4日 16:51:03
 */
public class TypeCastTest {

    /**
     * 1.Java自动类型转换
     */
    @Test
    public void autoTypeCast() {

        /*
        1.1.Java整型字面量会自动类型转换:

        整型的自动转换: java中整型字面量默认是int类型的。
        如果字面量在表达式左边类型的取值范围之内，会自动进行类型转换,
        如果不在表达式左边类型的取值范围之内，则会提示需要强制类型转换。
        */
        byte b1 = 10;// 字面量int 10在byte范围[128,127),自动转换成byte
        byte b2 = 127;
        byte b3 = (byte) 128;// 字面量int 128不在在byte范围[128,127),需要强制转换，否则报错。

        /*
        1.2.注意：
        Java浮点型字面量不会自动类型转换到float,虽然字面量的值在float的取值范围之内。

        Java中浮点型字面量默认是double类型
         */
        float f1 = (float) 1.0;//虽然字面量的值在float的取值范围之内，但是不会自动将double->float。需要强制类型转换,否则报错。

        // 如何将浮点字面量转换成float?
        float f2 = 1.0f;//在浮点数字面量后加上f,字面量就成了float类型。

        /*
         * 1.3.包装类的自动拆箱、包箱
         * 也是一种自动类型转换
         *
         * 拆箱: 包装类 -> 简单类的过程
         * 包箱: 简单类型 -> 包装类的过程
         *
         */
        int i1 = 15;
        // 1.3.1.包箱的过程不会发生异常
        Integer integer1 = i1;
        TestHelper.println("包厢结果: " + integer1);

        integer1 = null;
        /*
         1.3.2.拆箱的过程可能发生运行时异常，因为包装类对象可能为null:
         java.lang.NullPointerException
            at com.zj.test.java.lang.data_type.TypeCastTest.autoTypeCast(TypeCastTest.java:58)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
            at java.lang.reflect.Method.invoke(Method.java:498)
            at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
            at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
            at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
            at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
            at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
            at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
            at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
            at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
            at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
            at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
            at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
            at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
            at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
            at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
            at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
            at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
         */
        int i2 = integer1;
        TestHelper.println("拆箱结果: " + integer1);

    }

    /**
     * 2.强制类型转换
     */
    public void typeCast() {
        short s1 = 10;
        short s2 = 11;

        short s3 = (short) (s1 + s2);//s1+s2的类型为int,此处需要强制类型转换

        short s4 = (short) (s1 + 10);//s1+10类型为int

        int i1 = s1;//小类型可以直接赋值给大类型

        short s5 = (short) i1; //大类型赋值给小类型，需要强制类型转换。
    }
}
