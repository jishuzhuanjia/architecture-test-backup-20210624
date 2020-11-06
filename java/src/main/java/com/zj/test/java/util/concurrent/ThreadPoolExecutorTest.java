package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.Runner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    /**1.
     public ThreadPoolExecutor(int corePoolSize,
     int maximumPoolSize,
     long keepAliveTime,
     TimeUnit unit,
     BlockingQueue<Runnable> workQueue)

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
     * 测试: 线程池线程数量增加的时机
     *
     * 结论：
     * 线程池线程数量增加的时机:
     * corePoolSize没有可用的线程，并且阻塞队列已满
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
     * 主要参数已经在前面涉及，不再测试
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
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1));

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

        //接下来的任务会创建新线程
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
            //接下来的任务会创建新线程，看是否是上一个
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
}
