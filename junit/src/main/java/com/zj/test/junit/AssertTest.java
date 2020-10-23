package com.zj.test.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/22 15:46
 * @description: org junit.jar Assert断言测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月22日 16:25:41
 */
@Slf4j
public class AssertTest {


    /**用来比较整型的唯一方法
     * 1.public static void assertEquals(long expected,long actual)  整型比较唯一方法
     * 不相同时断言错误
     *
     * java.lang.AssertionError:
     * Expected :10
     * Actual   :11
     * <Click to see difference>
     *
     * 	at org.junit.Assert.fail(Assert.java:88)
     * 	at org.junit.Assert.failNotEquals(Assert.java:834)
     * 	at org.junit.Assert.assertEquals(Assert.java:645)
     * 	at org.junit.Assert.assertEquals(Assert.java:631)
     * 	at com.zj.test.junit.AssertTest.test(AssertTest.java:25)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     * 	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     * 	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * 	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * 	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * 	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     */
    @Test
    public void assertEquals1(){
        // 整型都使用assertEquals(long expected,long actual)
        Assert.assertEquals(10,5+6);
    }

    /**
     * 用于比较浮点数的两个方法
     * 2. public static void assertEquals(float expected,
     *                                 float actual,
     *                                 float delta)
     *
     * static public void assertEquals(double expected, double actual, double delta)
     *
     *
     * delta必须大于等于0,否则总是抛出断言错误
     *
     * Math.abs(expected-actual) < delta,否则抛出异常
     *
     */
    @Test
    public void assertEeuals2(){
        Assert.assertEquals(1.1f,1.0f,0.11f);
    }

    /**
     * 3.void assertEquals(Object expected,
     *                                 Object actual)
     * 用来比较对象是否相等
     */
    @Test
    public void assertEquals3(){
        Assert.assertEquals("HelloWorld","HelloWorld1");
    }

    /**
     *
     * 4.assertEquals(message,expected,actual)系列方法
     * 值不相等时抛出错误,带有message
     *
     * java.lang.AssertionError: 值不相等
     * Expected :10
     * Actual   :1110
     * <Click to see difference>
     *
     *
     * 	at org.junit.Assert.fail(Assert.java:88)
     * 	at org.junit.Assert.failNotEquals(Assert.java:834)
     * 	at org.junit.Assert.assertEquals(Assert.java:645)
     * 	at com.zj.test.junit.AssertTest.test(AssertTest.java:98)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     * 	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     * 	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * 	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * 	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * 	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     */
    @Test
    public void assertEquals4(){
        Assert.assertEquals("值不相等",10,1110);
    }

    /**
     * 5. assertFalse(boolean condition)
     * 只有当condition为false时，不会报错
     * 当为true时，抛出错误
     */
    @Test
    public void assertFalse(){
        Assert.assertFalse(false);
        Assert.assertFalse("返回了true",false);
    }

    /***
     * 6. assertTrue(String message,boolean condition)
     * 返回false时抛出错误，并返回message信息。
     */
    @Test
    public void assertTrue(){
        Assert.assertTrue(true);
        Assert.assertTrue("返回了false",true);
    }

    /**
     * 7.assertNull(Object object)
     * object不为null时抛出错误
     */
    @Test
    public void assertNull(){
        Assert.assertNull(null);
        Assert.assertNull("not null",null);
    }

    /**
     * 8.assertNotNull(Object object)
     * 当object为null时抛出异常
     */
    @Test
    public void assertNotNull(){
        Assert.assertNotNull("hello");
        Assert.assertNotNull("返回了null值","hello");
    }

    /**
     * 9.assertNotEquals系列方法，和assertEquals相反
     * 当相同时抛出错误
     */
    @Test
    public void assertNotEqual(){
        Assert.assertNotEquals(10,11);
    }

    /**
     * public static void fail()                宣告测试失败
     * 不提示excepted actual
     *
     * java.lang.AssertionError
     * 	at org.junit.Assert.fail(Assert.java:86)
     * 	at org.junit.Assert.fail(Assert.java:95)
     * 	at com.zj.test.junit.AssertTest.fail(AssertTest.java:186)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     * 	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     * 	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * 	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * 	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * 	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     */
    @Test
    public void fail(){
        //Assert.fail();
        /*
        at org.junit.Assert.fail(Assert.java:88)
        at com.zj.test.junit.AssertTest.fail(AssertTest.java:219)
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
        at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)*/
        Assert.fail("测试失败了 55555");
    }

    /**
     *
     * 10.其他方法
     * assertSame               引用相同，否则抛出错误
     * assertNotSame            引用不相同，否则抛出错误
     * fail()                   直接宣告测试失败
     * fail(message)
     */
}
