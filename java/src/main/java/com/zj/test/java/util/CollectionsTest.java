package com.zj.test.java.util;

import com.zj.test.java.po.User;
import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.*;

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
    public void singleton() {
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
    public void singletonList() {
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
    public void singletonMap() {
        Map<String, String> map = Collections.singletonMap("username", "password");

        // 不能够修改map.否则: java.lang.UnsupportedOperationException
        //map.put("password","123456");
        TestHelper.println(map);
    }

    /**
     * 5.public static final <T> List<T> emptyList()
     *
     * 【作用/描述】
     *
     * 【出/入参记录】
     * java.lang.UnsupportedOperationException
     *
     * 【使用注意点】
     *
     */
    @Test
    public void emptyList() {
        List<Object> emptyList = Collections.emptyList();
        // java.lang.UnsupportedOperationException
        emptyList.add("a");
    }

    /**
     * 6.public static <T> void sort(List<T> list, Comparator<? super T> c)
     *
     * 【作用/描述】
     * 对list中的元素进行排序
     *
     * 【出/入参记录】
     * [main] - userList: [User(username=username3, password=password, age=9), User(username=username1, password=password, age=11), User(username=username2, password=password, age=22)]
     *
     * 【使用注意点】
     *
     */
    @Test
    public void sortWith2Params() {
        TestHelper.startTest("sort(List<T> list, Comparator<? super T> c)");
        User user1 = new User("username1", "password", 11);
        User user2 = new User("username2", "password", 22);
        User user3 = new User("username3", "password", 9);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                // o1 - o2: 升序
                // o2 - o1: 降序

                // 如果返回0,不对元素位置进行调整
                //return 0;

                return o1.getAge() - o2.getAge();
            }
        });

        TestHelper.println("userList", userList);
    }

    /**
     * 7.public static <T extends Comparable<? super T>> void sort(List<T> list)
     *
     * 【作用/描述】
     *
     * 【出/入参记录】
     *
     * 【使用注意点】
     * 元素类需要实现
     *
     */
    @Test
    public void sortWith1Param() {
        TestHelper.startTest("sort(List<T> list)");

        User user1 = new User("username1", "password", 11);
        User user2 = new User("username2", "password", 22);
        User user3 = new User("username3", "password", 9);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        // List元素类需要实现Comparable接口
        /*Required type:
        List
                <T>
        Provided:
        List
                <User>*/
        Collections.sort(userList);

        TestHelper.println("userList", userList);
    }

    /**
     * 8.boolean addAll(Collection<? super T> c, T... elements)
     *
     * 【作用/描述】
     * 将elements中的元素都添加到c中
     *
     * 【出/入参记录】
     *
     * 【使用注意点】
     * 参数2是数组，要先将集合转换成数组
     */
    @Test
    public void addAll() {
        TestHelper.startTest("addAll(Collection<? super T> c, T... elements)");
        List<User> userList = new ArrayList<>();
        userList.add(new User("username1", "123456", 11));

        List<User> userList2 = new ArrayList<User>();
        userList2.add(new User("username2", "123456", 11));
        userList2.add(new User("username3", "123456", 11));
        userList2.add(new User("username4", "123456", 11));

        TestHelper.println("userList", userList);
        TestHelper.println("userList2", userList2);

        TestHelper.startTest("将userList2元素添加到userList");

        // toArray(@NotNull T[] a) - 如果a指定的数组长度不足以容纳集合所有的元素，会重新分配，因此这里可以创建长度为0的数组
        Collections.addAll(userList, userList2.toArray(new User[0]));

        TestHelper.println("合并后的userList", userList);
    }
}
