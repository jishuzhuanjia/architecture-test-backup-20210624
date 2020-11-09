package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.Callable;
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
     * Future模式存在的问题: 虽然不会阻塞，可以运行后面的代码，但是后续需要手动判断子线程是否已经完成，并
     * 获取返回数据。
     */
    @Test
    public void futureTask(){

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
                return "时间到了...";
            }
        };

        FutureTask futureTask = new FutureTask<String>(countCallable);

        // 如果此时取消，后续不会被执行
        // futureTask.cancel(false);

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

        if(futureTask.isDone()){
            TestHelper.println("开始寻找");
        }else
        {
            TestHelper.println("等一下，还没躲好呢");
            /*boolean cancel = futureTask.cancel(false);
            TestHelper.println("是否取消",cancel);
            TestHelper.println(futureTask.isCancelled());*/
        }

        TestHelper.println("趁他没躲好，我吃一口辣条...");

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
