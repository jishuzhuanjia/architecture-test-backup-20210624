package com.zj.test.java.util.map;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.Hashtable;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/28 17:05
 * @description: HashTable测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

/**
 * 继承关系
 * Hashtable单独继承自Dictionary类：
 * public class Hashtable<K,V> extends Dictionary<K,V>
 *
 * 并实现了Map<K,V>接口
 *
 */
public class HashTableTest {

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 1.测试：
     * Hashtable是否可用null作为key或value?
     *
     *
     * 【作用】
     *
     * 【测试结果】
     * 将key或value设置为null,都会抛出异常：
     * java.lang.NullPointerException
     *
     * 【结论】
     *
     * Hashtable key 和 value 都不能为null。
     * 【优点】
     * 【缺点】
     */
    @Test
    public void test1(){
        Hashtable<Object,Object> hashtable = new Hashtable<Object,Object>();

        /*
        1.将key设置为null,抛出异常：
        java.lang.NullPointerException

        即key不能为null
         */
        /*hashtable.put(null,"value1");
        TestHelper.println("hashtable",hashtable);*/

        /*
        2.将value设置为null，抛出异常：
        java.lang.NullPointerException

        即value也不能为null。
         */
        hashtable.put("key1",null);
        TestHelper.println("hashtable",hashtable);

    }
}
