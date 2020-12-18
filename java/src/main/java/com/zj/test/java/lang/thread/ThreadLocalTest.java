package com.zj.test.java.lang.thread;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/12/9 10:54
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

/**
 * 好处：
 * 1.使得多线程可以共享数据.
 * 2.对数据的操作是相互隔离的，不会互相影响。
 * */
public class ThreadLocalTest {

    /**
     * 1.ThreadLocal使用Demo
     *
     * 【结论】
     * 1.initialValue() 方法调用时机：
     * 线程第一次调用get时，会返回initialValue()返回值。
     * 线程调用threadLocal.remove()后，再次调用get时。
     *
     */
    @Test
    public void demo() {
        // 线程id
        final int[] threadId = {1};
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return threadId[0]++;
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TestHelper.println(Thread.currentThread().getName() + "获取到数据", threadLocal.get());
                }
            }).start();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 2.测试：不同线程，initialValue()返回的引用对象可能一样吗？
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 可能：
     * 1.返回非new方式构造的字符串，可能相同。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void initialValue() {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>() {

            @Override
            protected String initialValue() {
                return "initialValue";
            }
        };

        // 线程1, 2获取得到的值
        AtomicReference<String> value1 = new AtomicReference<>();
        AtomicReference<String> value2 = new AtomicReference<>();

        new Thread(() -> {
            value1.set(threadLocal.get());
        }).start();

        new Thread(() -> {
            value2.set(threadLocal.get());
        }).start();

        // 等待线程执行完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // true
        TestHelper.println("多线程获取对象使用一致", value1.get() == value2.get());
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 3.测试：一个线程对于变量的修改，是否会影响其他线程的变量
     *
     * 【结论】
     * 1.不会，一个线程get set remove都是本线程的变量。
     *
     * 【结论拓展】
     * 1.remove后调用 set 不会调用initialValue
     * 2.remove后再get，会调用initialValue
     */
    @Test
    public void test1() {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                TestHelper.println(Thread.currentThread().getName() + "调用了initialValue()");
                return "initialValue";
            }
        };

        // 线程1修改变量
        new Thread(() -> {
            TestHelper.println(Thread.currentThread().getName() + "初始值", threadLocal.get());
            threadLocal.set("new value");
            TestHelper.println(Thread.currentThread().getName() + "最新值", threadLocal.get());
            threadLocal.remove();
            // remove后调用 set 不会调用initialValue
            threadLocal.set("new value2");
            TestHelper.println(Thread.currentThread().getName() + "最新值", threadLocal.get());

            threadLocal.remove();
            // remove后再get，会调用initialValue
            TestHelper.println(Thread.currentThread().getName() + "最新值", threadLocal.get());
        }).start();

        // 等待线程1修改完毕
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 线程2获取变量，查看是否改变
        new Thread(() -> {
            TestHelper.println(Thread.currentThread().getName() + "获取的值", threadLocal.get());
        }).start();
    }
}
