package com.zj.test.java.util;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/17 15:06
 * @description: Collections测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class CollectionsTest {

    /**
     * author: 2025513
     *
     * 1.测试
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void test1() {
    }


    /**
     * author: 2025513
     *
     * 2.测试public static <T> java.util.List<T> synchronizedList(List<T> list)
     *
     * 【作用】
     * 返回线程安全的List, 将list作为同步方法锁。
     *
     * 【测试结果】
     *
     * 【结论】
     *
     *
     * 【优点】
     * 【缺点】
     * iterator()不是同步方法,遍历的时候需要手动同步，否则会导致不确定行为：
     *
     *         List list = Collections.synchronizedList(new ArrayList());
     *             ...
     *         synchronized (list) {
     *             Iterator i = list.iterator(); // Must be in synchronized block
     *             while (i.hasNext())
     *                 foo(i.next());
     *         }
     */
    @Test
    public void test2() {
        ArrayList<String> strings = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(strings);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(() -> {
                strings.add("string" + finalI);
                TestHelper.println(Thread.currentThread().getName(), synchronizedList.size());
            }).start();
        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
