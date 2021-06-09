package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/9 16:22
 * @is-finished: 1
 *
 */
public class CyclicBarrierTest {


    // CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
    /**
     * 1.构造方法
     * 1.1.public CyclicBarrier(int parties,Runnable barrierAction)
     * 参数说明：
     * parties: 参与线程数await数达到parties，会先执行参数2执行的任务，然后所有因调用await阻塞的线程继续执行。
     *
     * 注意：参数2的任务由最后一个调用await()的线程来执行的。
     *
     * 1.2.public CyclicBarrier(int parties)
     * parties: 参与线程数await数达到parties，然后所有因调用await阻塞的线程继续执行。
     *
     */
    CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestHelper.println("全部线程就绪，准备执行任务.");
    });

    /**
     * <p>
     *     1.测试: CyclicBarrier入门测试
     * </p>
     *
     * 【出入参记录】
     *
     * 【结论】
     *
     * 【注意点】
     *
     */
    @Test
    public void test() {
        TestHelper.startTest("CyclicBarrier");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TestHelper.println("就绪");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                TestHelper.println("开始执行");
            }).start();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.测试：CyclicBarrier循环使用
     *
     *
     * 【结论】
     *1.CyclicBarrier会自动控制循环，一个循环结束后，下次await进入下一个循环。
     */
    @Test
    public void test2() {
        TestHelper.startTest("CyclicBarrier循环使用");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TestHelper.println("就绪");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                TestHelper.println("开始执行");
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestHelper.println("开始循环测试...");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TestHelper.println("就绪");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                TestHelper.println("开始执行");
            }).start();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3.public int await(long timeout,TimeUnit unit)
     *
     * 【作用/描述】
     * 指定时间内就绪线程数不达标，会抛出超时TimeoutException异常，结束等待，继续执行后面的代码。
     *
     * 需要注意的是，如果某个await等待超时了，CyclicBarrier处于被破坏(Broken)状态，
     * 后续的线程在await时会抛出BrokenBarrierException异常。
     *
     * 【出/入参记录】
     * java.util.concurrent.TimeoutException
     * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:257)
     * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
     * 	at com.zj.test.java.util.concurrent.CyclicBarrierTest.awaitTest(CyclicBarrierTest.java:146)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
     * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
     * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     *
     * 【使用注意点】
     *
     */
    @Test
    public void awaitTest() {

        try {
            cyclicBarrier.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        TestHelper.println("cyclicBarrier等待超时，继续执行.");

        /**
         * 测试：超时的线程是否计入计数？
         * 计数没有意义了，没有必要去讨论是否计数了。因为如果某个线程调用await超时了，则该CyclicBarrier被破坏，
         * 后续不管调用多少次await()总是抛出BrokenBarrierException异常。
         */

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    TestHelper.println("就绪");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 4.public int getNumberWaiting()
     *
     * 【作用/描述】
     * 获取当前就绪/正在等待的线程数，在没有发生异常的情况下，该值等于当前循环中调用await的线程数。
     *
     * 【出/入参记录】
     *
     * 【使用注意点】
     *
     */
    @Test
    public void getNumberWaitingTest() {

        TestHelper.startTest("getNumberWaiting()测试");
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //查看当前就绪的线程数
        //5
        TestHelper.println("cyclicBarrier.getNumberWaiting() - 就绪的线程数", cyclicBarrier.getNumberWaiting());

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 5.public boolean isBroken()
     *
     * 【作用/描述】
     * 返回当前CyclicBarrier是否已经被破坏。
     *
     * 【出/入参记录】
     *
     * 【使用注意点】
     *
     */
    @Test
    public void apitest() {
        TestHelper.startTest("isBroken");

        //使CyclicBarrier破坏
        try {
            cyclicBarrier.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        // true
        TestHelper.println("cyclicBarrier.isBroken()", cyclicBarrier.isBroken());
    }

    /**
     * 6.public void reset()
     *
     * 【作用/描述】
     * 破坏当前CyclicBarrier并重新初始化使其可用
     *
     * 对于那些正在await的线程，会抛出BrokenBarrierException异常
     *
     * reset可使已经被破坏的CyclicBarrier重新可用
     *
     * 【出/入参记录】
     *
     * 【使用注意点】
     *
     */
    @Test
    public void reset() {

        TestHelper.startTest("reset");

        //使CyclicBarrier破坏
        try {
            cyclicBarrier.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        // true
        TestHelper.println("cyclicBarrier.isBroken()", cyclicBarrier.isBroken());

        // reset-可使被破坏的CyclicBarrier重新可用
        cyclicBarrier.reset();

        TestHelper.println("cyclicBarrier.isBroken()", cyclicBarrier.isBroken());

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    TestHelper.println(finalI + "就绪");
                    cyclicBarrier.await();
                    TestHelper.println(finalI + "开始执行");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
