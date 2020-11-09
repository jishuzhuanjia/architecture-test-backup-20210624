package com.zj.test.java.lang.thread;

import com.zj.test.util.TestHelper;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/9 9:34
 * @description: Thread Main测试: 主线程结束，子线程是否会继续运行?
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ThreadMainTest {
    /**
      * author: 2025513
      *
      * 测试: 主线程结束，子线程是否会继续运行?
      *
      * 结果:
      *
      * 结论:
     * 主线程结束，子线程继续运行。
      */
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                TestHelper.println("sub thread is running");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        TestHelper.println("main thread is finished");
    }
}
