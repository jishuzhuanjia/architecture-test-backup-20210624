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
        map.put("key1", null);
        TestHelper.println("map", map);
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 2.api测试
     * public boolean containsKey(Object key)
     * public boolean containsValue(Object value)
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.public boolean containsKey(Object key)
     * 判断是否存在指定的key,如果存在返回true,否则返回false。
     *
     * 2.public boolean containsValue(Object value)
     * 判断是否存在指定的value，如果存在，返回true，否则返回false
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void containsKey_containsValue() {

        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        TestHelper.println("hashMap",hashMap);

        // 1.public boolean containsKey(Object key)
        TestHelper.println(" hashMap.containsKey(\"key1\")", hashMap.containsKey("key1"));

        TestHelper.println(" hashMap.containsKey(\"key4\")", hashMap.containsKey("key4"));

        // 2.public boolean containsValue(Object value)
        TestHelper.println(" hashMap.containsValue(\"value2\")", hashMap.containsValue("value2"));
        TestHelper.println(" hashMap.containsValue(\"value4\")", hashMap.containsValue("value4"));

    }
}
