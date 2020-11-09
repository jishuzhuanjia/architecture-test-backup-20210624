package com.zj.test.java.util.concurrent;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/9 15:36
 * @description: synchronized关键字测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月9日 17:45:49
 */
public class SynchronizedTest {

    // 登录用户数
    private static int loginCount;

    // 增加登录数并返回 - 同步版本
    // 锁对象为SynchronizedTest.class
    public static synchronized int addAndGetLoginCount(){
        ++loginCount;
        return loginCount;
    }

    // 增加登录数并返回- 非同步版本
    public static int addAndGetLoginCount2(){
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
      */
    @Test
    public void test1(){

        for (int i = 0; i <1000 ; i++) {
            new Thread(()->{
                TestHelper.println("当前登录用户数",SynchronizedTest.addAndGetLoginCount2());

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
    public void test2(){
        for (int i = 0; i <10000 ; i++) {
            new Thread(()->{
                TestHelper.println("当前登录用户数",SynchronizedTest.addAndGetLoginCount());

            }).start();
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
