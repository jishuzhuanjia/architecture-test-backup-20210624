/**
 * Test.java
 * Creation time:2018年12月11日 上午9:53:18
 * Author:zhoujian
 * QQ:2025513
 * e-mail:www.mfcdebugger@163.com
 */

package com.zj.test.java.lang.thread;

import com.zj.test.util.TestHelper;

/**
 * 线程join()方法测试
 */
public class JoinTest {

    public static void main(String[] args) {
        // 子任务线程
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 1000; i++) {
                    TestHelper.println("subThread进度", i/(double)1000);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        subThread.start();

        /*try {
         *//*在一个线程中调用另外一个线程对象的join方法
            实际是通过thread.isAlive() 和 wait()来实现
            推荐不要对线程实例使用wait(),notify 和 notifyAll();
            如果线程没有调用start()则无操作。
            如果线程已经开始,等待指定时间后会继续执行后面的代码
            单位  ms
            1ms = 1000000ns
            *//*
            subThread.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /**
         join()
         会等待指定的线程执行完成

         永久等待：
         subThread.join(0);
         或
         subThread.join();

         等待指定的时间(ms)：
         subThread.join(1000);
         如果指定的时间内指定线程没有执行完成，会继续执行后面的代码。
         指定的线程会继续执行，不会被强制停止。
         */
        try {
            subThread.join(1000,999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("mainThread继续执行");

        /*
        死亡状态(执行完)的线程不能重新开始
        否则Exception in thread "main" java.lang.IllegalThreadStateException
        */

        /*
        如果start正在运行状态的线程:
        Exception in thread "main" java.lang.IllegalThreadStateException
        at java.lang.Thread.start(Thread.java:708)
        at com.zj.test.java.lang.thread.JoinTest.main(JoinTest.java:74)
        */
    }
}