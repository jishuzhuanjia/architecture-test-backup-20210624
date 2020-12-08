package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/9 14:34
 * @description: java线程的Future模式
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年11月9日 15:00:44
 */
public class FutureTaskTest {

    /**
     * 问题引入: 之前我们等待一个线程返回结果，使用的是thread.join()方法，该方法会阻塞在这里，一直等到
     * 线程返回结果或超时，那么有没有方法可以实现异步等待呢? 答案就是Java的Future模式
     *
     * 1.线程的Future模式使用demo:
     *
     * 测试：
     * 躲猫猫时，当躲猫猫的人在找地方躲藏的时候，伙伴必须遮住眼睛并计数，计时到100的时候，躲猫猫完成，
     * 然后数数的人开始寻找已经藏好的人，我们用线程来模拟该过程。
     *
     *
     * 优点:
     * 可以以非阻塞的方式等待其他线程执行完成并获取返回的数据。
     *
     * Future模式存在的问题: 获取返回结果不及时
     * 主线程虽然不会阻塞，可以运行后面的代码，但是后续需要手动判断子线程是否已经完成，并获取返回数据。
     * 如果我们使用while(true){futureTask.isDone()...}不断判断执行状态，会大量消耗cpu性能。
     */
    @Test
    public void futureTask() {

        // 计数回调
        Callable<String> countCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {

                for (int i = 1; i <= 100; i++) {
                    // 模拟计数人计数
                    TestHelper.println(i);
                    // 为了缩短程序执行时间，50ms视为1S
                    Thread.sleep(50);
                }
                return "时间到了...你可以找我啦~";
            }
        };

        FutureTask futureTask = new FutureTask<String>(countCallable);

        // 如果此时取消，后续不会被执行
        //futureTask.cancel(false);

        // 计数线程
        // FutureTask泛型表示的是返回数据类型,而不是Callable的类型
        Thread countThread = new Thread(futureTask);
        countThread.start();

        // 模拟等待一会
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 可以执行其他的代码

        while (true) {
            if (futureTask.isDone()) {
                try {
                    // 不能尝试获取被取消任务的返回结果,否则:
                    // CancellationException
                    TestHelper.println("小伙伴喊道", futureTask.get());
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                TestHelper.println("开始寻找");
            } else {

            /*
            TestHelper.println("等一下，还没躲好呢");
            mayInterruptIfRunning:
            false: 允许运行中的线程执行完成。
            true: 立即中断执行。
            */
                TestHelper.println("等一下，还没躲好呢");
                //TestHelper.println(futureTask.cancel(false));
                // 对于cancel(true)和cancel(false),都返回了true。
                //TestHelper.println(futureTask.isDone());// true
            /*boolean cancel = futureTask.cancel(false);
            TestHelper.println("是否取消",cancel);
            TestHelper.println(futureTask.isCancelled());*/
            }

        }
    }

    /**
     * 2.while(true)性能损耗测试
     *
     * cpu占用率达到了50%，非常恐怖，会造成系统的不稳定。
     */
    @Test
    public void test2() {
        while (true) {
            TestHelper.println("趁他没躲好，我吃一口辣条...");
        }
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 3.构造函数测试
     * public FutureTask(Callable<V> callable)
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
    public void FutureTask1() {

        Callable<String> callable = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + ": finished";
        };

        FutureTask<String> futureTask = new FutureTask(callable);

        new Thread(futureTask).start();

        // 监听任务是否完成
        while (true) {

            // 判断任务是否执行完成
            if (futureTask.isDone()) {
                try {
                    TestHelper.println(futureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                TestHelper.println("任务还没执行完成");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
     * 4.构造方法测试
     * public FutureTask(Runnable runnable, V result)
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.public FutureTask(Runnable runnable, V result)
     * 参数指定任务 runnable 和 返回结果result。
     * result: 任务完成后会返回result。可通过get()方法获取。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void FutureTask2(){
        Runnable runnable = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        /*
        作为任务执行完成后的返回值, 可以为null , 通过 futureTask.get() 获取。
         */
        String result = "runnable finished...";
        FutureTask<String> futureTask = new FutureTask<String>(runnable,result);

        new Thread(futureTask).start();

        while(true){

            if(futureTask.isDone()){
                try {
                    TestHelper.println(futureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            }else
            {
                TestHelper.println("任务还没执行完成");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
