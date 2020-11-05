package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.*;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/5 16:31
 * @description: java线程池测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ThreadPoolTest {

    /**
     * 1.ExecutorService api
     *
     * 1.1.execute(@NotNull Runnable command)       执行指定的任务，不能返回数据
     *
     * 1.2.提交一个任务到到线程池,并获取返回数据,有以下几个方法:
     * <T> Future<T> submit(Callable<T> task);
     * 如果在指定的时间内返回,可以返回指定类型的数据，并且可以自定义返回数据。
     *
     * Future<?> submit(Runnable task);
     * 如果在指定时间内返回，则返回的数据只能是null
     *
     * <T> Future<T> submit(Runnable task, T result);
     * 如果在指定时间内返回，则返回的数据只能是调用时传递的result。因此返回数据是固定值。
     */

    /**
     * 2.不同类型线程池的测试
     * 2.1.FixedThreadPool
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     *
     * <p>
     * 一个任务在使用线程池中线程执行时,如果没有执行完成,不会切换线程，会一直占用线程执行
     * <p>
     * <p>
     * 也是提交一个任务到线程池，不同的是可以获取返回值。
     * 等待结果的过程相当于join。
     */
    @Test
    public void fixedThreadPool() {
        //ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        //int count = 0;
        // nThreads为多少，就会调用多少次threadFactory.newThread()
        // 这里不能实现对线程名的编号,因为不使用lambda来实现ThreadFactory接口。
        /*
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10, (r) -> {

            Thread thread = new Thread(r);
            thread.setName("fixed-thread_pool-" + count);
            // Variable used in lambda expression should be final or effectively final

            //count ++;
            return thread;
        });
        */

        int[] count = {0};
        //使用内部类来为线程指定带有序号的线程名
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("fixed-thread-pool-thread-" + count[0]);
                count[0]++;

                return thread;
            }
        });

        // 提交一个任务
        fixedThreadPool.execute(() -> {
            while (true) {
                TestHelper.println("execute task-1" + Thread.currentThread().getName() + "  is running,by execute");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            fixedThreadPool.submit(() -> {
                while (true) {
                    TestHelper.println("submit task-" + (finalI + 1) + Thread.currentThread().getName() + "  is running,by submit");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // submit执行任务并返回返回值demo
        Future<Integer> submit = fixedThreadPool.submit(() -> {
            int i = 1314 * 520;
            Thread.sleep(10000);
            return i;
        });

        try {
            // 会等待线程返回值, 会阻塞在这里,相当于join
            // 无参,永久等待
            //TestHelper.println("线程返回值: " + submit.get());

            // 到达timeout时间, 线程没有返回值, 会打断等待状态, 继续执行后面的代码
            // 0: 立即打断,执行后面的代码
            TestHelper.println("线程1返回值: " + submit.get(0, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        TestHelper.println("线程1返回值后，执行的代码");

        // Future<?> submit(Runnable task)测试
        // 传递Runnable
        // 和Callable<T>参数不同的是,如果指定时间内返回了,则future.get()总是为null
        Future<?> future = fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        try {
            TestHelper.println("submit(Runnable task)返回数据 " + future.get(2, TimeUnit.SECONDS));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        TestHelper.println("code after submit(Runnable task)返回数据");

        // <T> Future<T> submit(Runnable task, T result)测试
        // return: 当执行时间内线程执行完成, 会作为返回数据。
        Future<Object> submit1 = fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, null);

        try {
            TestHelper.println("submit(Runnable task, T result)返回数据: " + submit1.get(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        TestHelper.println("code after submit(Runnable task, T result)返回数据");


        // 防止主线程结束
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * author: 2025513
     * 测试: 补充测试newFixedThreadPool线程池在线程执行失败时,是否会有新的线程替代失败的线程继续执行下一个任务
     * <p>
     * 结果:
     * 一个即将发生错误的线程,正在执行...
     * 线程名: pool-1-thread-1
     * Exception in thread "pool-1-thread-1" java.lang.NullPointerException
     * at java.util.Objects.requireNonNull(Objects.java:203)
     * at com.zj.test.java.util.concurrent.ThreadPoolTest.lambda$fixedThreadPool2$3(ThreadPoolTest.java:206)
     * at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
     * at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
     * at java.lang.Thread.run(Thread.java:748)
     * 这是新的任务...
     * 当前处理线程名: pool-1-thread-2
     * <p>
     * 结论:
     * newFixedThreadPool线程池任务处理失败时,会创建新的线程替代失败的线程执行新的任务。
     */
    @Test
    public void fixedThreadPool2() {

        // 线程数为1的线程，便于测试
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // 抛出一个异常来模拟测试失败
        executorService.execute(() -> {
            TestHelper.println("一个即将发生错误的线程,正在执行...");
            TestHelper.println("线程名: " + Thread.currentThread().getName());
            Objects.requireNonNull(null);
        });

        // 确保线程池线程错误已经发生
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 执行新的任务
        executorService.execute(() -> {
            TestHelper.println("这是新的任务...");
            TestHelper.println("当前处理线程名: " + Thread.currentThread().getName());
        });
    }

    /**
     * 测试newFixedThreadPool线程阻塞队列大小是否为Integer.MAX_VALUE
     * <p>
     * 结论:
     * 应该是的，因为我测试加入了1000W+个任务, 没有发生任何问题。因为Integer.MAX_VALUE最大值是21亿，我这里就不等待了。
     */
    @Test
    public void fixedThreadPool3() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    TestHelper.println("task " + finalI + " is processing");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            TestHelper.println("当前已经向线程池添加: " + (i + 1) + "个任务");
        }
    }

    /**
     * 2.2.CachedThreadPool
     * <p>
     * ExecutorService newCachedThreadPool()
     * 创建一个线程池，根据需要创建新线程，但在可用时重用之前构造的线程。这些池通常会提高执行许多短期异步任务的程序的性能。
     * 如果可用，对execute的调用将重用先前构造的线程。如果没有可用的现有线程，将创建一个新线程并添加到池中。
     * 未使用60秒的线程将被终止并从缓存中删除。因此，空闲达到60秒的线程将不会消耗任何资源。
     * 注意，可以使用ThreadPoolExecutor构造函数创建具有类似属性但细节不同(例如，超时参数)的池。
     * <p>
     * 测试: newCachedThreadPool()是否会动态创建线程:
     * <p>
     * 结果:
     * 是的, 添加1000个长时耗任务, 结果创建了1000个线程。
     */
    @Test
    public void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 添加1000个任务，每个任务都是长时任务,查看创建多少个线程
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    TestHelper.println("当前线程: " + Thread.currentThread().getName() + "...");
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    TestHelper.println("当前线程: " + Thread.currentThread().getName() + "执行完成...");
                }
            });
        }
    }

    /**
     * 2.3.ScheduledThreadPool
     * <p>
     * ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
     * 创建一个线程池，该线程池可以安排命令在给定的延迟后运行或定期执行。
     * <p>
     * 和FixedThreadPool一样，线程数不变,即使线程是空闲的，不同的是ScheduledThreadPool可以执行延迟，定时的任务。
     */
    @Test
    public void newScheduledThreadPool() {
        // corePoolSize—保留在池中的线程数，即使它们是空闲的
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        // 创建延迟2秒执行的任务
        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                TestHelper.println("这个任务延迟了2秒执行...");
            }
        }, 2, TimeUnit.SECONDS);

        try {
            // 在计算等待超时的时候, delay的时间也被算在timeout中
            TestHelper.println("线程返回的数据: " + schedule.get(2100, TimeUnit.MILLISECONDS));// 线程返回的数据: null
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        //防止主线程退出
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ScheduledThreadPool周期任务demo
     * <p>
     * scheduleAtFixedRate      固定周期执行，当任务耗时大于period时，任务周期>period
     * scheduleWithFixedDelay   固定延迟执行，每次任务执行间隔都是相等的。
     */
    @Test
    public void newScheduledThreadPool2() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        // initialDelay: 第一次执行的延迟
        // period: 之后任务执行的间隔
        /*
        创建并执行一个周期动作，该动作在给定的初始延迟后首先启用，随后在给定的周期内启用;也就是说，
        执行将在initialDelay之后开始，然后是initialDelay+period，然后是initialDelay+ 2 * period，以此类推。
        如果任务的任何执行遇到异常，则禁止后续执行。否则，任务将仅通过执行程序的取消或终止而终止。
        如果此任务的任何执行花费的时间超过其周期，则后续执行可能会延迟开始，但不会并发执行。

        即: 当任务耗时大于period周期时,任务的间隔将不是period
        并且不会并发执行
        当任务耗时小于period，会等待到period才会执行下一个任务, 这样就使得任务像是周期执行。
        */
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                TestHelper.println("周期代码执行了...");
            }
        }, 5, 2, TimeUnit.SECONDS);

        /*
        创建并执行一个周期性操作，该操作在给定的初始延迟之后首先启用，然后在一个执行的终止和下一个执行的开始之间使用给定的延迟。
        如果任务的任何执行遇到异常，则禁止后续执行。否则，任务将仅通过执行程序的取消或终止而终止。

        即: 多个任务之间的间隔是固定的，会等待上一个线程执行完成,等待delay后执行下一个任务。
        */
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                TestHelper.println("周期代码2执行了...");

            }
        }, 1, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
