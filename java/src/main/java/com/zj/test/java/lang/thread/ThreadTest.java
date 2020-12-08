package com.zj.test.java.lang.thread;


import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/18 16:45
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ThreadTest {


    /*创建一个测试线程*/
    Thread createAThread() {
        return new Thread(() -> {
            while (true) {

            }
        });
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 1.api测试
     * public void interrupt()
     *
     * public boolean isInterrupted()
     *
     *
     * 【作用】
     *
     * 【测试结果】
     * aThread.isInterrupted(): true
     * aThread.isInterrupted(): true
     *
     * 【结论】
     * 1.public void interrupt()
     * 将线程的打断标记置为true，这不会影响到线程的执行。
     * 但是如果当前线程之前由于调用wait,join,sleep等方法而处于阻塞状态，则会将
     * 打断标记置为false，打断阻塞状态并抛出打断异常。
     *
     * 2.public boolean isInterrupted()
     * 返回线程当前的打断状态，需要注意的是该方法不会更改打断状态。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void interrupt() {

        Thread aThread = createAThread();

        aThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        aThread.interrupt();

        // true
        TestHelper.println("aThread.isInterrupted()", aThread.isInterrupted());
        // true
        TestHelper.println("aThread.isInterrupted()", aThread.isInterrupted());

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class InterruptedTestThread extends Thread implements Runnable {

        public InterruptedTestThread(String name) {
            super(name);
        }

        @Override
        public void run() {

            while (true) {
                // 没有被打断才会执行
                if (!isInterrupted()) {
                    TestHelper.println(Thread.currentThread().getName() + " is running");
                    // 打断自己
                    this.interrupt();
                } else {
                    // Thread.interrupted()只能用来打断本线程，不用用来打断别的线程。
                    TestHelper.println("Thread.interrupted()之前的线程打断状态", Thread.interrupted());// true
                }

            }
        }
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 2.api测试
     * public static boolean interrupted()
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.public static boolean interrupted()
     * 将打断标记置为false,并返回之前的打断标记。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void interrupted() {
        InterruptedTestThread interruptedTestThread = new InterruptedTestThread("InterruptedTestThread");
        interruptedTestThread.start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
