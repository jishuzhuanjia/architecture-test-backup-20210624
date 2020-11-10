package com.zj.test.java.util.concurrent.locks;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/9 17:10
 * @description: 可重入锁ReentrantLock、公平锁的实现
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月9日 17:46:02
 */
public class ReentrantLockTest {

    /**
     *
     * 可重入锁: 也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。
     *
     * 1.ReentrantLock和synchronized
     * 两者都是独占锁，都可重入，加锁和解锁的过程自动进行
     * 区别：
     * 1.ReentrantLock不易操作，非常灵活。而synchronized易于操作，但是不够灵活。具体表现在
     * ReentrantLock加锁和解锁需要手动进行且次数需一致，否则其他线程无法获取锁。          // 易用性
     *
     * 2.ReentrantLock可以响应中断，而synchronized不可响应中断，一个线程获取不到锁就会一直等待。   //
     *
     * 3。ReentrantLock还可以实现公平锁机制: 等待时间长的线程将获取锁的使用权。 //功能
     */
    static int count = 0;
    Lock lock = new ReentrantLock();

    public int addAndGet() {
        int c = 0;
        try {
            lock.lock();
            //lock.lock();
            ++count;
            c = count;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            /*
            lock和unlock要成对出现，数量要箱等
            lock多: 其他线程将得不到锁
            unlock多: 报异常:
            Exception in thread "Thread-81438" java.lang.IllegalMonitorStateException
            at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
            at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
            at java.util.concurrent.locks.ReentrantLock.unlock(ReentrantLock.java:457)
            */
            lock.unlock();
        }
        return c;
    }

    /**
     * author: 2025513
     *
     * 2.测试: ReentrantLock的使用
     *
     * 结果: 最后输出20W
     *
     * 结论:
     * 达到了同步的效果
     */
    @Test
    public void test1() {
        for (int i = 0; i < 200000; i++) {
            new Thread(() -> TestHelper.println(addAndGet())).start();
        }
    }

    /**
     * 3.ReentrantLock实现公平锁
     *
     * 等待时间长的先获取锁
     */
    // 公平锁,默认构造函数是不公平锁
    Lock fairLock = new ReentrantLock(true);

    @Test
    public void test2() {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(10 * finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TestHelper.println("线程" + Thread.currentThread().getName() + "获取了锁");

                fairLock.lock();
            }, "thread" + (i + 1)).start();
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
