package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import com.zj.test.util.TimeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/9 15:36
 * @description: synchronized关键字测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月9日 17:45:49
 */
public class SynchronizedTest {

    Map<Object, Object> map;

    Object mapLock = new Object();

    int createCount = 0;

    /**
     * 懒汉式非同步实现
     */
    public Map<Object, Object> getMapNonSynchronzied() {

        if (null == map) {
            map = new HashMap<>();
            ++createCount;
        }

        return map;
    }

    /**
     * 懒汉式同步实现
     */
    public Map<Object, Object> getMapSynchronzied() {
        synchronized (mapLock){
            if (null == map) {
                map = new HashMap<>();
                ++createCount;
            }
        }
        return map;
    }


    // 登录用户数
    private static int loginCount;

    // 增加登录数并返回 - 同步版本
    // 锁对象为SynchronizedTest.class
    public static synchronized int addAndGetLoginCount() {
        ++loginCount;
        return loginCount;
    }

    // 增加登录数并返回- 非同步版本
    public static int addAndGetLoginCount2() {
        ++loginCount;
        return loginCount;
    }

    /**
     * author: 2025513
     *
     * 1.测试: 非synchronized方法是否会导致同步问题
     *
     * 结果: 当线程数增大时，发生同步问题的可能性越大，但是不排除低并发时的同步问题。
     *
     * 结论: 非synchronized方法可能会导致同步问题，随着时间的推移，一定会发生同步问题。
     *
     *  【出入参记录】
     *  有时同步问题不会轻易暴露出来，但随着并发个数和进程的推进，同步问题终会暴露出来，如测试当并发量为100000时，部分出参：
     * 当前登录用户数: 36530
     * 当前登录用户数: 36531
     * 当前登录用户数: 36531
     * 当前登录用户数: 36532
     */
    @Test
    public void test1() {

        int[] counts = new int[100000];

        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                int count = SynchronizedTest.addAndGetLoginCount2();
                System.out.println("当前登录用户数: " + count);

                ++counts[count - 1];
                if (counts[count - 1] > 1)
                    TestHelper.println("重复的用户数: " + count);
            }).start();
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
     * 测试: 2.同步方法解决同步问题测试
     *
     * 结果:
     *  解决了同步问题
     *
     * 结论:
     */
    @Test
    public void test2() {
        int[] counts = new int[100000];

        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                int count = SynchronizedTest.addAndGetLoginCount();
                System.out.println("当前登录用户数: " + count);

                ++counts[count - 1];
                if (counts[count - 1] > 1)
                    TestHelper.println("重复的用户数: " + count);
            }).start();
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     3.测试: 懒汉式非安全实现同步问题
     * </p>
     *
     * 【出入参记录】
     * [main] - TimeHelper: [main] 开始执行任务...
     * [main] - TimeHelper: [main] 执行任务完成,共用时: 1079ms.
     * [main] - 懒汉式创建map次数: 2
     *
     * 【结论】
     *
     * 【注意点】
     *
     */
    @Test
    public void getMapNonSynchronziedTest() {
        TimeHelper.start();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                getMapNonSynchronzied();
            }).start();
        }
        TimeHelper.finish();

        TestHelper.println("懒汉式创建map次数", createCount);

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>
     *     4.测试: 饿汉式同步解决测试
     * </p>
     *
     * 【出入参记录】
     *
     * 【结论】
     * map只创建了一次
     *
     * 【注意点】
     *
     */
    @Test
    public void getMapSynchronziedTest(){

        TimeHelper.start();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                getMapSynchronzied();
            }).start();
        }

        TimeHelper.finish();
        TestHelper.println("懒汉式同步实现创建map次数", createCount);

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
