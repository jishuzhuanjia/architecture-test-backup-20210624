package com.zj.test.java.lang.thread;

import com.zj.test.java.LangTest;
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
 * ThreadLocal好处：
 * 1.使得多线程可以共享数据.
 * 2.对数据的操作是相互隔离的，不会互相影响。
 *
 * 1.当initialValue中直接返回引用，而不是通过new一个对象返回时       -- 这种用法失去了ThreadLocal的意义，不推荐
 * ThreadLocal可以用来共享数据，在没有通过ThreadLocal.set来为本线程指定新的对象之前，对ThreadLocal.get获取的数据进行修改就是对共享数据的修改，
 *
 * 而调用ThreadLocal.set设置了本线程的数据后，则对Thread.get获取的数据的修改是对本线程私有数据的修改，改动不会被其他线程察觉。
 *
 * 线程在调用Thread.remove后可以重新调用Thread.get获得共享的数据
 *
 * 2.当initialValue中通过new一个对象返回时                 -- 推荐的用法
 * 这样既可以实现数据的共享，而且对数据的操作是相互隔离的，不会互相影响。
 *
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
                    TestHelper.println("获取到数据", threadLocal.get());
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
     * 1.initialValue中可以返回不同的对象的引用，但是如果返回同一对象的引用(包括)，则线程通过threadLocal.get()获取的对象引用都是相同的(前提是没有set新对象)。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void initialValue() {
        // 1.共享String变量测试
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
        TestHelper.println("多线程获取String对象使用一致", value1.get() == value2.get());

        // 2.共享其他引用类型变量测试

        LangTest.User user = new LangTest.User();
        user.username = "zhou jian";
        user.password = "123456";

        ThreadLocal<LangTest.User> threadLocal2 = new ThreadLocal<LangTest.User>() {

            @Override
            protected LangTest.User initialValue() {
                TestHelper.println("initialValue");
                // 这里返回相同的引用
                return user;
            }
        };

        // 线程1, 2获取得到的值
        AtomicReference<LangTest.User> value3 = new AtomicReference<>();
        AtomicReference<LangTest.User> value4 = new AtomicReference<>();

        new Thread(() -> {
            value3.set(threadLocal2.get());
            threadLocal2.get().username="zhouxxxx";

            // remove后，再次调用get会触发initialValue()
            /*threadLocal2.remove();
            TestHelper.println("threadLocal2.get()",threadLocal2.get());*/

        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestHelper.println("准备启动thread4");
        new Thread(() -> {
            value4.set(threadLocal2.get());
        }).start();

        // 等待线程执行完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestHelper.println("value3.get()",value3.get());
        TestHelper.println("value4.get()",value4.get());
        // true
        TestHelper.println("多线程获取User对象使用一致", value3.get() == value4.get());

    }

    /**
     * 测试：ThreadLocal线程之间的修改是否会互相影响？
     *
     * 会，因为共享同一引用。
     */
    @Test
    public void test(){

        LangTest.User user = new LangTest.User();
        user.username="shared user";
        user.password="123456";

        ThreadLocal<LangTest.User> threadLocal =new ThreadLocal<LangTest.User>(){
            @Override
            protected LangTest.User initialValue() {
                return user;
            }
        };

        // 1.线程1进行修改username
        new Thread(()->{
            TestHelper.println("获取到的user对象",threadLocal.get());
            // 修改了username
            threadLocal.get().username="newUsername";
            TestHelper.println("修改后",threadLocal.get());

        }).start();

        // 等待线程1修改完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2.线程2查看username是否改变？
        new Thread(()->{
            // [Thread-1] - 获取到的user对象: User{username='newUsername', password='123456', age=0}
            TestHelper.println("获取到的user对象",threadLocal.get());

        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试：ThreadLocal.set()会影响其他线程吗？
     *
     * 不会，当为本线程set一个新的对象后，操作的将是新对象，而不是共享的对象了。
     * 如果不set，对get的对象进行修改，则是修改的共享对象。
     *
     * 如果线程remove后再次get，会重新获得共享的对象引用。
     */
    @Test
    public void test2(){

        LangTest.User user = new LangTest.User();
        user.username="shared user";
        user.password="123456";

        ThreadLocal<LangTest.User> threadLocal =new ThreadLocal<LangTest.User>(){
            @Override
            protected LangTest.User initialValue() {
                return user;
            }
        };

        // 1.线程1进行修改username
        new Thread(()->{
            TestHelper.println("获取到的user对象",threadLocal.get());
            // 修改了user
            LangTest.User newUser = new LangTest.User();
            newUser.username="newUser";
            newUser.password="123";
            threadLocal.set(newUser);
            TestHelper.println("修改后",threadLocal.get());
            threadLocal.remove();
            // 重新获取了共享数据
            TestHelper.println("获取到的user对象",threadLocal.get());

        }).start();

        // 等待线程1修改完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2.线程2查看username是否改变？
        new Thread(()->{
            // [Thread-1] - 获取到的user对象: User{username='newUsername', password='123456', age=0}
            TestHelper.println("获取到的user对象",threadLocal.get());

        }).start();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
