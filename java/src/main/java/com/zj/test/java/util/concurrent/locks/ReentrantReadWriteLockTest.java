package com.zj.test.java.util.concurrent.locks;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * <p>
 * 可重入读写锁
 * </p>
 *
 * @author: zhou jian
 * @create-time: 2021/5/13 15:10
 * @finished: false
 * @finished-time:
 */
public class ReentrantReadWriteLockTest {

    //
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    /**
     * 读锁
     */
    private static ReadLock readLock = reentrantReadWriteLock.readLock();

    /**
     * 写锁
     */
    private static WriteLock writeLock = reentrantReadWriteLock.writeLock();

    /**
     * 读操作
     */
    public static void read() {
        try {
            readLock.lock();
            TestHelper.println("正在读...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写操作
     */
    public static void write() {
        try {
            writeLock.lock();
            TestHelper.println("正在写...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 多线程读锁测试
     *
     * 【测试输出】
     * [Thread-0] - 正在读...
     * [Thread-3] - 正在读...
     * [Thread-2] - 正在读...
     * [Thread-1] - 正在读...
     * [Thread-7] - 正在读...
     * [Thread-5] - 正在读...
     * [Thread-4] - 正在读...
     * [Thread-6] - 正在读...
     * [Thread-8] - 正在读...
     * [Thread-9] - 正在读...
     * [Thread-11] - 正在读...
     * [Thread-10] - 正在读...
     * [Thread-12] - 正在读...
     * [Thread-13] - 正在读...
     * [Thread-14] - 正在读...
     * [Thread-15] - 正在读...
     * [Thread-16] - 正在读...
     * [Thread-18] - 正在读...
     * [Thread-17] - 正在读...
     * [Thread-19] - 正在读...
     * [Thread-20] - 正在读...
     * [Thread-21] - 正在读...
     * [Thread-24] - 正在读...
     * [Thread-22] - 正在读...
     * [Thread-23] - 正在读...
     * [Thread-25] - 正在读...
     * [Thread-27] - 正在读...
     * [Thread-29] - 正在读...
     * [Thread-28] - 正在读...
     * [Thread-32] - 正在读...
     * [Thread-30] - 正在读...
     * [Thread-31] - 正在读...
     * [Thread-26] - 正在读...
     * [Thread-33] - 正在读...
     * [Thread-34] - 正在读...
     * [Thread-35] - 正在读...
     * [Thread-38] - 正在读...
     * [Thread-39] - 正在读...
     * [Thread-36] - 正在读...
     * [Thread-37] - 正在读...
     * [Thread-41] - 正在读...
     * [Thread-48] - 正在读...
     * [Thread-44] - 正在读...
     * [Thread-40] - 正在读...
     * [Thread-47] - 正在读...
     * [Thread-42] - 正在读...
     * [Thread-46] - 正在读...
     * [Thread-43] - 正在读...
     * [Thread-45] - 正在读...
     * [Thread-49] - 正在读...
     * [Thread-52] - 正在读...
     * [Thread-53] - 正在读...
     * [Thread-50] - 正在读...
     * [Thread-56] - 正在读...
     * [Thread-51] - 正在读...
     * [Thread-70] - 正在读...
     * [Thread-76] - 正在读...
     * [Thread-60] - 正在读...
     * [Thread-57] - 正在读...
     * [Thread-61] - 正在读...
     * [Thread-80] - 正在读...
     * [Thread-59] - 正在读...
     * [Thread-63] - 正在读...
     * [Thread-91] - 正在读...
     * [Thread-64] - 正在读...
     * [Thread-65] - 正在读...
     * [Thread-66] - 正在读...
     * [Thread-68] - 正在读...
     * [Thread-69] - 正在读...
     * [Thread-72] - 正在读...
     * [Thread-73] - 正在读...
     * [Thread-67] - 正在读...
     * [Thread-54] - 正在读...
     * [Thread-71] - 正在读...
     * [Thread-74] - 正在读...
     * [Thread-75] - 正在读...
     * [Thread-55] - 正在读...
     * [Thread-77] - 正在读...
     * [Thread-78] - 正在读...
     * [Thread-79] - 正在读...
     * [Thread-81] - 正在读...
     * [Thread-58] - 正在读...
     * [Thread-82] - 正在读...
     * [Thread-83] - 正在读...
     * [Thread-84] - 正在读...
     * [Thread-85] - 正在读...
     * [Thread-88] - 正在读...
     * [Thread-89] - 正在读...
     * [Thread-92] - 正在读...
     * [Thread-93] - 正在读...
     * [Thread-97] - 正在读...
     * [Thread-96] - 正在读...
     * [Thread-86] - 正在读...
     * [Thread-87] - 正在读...
     * [Thread-90] - 正在读...
     * [Thread-62] - 正在读...
     * [Thread-94] - 正在读...
     * [Thread-95] - 正在读...
     * [Thread-98] - 正在读...
     * [Thread-99] - 正在读...
     * 是在瞬间完成的
     *
     *
     * 【结论】
     * 1.读读共享：由于读是共享的，不需要等待其他读线程释放锁。
     * 同一时间可以多个线程一同写
     */
    @Test
    public void readersTest() {
        // 非公平锁，是抢夺式的
        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                read();
            }).start();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 多线程写锁测试
     *
     * 【结论】
     * 写写互斥，必须等待其他写线程释放锁
     * 同一时间只能有一个线程写
     */
    @Test
    public void writersTest() {
        // 非公平锁，是抢夺式的
        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                write();
            }).start();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读写线程测试1：有线程正在写，其他线程是否可读？
     *
     * 【结论】
     * 不可读，有线程在写时，读线程会阻塞。
     */
    @Test
    public void readersAndWritesTest() {

        // 1.线程A：写

        new Thread(() -> {
            write();
        }).start();

        // 2.保证A线程已经写lock
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3.线程B：读
        new Thread(() -> {
            read();
        }).start();

        new Thread(() -> {
            read();
        }).start();

        new Thread(() -> {
            read();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当读锁和写锁同时空闲时，此时有一个读线程和写线程请求锁，
     * 那么是先开始读操作还是写操作？
     *
     * 【测试结论】
     * 取决于哪个线程先lock。
     */
    @Test
    public void readersAndWritesTest2() {

        // 1.线程A：写

        new Thread(() -> {
            write();
        }).start();

        // 2.保证A线程已经写lock
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3.线程B：读
        new Thread(() -> {
            read();
        }).start();

        new Thread(() -> {
            write();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读操作内部进行写操作
     *
     * 虽然可重入，但是读操作内部写操作无法获取到锁，导致线程阻塞在这里。
     */
    public static void readWithWrite() {
        try {
            readLock.lock();
            TestHelper.println("正在读...");

            writeLock.lock();
            TestHelper.println("读操作正在写...");
            writeLock.unlock();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试：读操作内部是否可以进行写操作？
     *
     * 【结论】
     * 不可以，虽然可重入，但是读操作内部无法获取到写锁，导致该线程阻塞，并使得后续的第一个写操作阻塞，
     * 而该写操作的阻塞也会导致后续的所有读写线程全部阻塞。
     *
     * 因此必须避免读操作内部进行写操作。
     */
    @Test
    public void readWithWriteTest() {
        new Thread(()->{
            readWithWrite();
        }).start();

        // 线程2
        /*new Thread(()->{
            write();
        }).start();*/

        // 线程3
        // 如果线程2在线程3之前，线程2无法获取写锁，导致线程3无法获取读锁
        // 但是，如果线程3读操作在线程2之前，是可以进行读操作的。
        new Thread(()->{
            read();
        }).start();

        new Thread(()->{
            write();
        }).start();

        new Thread(()->{
            read();
        }).start();



        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 写操作内部进行读操作
     */
    public static void writeWithRead() {
        try {
            writeLock.lock();
            TestHelper.println("正在写...");

            readLock.lock();
            TestHelper.println("写操作正在读...");
            //readLock.unlock();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 【锁降级测试】
     * 线程获取读锁条件测试：本线程获取写锁是否可以获取读锁
     *
     * 【结论】
     * 1.获取写锁的线程可以继续获取读锁，以降级。但是必须等待写线程放弃锁，别的读线程才能继续读。
     *
     * 2.写锁内部读锁一定不要忘记unlock:
     * 如果忘记unlock,虽然写锁放弃后，其他线程虽然可以获取读锁进行读操作，因为是读之间是共享的。
     * 但是如果有线程想要获取写锁进行写操作，会失败，因为写锁内部读锁没有释放，虽然读操作完成了，但是读锁计数仍然大于0，表示依然有线程在读，因此
     * 无法获取写锁。
     */
    @Test
    public void writeWithReadTest() {
        new Thread(() -> {
            writeWithRead();
        }).start();

        // 保证线程1已经lock
        try {
            Thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 如果写操作内部读锁未释放，仍然可以读，因为读操作是共享的。
        /*new Thread(()->{
            read();
        }).start();*/

        new Thread(() -> {
            write();
        }).start();


        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 有线程在读，其他线程是否可写？
     */
    @Test
    public void test() {
        // 读线程
        new Thread(() -> {
            read();
        }).start();

        try {
            Thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 写线程
        new Thread(() -> {
            write();
        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
