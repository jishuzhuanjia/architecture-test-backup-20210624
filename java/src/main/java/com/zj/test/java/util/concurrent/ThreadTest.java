package com.zj.test.java.util.concurrent;

import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/10 14:07
 * @description: 线程Thread测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ThreadTest {

    /**
     * author: 2025513
     *
     * 1.测试: 默认单线程占用1M内存
     *
     * 创建5w个线程，一定时间内不让结束，观察内存占用情况
     *
     * 结果:
     * 5W线程在idea中占用3.2GB左右,由于idea做了内存优化的，所以没有达到5GB，但是可以明显看到线程是占用内存的。
     *
     * 结论:
     */
    @Test
    public void test1() {
        // 创建5w个线程，一定时间内不让结束，观察内存情况
        for (int i = 0; i < 50000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
