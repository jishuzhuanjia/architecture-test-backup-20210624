package com.zj.test.java.util;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/17 15:06
 * @description: Collections测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class CollectionsTest {

    /**
     *
     * 1.测试public static <T> java.util.List<T> synchronizedList(List<T> list)
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
    public void synchronizedList() {
        /*ArrayList<String> strings = new ArrayList<>();
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
        }*/
    }

    /**
     * 2.java.util.Set<T> singleton(T o)
     * 返回只包含指定对象的不可变集合。返回的集合是可序列化的。
     */
    @Test
    public void singleton(){
        Set<String> set1 = Collections.singleton("我");
        // 不能添加元素,否则: java.lang.UnsupportedOperationException
        /*set1.add("我");
        set1.add("爱");
        set1.add("你");*/

        TestHelper.println(set1);
    }

    /**
     * 3.List<T> singletonList(T o)
     * 于singleton不同的是，返回的是一个List，也不能够修改元素
     *
     */
    @Test
    public void singletonList(){
        List<String> list = Collections.singletonList("我");

        // java.lang.UnsupportedOperationException
        /*list.add("爱");
        list.add("你");*/

        TestHelper.println(list);

    }

    /**
     * 4.java.util.Map<K, V> singletonMap(K key,V value)
     * 与singleton不同的是，返回的是一个Map，也不能够修改元素
     */
    @Test
    public void singletonMap(){
        Map<String, String> map = Collections.singletonMap("username", "password");

        // 不能够修改map.否则: java.lang.UnsupportedOperationException
        //map.put("password","123456");
        TestHelper.println(map);
    }
}
