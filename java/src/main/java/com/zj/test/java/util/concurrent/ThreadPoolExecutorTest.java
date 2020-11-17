package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.Runner;

import java.util.concurrent.*;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/6 10:15
 * @description: ThreadPoolExecutor构造函数测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ThreadPoolExecutorTest {
    /**
     * 拒绝策略
     *
     * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize时，如果还有任务到来就会采取任务拒绝策略，通常有以下四种策略：
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
     */

    /**
     1.public ThreadPoolExecutor(int corePoolSize,
     int maximumPoolSize,
     long keepAliveTime,
     TimeUnit unit,
     BlockingQueue<Runnable> workQueue)

     参数说明：
     corePoolSize：线程池核心池线程数量。
     maximumPoolSize：线程池最大线程数量。
     keepAliveTime：线程最大空闲时间，到达空闲时间线程会被销毁，默认指的是corePoolSize外的线程。
     unit: keepAliveTime参数的时间单位。
     workQueue：任务队列。

     缺点：
     该线程池构造方法有个缺陷：
     必须队列满，然后有新的任务提交并被接受，才会创建新的线程，否则线程数一直是corePoolSize，
     而又因为该构造方法使用的默认拒绝策略为new AbortPolicy();即队列满之后不再接受任何任务。
     因此线程数永远是corePoolSize

     如果想要解决线程池不变的情况，需要修改拒绝策略，并将阻塞队列长度设定一个合理的值。
     因为默认阻塞队列长度Integer.MAX_VALUE,那样将太难填满了。
     */
    @Test
    public void threadPoolExecutor1() {
        // FixedThreadPool阻塞队列也是LinkedBlockingQueue

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 50, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000));

        for (int i = 0; i < 10000; i++) {
            threadPoolExecutor.execute(() -> {
                TestHelper.println("thread name", Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.ThreadPoolExecutor(int corePoolSize,
     *                               int maximumPoolSize,
     *                               long keepAliveTime,
     *                               TimeUnit unit,
     *                               BlockingQueue<Runnable> workQueue,
     *                               RejectedExecutionHandler handler)
     *
     * 参数说明：
     * RejectedExecutionHandler：拒绝策略，定义在ThreadPoolExecutor中。
     *
     * 测试: 线程池线程数量增加的时机
     *
     * 结论：
     * 线程池线程数量增加的时机:
     * corePoolSize没有可用的线程，并且任务队列已满。
     */
    @Test
    public void ThreadPoolExecutor2() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10000; i++) {
            threadPoolExecutor.execute(() -> {
                TestHelper.println("thread name", Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3.其他构造函数
     * 主要参数已经在前面涉及，不再说明
     *
     * public ThreadPoolExecutor(int corePoolSize,
     *                               int maximumPoolSize,
     *                               long keepAliveTime,
     *                               TimeUnit unit,
     *                               BlockingQueue<Runnable> workQueue,
     *                               ThreadFactory threadFactory,
     *                               RejectedExecutionHandler handler)
     *
     *  public ThreadPoolExecutor(int corePoolSize,
     *                               int maximumPoolSize,
     *                               long keepAliveTime,
     *                               TimeUnit unit,
     *                               BlockingQueue<Runnable> workQueue,
     *                               ThreadFactory threadFactory)
     *
     */

    /**
     * 4.keepAliveTime参数为0测试
     *
     * 测试结果：
     * 新线程: pool-1-thread-11 正在执行任务
     * 线程是否挂掉: pool-1-thread-12 正在执行任务
     * 线程是否挂掉: pool-1-thread-13 正在执行任务
     * pool-1-thread-11: 队列中的任务,正在被处理
     *
     * 结果:
     * 当keepAliveTime为0，线程一旦空闲，立即销毁。
     * 如果是其他值，达到指定的时间后，会被销毁。
     */

    @Test
    public void keepAliveTime() {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(10,
                        20,
                        0,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(1));

        // 占用核心部分线程
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 占用队列
        threadPoolExecutor.execute(() -> {
            TestHelper.println(Thread.currentThread().getName() + ": 队列中的任务,正在被处理");
        });

        // 接下来的任务会创建新线程
        threadPoolExecutor.execute(() -> {
            TestHelper.println("创建新线程: " + Thread.currentThread().getName() + ": 正在执行任务");
        });

        // 等待时间，等上一个创建的线程被销毁
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        for (int i = 0; i < 2; i++) {
            // 接下来的任务会创建新线程，看是否是上一个
            threadPoolExecutor.execute(() -> {
                TestHelper.println("线程是否挂掉: " + Thread.currentThread().getName() + " 正在执行任务");
            });
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * author: 2025513
     *
     * 5.测试：ThreadPoolExecutor初始线程池线程数量 + 是否会使用核心池空闲线程来执行新的任务 + 默认情况下核心池线程是否会过期销毁
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1、ThreadPoolExecutor被创建后且没有执行任务时，池中线程数量为0。
     * 每次执行新的任务时，不管核心池是否已经有空闲线程，都会创建新的线程来执行新任务，直到corePoolSize。
     *
     * 2.public int getPoolSize()
     * 获取当前线程池中的线程数量
     *
     * 3.默认情况下核心池线程过期不会销毁。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void allowsCoreThreadTimeOut() {
        // 设置线程空闲销毁时间为1s
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // 0
        TestHelper.println("threadPoolExecutor.getPoolSize()", threadPoolExecutor.getPoolSize());

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                TestHelper.println(Thread.currentThread().getName() + " is running...");
            });

            /*
            睡眠一会，等待核心池线程空闲，用来测试是否会使用核心池中空闲线程来执行新任务。
            结果：不会。会创建新的核心池线程来执行新任务。
            */
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 等待线程池10个线程都空闲超时
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 10
        TestHelper.println("threadPoolExecutor.getPoolSize()", threadPoolExecutor.getPoolSize());

        //threadPoolExecutor.allowCoreThreadTimeOut(true);

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * author: 2025513
     *
     * 6.public int getActiveCount()测试
     * 【作用】
     * 返回正在执行任务的线程数。
     *
     * 【测试结果】
     * 当前正在执行任务的线程数: 10
     * 当前正在执行任务的线程数: 10
     * 当前正在执行任务的线程数: 10
     * 当前正在执行任务的线程数: 9
     * 当前正在执行任务的线程数: 9
     * 当前正在执行任务的线程数: 9
     * 当前正在执行任务的线程数: 8
     * 当前正在执行任务的线程数: 8
     * 当前正在执行任务的线程数: 8
     * 当前正在执行任务的线程数: 7
     * 当前正在执行任务的线程数: 7
     * 当前正在执行任务的线程数: 7
     * 当前正在执行任务的线程数: 6
     * 当前正在执行任务的线程数: 6
     * 当前正在执行任务的线程数: 6
     * 当前正在执行任务的线程数: 5
     * 当前正在执行任务的线程数: 5
     * 当前正在执行任务的线程数: 5
     * 当前正在执行任务的线程数: 4
     * 当前正在执行任务的线程数: 4
     * 当前正在执行任务的线程数: 4
     * 当前正在执行任务的线程数: 3
     * 当前正在执行任务的线程数: 3
     * 当前正在执行任务的线程数: 3
     * 当前正在执行任务的线程数: 2
     * 当前正在执行任务的线程数: 2
     * 当前正在执行任务的线程数: 2
     * 当前正在执行任务的线程数: 1
     * 当前正在执行任务的线程数: 1
     * 当前正在执行任务的线程数: 1
     * 当前正在执行任务的线程数: 0
     * 当前正在执行任务的线程数: 0
     * 当前正在执行任务的线程数: 0
     *
     * 【结论】
     * public int getActiveCount()
     * 返回正在执行任务的线程数。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void getActiveCount() {
        // 设置线程空闲销毁时间为1s
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(finalI * 1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 打印当前正在运行的线程
        while (true) {
            TestHelper.println("当前正在执行任务的线程数", threadPoolExecutor.getActiveCount());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * author: 2025513
     *
     * 7.使用allowsCoreThreadTimeOut(true)让核心池超时销毁
     * 【作用】
     *
     * 【测试结果】
     * pool-1-thread-1: is running
     * pool-1-thread-2: is running
     * pool-1-thread-5: is running
     * pool-1-thread-6: is running
     * pool-1-thread-10: is running
     * pool-1-thread-9: is running
     * pool-1-thread-3: is running
     * pool-1-thread-4: is running
     * pool-1-thread-7: is running
     * pool-1-thread-8: is running
     * 线程池中剩余线程: 0
     *
     * 【结论】
     * 1.调用threadPoolExecutor.allowCoreThreadTimeOut(true)后，核心线程也可以超时销毁。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void allowsCoreThreadTimeOut2() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // 设置核心池线程超时过期
        threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 1; i <= 10; i++) {
            threadPoolExecutor.execute(() -> {
                TestHelper.println(Thread.currentThread().getName(), "is running");
            });
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 0
        TestHelper.println("线程池中剩余线程",threadPoolExecutor.getPoolSize());
    }
}
