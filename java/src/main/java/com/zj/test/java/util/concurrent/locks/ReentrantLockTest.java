package com.zj.test.java.util.concurrent.locks;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/9 17:10
 * @description: 可重入锁ReentrantLock、公平锁的实现
 * @finished: 1
 * @finished-time: 2020年11月9日 17:46:02
 *
 *
 * 可重入锁: 也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。
 *
 * 1.ReentrantLock和synchronized
 * 两者都是独占锁，都可重入，加锁和解锁的过程自动进行
 * 区别：
 * 1.ReentrantLock不易操作，位置非常灵活。
 * 而synchronized易于操作，但是不够灵活。具体表现在ReentrantLock加锁lock和解锁unlock需要手动进行且次数需一致，
 * 否则其他线程无法获取锁。更为致命的问题是java中每个线程都会占用一定的线程栈,如果它们一直都无法得到锁，任务无法结束, 则会一直占用内存空间,
 * 严重时直接导致jvm内存耗尽。
 * 而synchronized加锁和解锁的过程是自动的。
 * 2.ReentrantLock可以响应中断，而synchronized不可响应中断，一个线程获取不到锁就会一直等待。
 * 3.ReentrantLock还可以实现公平锁机制: 等待时间长的线程优先获得锁。
 */
public class ReentrantLockTest {

    static int count = 0;
    Lock lock = new ReentrantLock();

    public int addAndGet() {
        int c = 0;
        try {
            lock.lock();
            lock.lock();
            ++count;
            c = count;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
            可重入锁可以多次调用lock()和unlock(),必须保证占用锁和释放锁调用次数一致,否则:
            lock次数大于unlock -> 会导致其他线程得不到锁。更为致命的问题是java中每个线程都会占用一定的线程栈,
            如果它们一直都无法得到锁，任务无法结束, 则会一直占用内存空间, 严重时直接导致jvm内存耗尽。

            如果unlock次数大于lock -> 报异常:
            Exception in thread "Thread-81438" java.lang.IllegalMonitorStateException
            at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
            at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
            at java.util.concurrent.locks.ReentrantLock.unlock(ReentrantLock.java:457)
            如果不显示捕获异常，则代码中断。
             */
            //
            lock.unlock();
            lock.unlock();
        }
        return c;
    }

    /**
     * author: 2025513
     *
     * 2.测试: ReentrantLock的使用(非公平锁)
     *
     * 输出：
     * [Thread-13] - 结果: 14
     * [Thread-0] - 结果: 1
     * [Thread-11] - 结果: 12
     * [Thread-4] - 结果: 5
     * [Thread-9] - 结果: 10
     * [Thread-1] - 结果: 2
     * [Thread-7] - 结果: 8
     * [Thread-2] - 结果: 3
     * [Thread-8] - 结果: 9
     * [Thread-5] - 结果: 6
     * [Thread-12] - 结果: 13
     * [Thread-3] - 结果: 4
     * [Thread-10] - 结果: 11
     * [Thread-6] - 结果: 7
     * [Thread-19] - 结果: 20
     * [Thread-18] - 结果: 19
     * [Thread-17] - 结果: 18
     * [Thread-21] - 结果: 22
     * 可以看出任务不是按顺序执行的，这就是非公平锁的特征。
     */
    @Test
    public void unfairLockTest() {
        for (int i = 0; i < 10000; i++) {
            // 默认线程名格式: Thread-N(N从0开始)
            new Thread(() -> {
                TestHelper.println("结果", addAndGet());
                try {
                    Thread.sleep(100);
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


    // 公平锁,默认构造函数是不公平锁
    Lock fairLock = new ReentrantLock(true);

    /**
     * 3.ReentrantLock实现公平锁
     *
     * 【结论】
     * 1.等待时间长的先获取锁，即最先调用lock()的线程优先获取锁。
     * 2.thread.start()调用的顺序不一定等于获取锁的顺序，锁的获取顺序与实际调用lock方法的顺序有关。
     * 3.不要忘记释放锁，否则其他线程获取不到锁。
     */
    @Test
    public void fairLockTest() {

        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            new Thread(() -> {
                fairLock.lock();
                TestHelper.println("获取了锁");
                fairLock.unlock();

            }, "thread" + (i + 1)).start();

            // 确保先提交的任务首先调用fairLock.lock()
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
