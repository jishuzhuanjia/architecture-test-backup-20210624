package com.zj.test.java.lang.thread;

import com.zj.test.util.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/5 15:01
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Slf4j
public class SleepTest {

    /*
     * 1.测试打断正在阻塞状态如sleep状态的线程
     *
     * 【结果】
     * 会抛出打断异常
     * java.lang.InterruptedException: sleep interrupted
     * at java.lang.Thread.sleep(Native Method)
     * at com.zj.test.java.lang.thread.SleepTest.lambda$sleep1$0(SleepTest.java:30)
     * at java.lang.Thread.run(Thread.java:748)
     *
     * 【结论】
     * 1.线程睡眠状态被打断，会将打断标记置为false。
     * 此时catch语句中,thread.isInterrupted()返回false。
     */
    @Test
    public void interruptSleepStatus() {
        Thread thread = new Thread(() -> {
            TestHelper.println("thread" + Thread.currentThread().getName() + " is going to sleep for 10s");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                /*
                被打断进入catch后，线程打断状态将置为false。
                */
                TestHelper.println("interrupt status in catch", Thread.interrupted());
                e.printStackTrace();
            }

            TestHelper.println("code after sleep");
        });
        thread.start();

        // 保证线程已经开始睡眠
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打断睡眠状态的线程,最终打断状态将是false
        thread.interrupt();

        // 防止虚拟机退出
        try {
            thread.join(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.测试：调用sleep的线程，是否前提需要打断状态为false?
     * author : 2025513
     *
     * 测试:
     * 主线程首先打断自己，然后尝试sleep
     *
     * 结果: isInterrupted()为true的线程在调用sleep()等阻塞方法时,会直接抛出异常:
     * java.lang.InterruptedException: sleep interrupted
     * at java.lang.Thread.sleep(Native Method)
     * at com.zj.test.java.lang.thread.SleepTest.sleep2(SleepTest.java:70)
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
     *
     * 结论:
     * 是
     * 1.线程调用sleep方法时，如果当前线程是打断状态，则sleep会立即被打断。
     * 因此线程调用 sleep 等阻塞方法之前，需要清除打断标记，否则无法阻塞。
     */
    @Test
    public void sleep2() {

        // 主线程打断自己
        Thread.currentThread().interrupt();

        TestHelper.println("主线程当前是否是打断状态: ", Thread.currentThread().isInterrupted());
        TestHelper.println("主线程准备调用sleep...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // false
        TestHelper.println("线程当前是否打断状态: ", Thread.currentThread().isInterrupted());
    }
}
