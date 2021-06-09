package com.zj.test.java.lang.thread;


import com.zj.test.util.TestHelper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
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

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 3.api测试
     *
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void getId() {
        for (int i = 0; i < 5; i++) {
            Thread aThread = createAThread();
            aThread.start();
            TestHelper.println("thread is running, id: " + aThread.getId() + ", name: " + aThread.getName());
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 4.测试: 默认优先级别
     *
     *
     * 【作用】
     *
     * 【测试结果】
     * aThread.getPriority().: 5
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void getPriority() {
        Thread aThread = createAThread();
        TestHelper.println("aThread.getPriority()", aThread.getPriority());
    }

    /**
     * <p>
     *     5.测试: thread.interrupt()对阻塞状态的影响
     * </p>
     *
     * 【出入参记录】
     * [Thread-0] - 线程进入休眠
     * [Thread-0] - thread.isInterrupted(): false
     * java.lang.InterruptedException: sleep interrupted
     * 	at java.lang.Thread.sleep(Native Method)
     * 	at com.zj.test.java.lang.thread.ThreadTest$Thread1.run(ThreadTest.java:209)
     *
     * 【结论】
     * 会打断阻塞状态，并且立即清除打断标记。
     *
     * 【注意点】
     *
     */
    class Thread1 extends Thread implements Runnable {
        @Override
        public void run() {
            try {
                TestHelper.println("线程进入休眠");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // 如果线程休眠过程被打断，进入catch语句之前，打断标记已经被清除。
                // [Thread-0] - thread.isInterrupted(): false
                TestHelper.println("thread.isInterrupted()", isInterrupted());
                e.printStackTrace();
            }
        }
    }
    @Test
    public void interruptTest() {

        Thread1 thread1 = new Thread1();
        thread1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 6.public final String getName()
     * public long getId()
     *
     * 【作用/描述】
     * 获取线程对象的名字/id, 名字和id在线程构造后就被初始化了。
     *
     * 【出/入参记录】
     * [main] - thread.getName(): Thread-0
     * [main] - thread.getId(): 12
     * [main] - thread.getName(): Thread-0
     * [main] - thread.getId(): 12
     *
     * 【使用注意点】
     *
     */
    @Test
    public void getNameAndGetId(){

        Thread thread = new Thread(() -> {

        });
        TestHelper.println("thread.getName()",thread.getName());
        TestHelper.println("thread.getId()",thread.getId());
        thread.start();
        TestHelper.println("thread.getName()",thread.getName());
        TestHelper.println("thread.getId()",thread.getId());
    }
}
