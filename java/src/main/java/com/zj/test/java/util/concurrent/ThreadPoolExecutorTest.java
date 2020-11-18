package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import com.zj.test.util.ZJSwingUtilities;
import org.junit.Test;
import org.junit.runner.Runner;

import javax.swing.*;
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
     workQueue：存储任务的队列。

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
     * 2.测试
     * 线程池线程数量增加的时机
     *
     * ThreadPoolExecutor(int corePoolSize,
     *                               int maximumPoolSize,
     *                               long keepAliveTime,
     *                               TimeUnit unit,
     *                               BlockingQueue<Runnable> workQueue,
     *                               RejectedExecutionHandler handler)
     *
     * 参数说明：
     * RejectedExecutionHandler：拒绝策略，定义在ThreadPoolExecutor中。
     *
     *
     *
     * 【结论】
     * 线程池线程数量增加的时机:
     * corePoolSize中没有可用的空闲线程，并且任务队列已满时。
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
     * 4.keepAliveTime参数为0时效果测试
     *
     * 【测试结果】
     * 新线程: pool-1-thread-11 正在执行任务
     * 线程是否挂掉: pool-1-thread-12 正在执行任务
     * 线程是否挂掉: pool-1-thread-13 正在执行任务
     * pool-1-thread-11: 队列中的任务,正在被处理
     *
     * 【结论】
     * 当keepAliveTime为0，线程一旦空闲，立即销毁。
     * 如果keepAliveTime是其他非0值，达到指定的时间后，会被销毁。
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
     * 5.测试：
     * 1.ThreadPoolExecutor完成构造时线程池线程数量?
     *
     * 2.核心池线程数是如何增长的?
     *
     * 3.默认情况下核心池线程是否会过期销毁?
     *
     * 4.如何获取线程池中线程的数量?
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1、ThreadPoolExecutor对象在完成构造，且还没有开始执行任务时，线程池中线程数量为0。
     *
     * 2.当有新的任务到达时，且线程池线程数 < corePoolSize，不管核心池是否有空闲线程，都会创建新的线程来执行新任务，
     * 直到corePoolSize。
     * 默认情况下，核心池中corePoolSize个线程不会超时销毁，会一直存在。
     *
     * 3.默认情况下核心池线程空闲超时不会销毁。
     *
     * 4.public int getPoolSize()
     * 获取当前线程池中的线程数量
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
     * 返回正在执行任务的线程大致数量。
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
     * 返回正在执行任务的线程大致数量。
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
     * 7.使用allowsCoreThreadTimeOut(true)让核心池线程空闲超时销毁
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
     * 1.调用threadPoolExecutor.allowCoreThreadTimeOut(true)后，核心线程空闲超时也会被销毁。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void allowsCoreThreadTimeOut2() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // 设置允许核心池线程超时过期
        threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 1; i <= 10; i++) {
            threadPoolExecutor.execute(() -> {
                TestHelper.println(Thread.currentThread().getName(), "is running");
            });
        }

        // 睡眠5s，等待核心池线程空闲超时
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 0
        TestHelper.println("线程池中剩余线程", threadPoolExecutor.getPoolSize());


    }

    /**
     * author: 2025513 2020年11月17日 23:02:18
     *
     * 8.api测试: public long getCompletedTaskCount()
     *
     * 【作用】
     * 返回已完成执行的任务的大致总数。那是因为任务和线程的状态可能在计算期间动态变化，
     * 所以返回值只是一个近似值，但在连续调用时值不会减少。
     *
     * 【测试结果】
     * 当前已经完成的任务数: 0
     * 当前已经完成的任务数: 0
     * 当前已经完成的任务数: 0
     * 当前已经完成的任务数: 1
     * 当前已经完成的任务数: 1
     * 当前已经完成的任务数: 1
     * 当前已经完成的任务数: 2
     * 当前已经完成的任务数: 2
     * 当前已经完成的任务数: 2
     * 当前已经完成的任务数: 3
     * 当前已经完成的任务数: 3
     * 当前已经完成的任务数: 3
     * 当前已经完成的任务数: 4
     * 当前已经完成的任务数: 4
     * 当前已经完成的任务数: 4
     * 当前已经完成的任务数: 5
     * 当前已经完成的任务数: 5
     * 当前已经完成的任务数: 5
     * 当前已经完成的任务数: 6
     * 当前已经完成的任务数: 6
     * 当前已经完成的任务数: 6
     * 当前已经完成的任务数: 7
     * 当前已经完成的任务数: 7
     * 当前已经完成的任务数: 7
     * 当前已经完成的任务数: 8
     * 当前已经完成的任务数: 8
     * 当前已经完成的任务数: 8
     * 当前已经完成的任务数: 9
     * 当前已经完成的任务数: 9
     * 当前已经完成的任务数: 9
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     * 当前已经完成的任务数: 10
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void getCompletedTaskCount() {
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


        // 打印当前已经完成的任务数
        while (true) {
            TestHelper.println("当前已经完成的任务数", threadPoolExecutor.getCompletedTaskCount());

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
     * 9.api测试
     * public int getCorePoolSize()
     *
     * 【作用】
     * 返回corePoolSize参数,与核心池中实际存在的线程数无关，值等于构造ThreadPoolExecutor对象时传递的corePoolSize参数。
     *
     * 【测试结果】
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void getCorePoolSize() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // 10
        TestHelper.println("getCorePoolSize()", threadPoolExecutor.getCorePoolSize());

        for (int i = 0; i < 15; i++) {

            threadPoolExecutor.execute(() -> {
                try {
                    new CountDownLatch(1).await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 10
        TestHelper.println("getPoolSize()", threadPoolExecutor.getPoolSize());
        // 10
        TestHelper.println("getCorePoolSize()", threadPoolExecutor.getCorePoolSize());

    }

    /**
     * author: 2025513
     *
     * 10.api测试
     * public void setCorePoolSize(int corePoolSize)
     *
     * 1.验证：新corePoolSize如果比当前corePoolSize小，当核心线程空闲时，是否会从当前核心线程中销毁多余的线程。
     *
     * 【作用】
     * 设置线程的核心数量。这将覆盖构造函数中设置的任何值。如果新值小于当前值，多余的现有线程将在下一次空闲时终止。
     * 如果较大，则需要启动新线程来执行任何排队的任务。
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.新corePoolSize如果比当前corePoolSize小，当核心线程空闲时，会从当前核心线程中销毁多余的线程。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void setCorePoolSize() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // 提交任务，让ThreadPoolExecutor创建corePoolSize个线程。
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                TestHelper.println(Thread.currentThread().getName() + "当前线程池线程数量", threadPoolExecutor.getPoolSize());
                TestHelper.println(Thread.currentThread().getName() + " is running...");
            });

        }

        TestHelper.println("当前线程池线程数量", threadPoolExecutor.getPoolSize());

        TestHelper.println("将corePoolSize设置为8.");
        // 8
        threadPoolExecutor.setCorePoolSize(8);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestHelper.println("当前线程池线程数量", threadPoolExecutor.getPoolSize());

    }

    /**
     * author: 2025513
     *
     * 11.api测试
     * public void setCorePoolSize(int corePoolSize)
     *
     * 1.验证：新corePoolSize如果比当前corePoolSize大，队列未满时，是否会创建核心线程来执行队列任务？
     *
     * 【作用】
     * 设置线程的核心数量。这将覆盖构造函数中设置的任何值。如果新值小于当前值，多余的现有线程将在下一次空闲时终止。
     * 如果较大，则需要启动新线程来执行任何排队的任务。
     *
     * 【测试结果】
     * 当前线程池线程数量: 10
     * 将corePoolSize设置为12.
     * 当前线程池线程数量: 12
     *
     * 【结论】
     * 1.新corePoolSize如果比当前corePoolSize大，
     * 如果队列中有任务，则会创建核心池线程来执行队列中的任务。
     * 如果提交新的任务，则会创建核心池线程来执行新的任务。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void setCorePoolSize2() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // 提交任务,10个任务占用corePoolSize
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                TestHelper.println(Thread.currentThread().getName() + " is running...");
                try {
                    new CountDownLatch(1).await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TestHelper.println("当前线程池线程数量", threadPoolExecutor.getPoolSize());

        TestHelper.println("将corePoolSize设置为12.");
        threadPoolExecutor.setCorePoolSize(12);

        for (int i = 0; i < 2; i++) {
            threadPoolExecutor.execute(() -> {
                TestHelper.println(Thread.currentThread().getName() + " is running...");
            });
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestHelper.println("当前线程池线程数量", threadPoolExecutor.getPoolSize());
    }

    /**
     * author: 2025513
     *
     * 12.api测试
     * public void shutdown()
     *
     * 1.调用shutdown()后，是否会完成之前已经提交的任务？
     *
     * 【作用】
     * 关闭线程池，不再接受新的任务，已经接受的任务会执行完成。
     * 该方法会立即返回，不会等待已经接受的任务执行完成。
     * 如果想要等待已经接受的任务执行完成，使用awaitTerminate(..)
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.是，会将已提交的任务执行完成。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void shutdown() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TestHelper.println(Thread.currentThread().getName() + " is running...");
            });
        }

        threadPoolExecutor.shutdown();
        threadPoolExecutor.shutdown();
        threadPoolExecutor.shutdown();

        TestHelper.println("当前线程池是否已经关闭", threadPoolExecutor.isShutdown());

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * author: 2025513
     *
     * 13.api测试
     * public boolean awaitTermination(long timeout,
     *                                 java.util.concurrent.TimeUnit unit)
     *
     * 【作用】
     * 会阻塞等待已经调用shutdown()的线程池执行完所有的任务，如果在指定的时间内完成，返回true，否则返回false。
     * 如果timeout设置为0，则该方法会立即返回。
     *
     * 注意：需要和shotdown()一起使用，因为awaitTermination不会关闭线程池。
     * 如果单独使用，不管线程池中任务是否完成，总是阻塞等到timeout才会返回。
     *
     * 【测试结果】
     * pool-1-thread-7 is running...
     * pool-1-thread-3 is running...
     * pool-1-thread-5 is running...
     * pool-1-thread-1 is running...
     * pool-1-thread-10 is running...
     * pool-1-thread-9 is running...
     * pool-1-thread-2 is running...
     * pool-1-thread-6 is running...
     * pool-1-thread-4 is running...
     * pool-1-thread-8 is running...
     * 已提交任务是否完成: true
     * 当前线程池是否已经关闭: true
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void awaitTermination(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TestHelper.println(Thread.currentThread().getName() + " is running...");
            });
        }

        threadPoolExecutor.shutdown();

        try {
            boolean b = threadPoolExecutor.awaitTermination(6, TimeUnit.SECONDS);
            TestHelper.println("已提交任务是否完成",b);
            TestHelper.println("当前线程池是否已经关闭", threadPoolExecutor.isShutdown());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        JFrame hello = ZJSwingUtilities.createTestJFrame("hello", 500, 800);
        hello.setVisible(true);
    }
}
