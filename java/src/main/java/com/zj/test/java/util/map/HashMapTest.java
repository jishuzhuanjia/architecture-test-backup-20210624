package com.zj.test.java.util.map;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/23 15:07
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 继承关系：HashMap(Class) -> AbstractMap(Class)
 *
 * HashMap实现了Map<K,V>接口
 *
 * 线程不安全的
 */
public class HashMapTest {
    // HashMap 和 SortedMap
    //              TreeMap

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 1.测试: HashMap中key和value是否能为null。
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     *
     * 1.HashMap的key和value可以使用null值
     * 需要注意的为null的key最多只有一个，多次修改key为nuLl的值，只是覆盖。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void test1() {
        Map<Object, Object> map = new HashMap<>();

        /* 1.设置key为null
           需要注意的是,等于null的key只有一个，因此多次赋值会覆盖值。
        */
        map.put(null, "value of key.null1");
        map.put(null, "value of key.null2");
        TestHelper.println("map", map);

        /*
        2.设置value为null
         */
        map.put("key1",null);
        TestHelper.println("map", map);
    }
}
