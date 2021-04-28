package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 倒计时门闩 - 线程同步工具
 * </p>
 *
 * @author: zhoujian
 * @create-time: 2020/11/17 13:17
 * @description:
 * @finished-time: 2020年11月17日 14:42:04
 */
public class CountDownLatchTest {
    /*
     * 【CountDownLatch api文档注释内容精选】
     *
     * 使用给定的<em>count</em>初始化一个{@code CountDownLatch}。
     * {@link #await await}方法被阻塞，直到由于调用了{@link #countDown}方法，当前计数为零，之后所有等待的线程都被释放，
     * 随后调用的{@link #await await}立即返回。这是一次性现象——计数不能被重置。
     * 如果您需要一个重新设置计数的版本，可以考虑使用{@link CyclicBarrier}。
     *
     * 一个{@code CountDownLatch}是一个通用的同步工具，可以用于许多目的。
     * 初始化一个计数为1的{@code CountDownLatch}可以作为一个简单的开/关锁，或者门:所有调用{@link #await await}的线程都在门处等待，
     * 直到它被一个调用{@link #countDown}的线程打开。
     * 一个初始化为N的{@code CountDownLatch}可以用来让一个线程等待，直到N个线程完成了某个动作，或者某个动作已经完成了N次
     *
     * {@code CountDownLatch}的一个有用特点是，它不要求调用{@code countDown}的线程在继续执行之前等待计数为零，
     * 它只是阻止任何调用{@link #await await}的线程继续执行，直到所有线程都通过。
     *
     * 计时到达0时，将会开门，届时所有调用await()等待的线程都将会继续执行。
     */

    /**
     * CountDownLatch同步应用demo
     */
    class Worker implements Runnable {
        /**
         * 开始信号，计数为0开始工作
         */
        private final CountDownLatch startSignal;

        /**
         * 结束信号，计数为0所有任务完成
         */
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                // 由于主线程countDown很快，这里看到的值可能为0
                TestHelper.println("当前开始倒计时",startSignal.getCount());
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {
            } // return;
        }

        void doWork() {
            TestHelper.println("开始任务...");
        }
    }

    /**
     * author: 2025513
     *
     * 1.CountDownLatch官方demo1：控制多线程任务的开始和监控多线程任务的结束
     * 【作用】
     * 1.可以用来控制多线程任务的开始。
     *
     * 2.可以用来监控多线程任务的结束。
     *
     * 【测试结果】
     *
     *
     * 【结论】
     * 1.public long getCount()  获取当前倒计时计数
     *
     * 【优点】
     * 1.任务线程的执行不是串行化的，而是争夺式执行。
     *
     * 【缺点】
     */
    @Test
    public void demo1() {
        // 控制任务开始的倒计时门闩
        CountDownLatch startSignal = new CountDownLatch(5);
        // 控制任务进度的倒计时门闩
        CountDownLatch doneSignal = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new Worker(startSignal,doneSignal)).start();
            // 减少倒计时，当所有任务就绪后，任务开始执行
            startSignal.countDown();
        }

        TestHelper.println("等待所有任务完成.................");
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestHelper.println("任务全部完成.");


    }

    class WorkerRunnable implements Runnable {
        private final CountDownLatch doneSignal;

        // 用来标识完成任务的部分
        private final int i;

        WorkerRunnable(CountDownLatch doneSignal, int i) {
            this.doneSignal = doneSignal;
            this.i = i;
        }

        public void run() {
            // 多个线程通过getCount()获取剩余倒计时计数可能相同，因此不能使用getCount()返回值来作为任务的标识。
            TestHelper.println(Thread.currentThread().getName() + " getCount()", doneSignal.getCount());
            doWork(i);
            doneSignal.countDown();
        }

        void doWork(int part) {
            TestHelper.println(Thread.currentThread().getName(), "在处理任务-part-" + part);
        }
    }

    /**
     * author: 2025513
     *
     * 2.CountDownLatch 官方demo2：拆分任务
     * 【作用】
     * 1.将大任务分为多个子任务来执行。
     *
     * 【测试结果】
     * pool-1-thread-1: 在处理任务-part-0
     * pool-1-thread-2: 在处理任务-part-1
     * pool-1-thread-5: 在处理任务-part-4
     * pool-1-thread-6: 在处理任务-part-5
     * pool-1-thread-9: 在处理任务-part-8
     * pool-1-thread-10: 在处理任务-part-9
     * pool-1-thread-4: 在处理任务-part-3
     * pool-1-thread-7: 在处理任务-part-6
     * pool-1-thread-8: 在处理任务-part-7
     * pool-1-thread-3: 在处理任务-part-2
     * main: 子任务全部完成
     *
     *
     * pool-1-thread-1: 在处理任务-part-0
     * pool-1-thread-2: 在处理任务-part-1
     * pool-1-thread-3: 在处理任务-part-2
     * pool-1-thread-4: 在处理任务-part-3
     * pool-1-thread-7: 在处理任务-part-6
     * pool-1-thread-8: 在处理任务-part-7
     * pool-1-thread-5: 在处理任务-part-4
     * pool-1-thread-6: 在处理任务-part-5
     * pool-1-thread-9: 在处理任务-part-8
     * pool-1-thread-10: 在处理任务-part-9
     * main: 子任务全部完成
     * main: code after await()
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     * 1.子任务执行是无序的。
     */
    @Test
    public void demo2() {
        TestHelper.startTest("CountDownLatch 官方demo2：拆分任务");
        // 假设将任务拆分为10个子任务
        int taskCount = 10;

        // 任务完成倒计时门闩
        CountDownLatch doneSignal = new CountDownLatch(taskCount);
        Executor e = Executors.newFixedThreadPool(taskCount);

        // 将一个任务分成10个子任务
        // i：用来标识任务的部分
        for (int i = 0; i < taskCount; ++i) // create and start threads
            e.execute(new WorkerRunnable(doneSignal, i));

        try {
            doneSignal.await();           // wait for all to finish
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        TestHelper.println(Thread.currentThread().getName(), "子任务全部完成");

        // 如果倒计时已经到0，调用await()立即返回
        try {
            doneSignal.await();
            doneSignal.await();
            doneSignal.await();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        TestHelper.println(Thread.currentThread().getName(), "code after await()");
    }

    /**
     * author: 2025513
     *
     * 3.CountDownLatch.await()测试
     * 【作用】
     * 会一直等待倒计时门闩计数到达0才返回，除非当前线程被打断，
     * 如果当前倒计时门闩计数已经为0，则该方法立即返回
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.public boolean await(long timeout,TimeUnit unit)
     * 会等待指定的时长，超时后会直接返回
     *
     * 返回值：
     * 如果没有超时，计数为0，则返回true,否则如果超时了,返回false。
     *
     * 2.public boolean await()
     * 一直等待计数为0，返回true。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void await() {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            // 10秒后将倒计时门闩计数置0
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }).start();

        try {
            //false
            TestHelper.println("countDownLatch.await(5, TimeUnit.SECONDS)", countDownLatch.await(5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestHelper.println("code after await()");
    }
}