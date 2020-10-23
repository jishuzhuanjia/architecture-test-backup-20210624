package com.zj.test.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/22 15:07
 * @description: Slf4j Logger测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月22日 15:42:46
 */
// 该注解相当于
// private static final Logger log = org.slf4j.LoggerFactory.getLogger(LombokTest.class)
@Slf4j
public class LombokTest {

    /**
     * 1.void debug(String  msg)  打印debug级别消息,黑色字体
     * <p>
     * 15:17:41.598 [main] DEBUG com.zj.test.lombok.LombokTest - this is message of level debug
     */
    @Test
    public void debug() {
        log.debug("this is message of level debug");
    }

    /**
     * 2.void debug(String  msg,Throwable  t) 先打印msg，再打印异常，黑色字体
     * <p>
     * 15:20:55.047 [main] DEBUG com.zj.test.lombok.LombokTest - this is message of level debug
     * java.lang.IllegalArgumentException: 非法参数
     * at com.zj.test.lombok.LombokTest.debug2(LombokTest.java:36)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * at java.lang.reflect.Method.invoke(Method.java:498)
     * at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     * at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     * at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     * at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     * at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     * at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     * at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     * at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     * at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     * at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     */
    @Test
    public void debug2() {
        log.debug("this is message of level debug", new IllegalArgumentException("非法参数"));
    }

    /**
     * 3.void debug(String  format,Object  arg)
     * 可以将arg消息嵌入到format表达式中
     */
    @Test
    public void debug3() {
        // getLocalizedMessage,geMessage返回"非法参数"
        // 15:30:36.506 [main] DEBUG com.zj.test.lombok.LombokTest - a exception occured:【com.zj.test.lombok.LombokTest.debug3(LombokTest.java:71)】
        log.debug("a exception occured:【{}】", new IllegalArgumentException("非法参数").getStackTrace());
        // 15:31:55.295 [main] DEBUG com.zj.test.lombok.LombokTest - a exception occured:【java.lang.IllegalArgumentException: 非法参数】
        log.debug("a exception occured:【{}】", new IllegalArgumentException("非法参数"), "");

    }

    /**
     * 4.void info(String  msg) 打印info级别信息,黑色，用法同debug不再赘述
     */
    @Test
    public void info() {
        // 15:33:42.513 [main] INFO com.zj.test.lombok.LombokTest - this is a message of level log
        log.info("this is a message of level log");
    }

    /**
     * 5.void error(String  msg) 打印error级别信息,用法同debug不再赘述
     * 注意: error方法只是打印消息，而不是抛出异常，因此不会中断代码。
     */
    @Test
    public void error() {
        log.error("this is a message of level error");
        log.error("message:{}", new IllegalArgumentException("非法参数"));
        log.error("message:{}", new IllegalArgumentException("非法参数").getStackTrace());
    }

    /**
     * 6.void warn(String  msg) 打印warn级别消息
     * <p>
     * 15:39:16.831 [main] WARN com.zj.test.lombok.LombokTest - this is a message of level warn
     */
    @Test
    public void warn() {
        log.warn("this is a message of level warn");
    }

    /**
     * 7.void trace(String  msg)
     * trace级别比debug级别更低，在控制台中都不会打印。
     */
    @Test
    public void trace() {
        log.trace("this is a message of level trace");
    }
}
