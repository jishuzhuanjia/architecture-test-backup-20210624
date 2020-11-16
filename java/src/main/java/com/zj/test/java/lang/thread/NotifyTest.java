package com.zj.test.java.lang.thread;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/16 16:06
 * @description: object.notify()测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月16日 21:12:23
 */
public class NotifyTest {

    public synchronized void notifyTest() {
        TestHelper.println(Thread.currentThread().getName() + " is running...");

        this.notify();
        TestHelper.println(Thread.currentThread().getName() + " code after notify() invoked");

        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestHelper.println(Thread.currentThread().getName() + " code after wait() invoked");

    }

    /**
     * author: 2025513
     *
     * 1.测试：调用notify()/notifyAll()后，线程是否会立即放弃cpu资源?
     *
     * 结论: 不会
     * 1.线程调用调用notify()/notifyAll()后不会释放锁，会继续执行。
     *
     * 2.线程调用wait()会立即释放锁并释放cup资源。
     *
     */
    @Test
    public void test1() {

        new Thread(() -> {
            while (true) {
                notifyTest();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1").start();

        new Thread(() -> {
            while (true) {
                notifyTest();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2").start();

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
