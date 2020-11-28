package com.zj.question.internet;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/28 15:41
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class InternetQuestion {
    /*
    ################################ 1.Java集合框架面试题 #####################################

    1.HashMap 和 HashTable 的区别？
    HashMap 不是线程安全的
    HashMap是map接口的实现类，是将键映射到值的对象，其中键和值都是对象，并且不能包含重复键，
    但可以包含重复值。HashMap 允许 nullkey和 null value，而 HashTable 不允许。

    HashTable是线程安全Collection。
    HashMap 是 HashTable 的轻量级实现，他们都完成了Map 接口，
    主要区别在于HashMap 允许null key和 null value,由于非线程安全，效率上可能高于Hashtable。

    区别:
    1.HashMap 允许将null作为一个entry 的key 或者value，而 Hashtable 不允许(key和value都不允许,否则报空指针异常)。
    2.HashMap 把 Hashtable 的contains方法去掉了，改成containsValue 和 containsKey。因为contains 方法容易让人引起误解。
    // 2不正确。
    // 注：不应该说HashMap将Hashtable的contains方法去掉了，因为二者没有继承关系。
    // 应该说Hashtable自己添加了一个contains方法，其实Hashtable也有containsValue和containsKey方法。
    // 标注人：zhoujian 2025513

    3.HashTable 继承自Dictionary 类，而HashMap是Java1.2引进的 Map interface 的一个实现。
    4.HashTable 的方法是Synchronize 的，而HashMap不是，在多个线程访问Hashtable 时，不需要自己为它的方法实现同步，
    而HashMap 就必须为之提供外同步。

    ----------------------------------------------------------------------------------------

    2.说一下HashMap的底层结构？
    2.1.底层数据结构是数组 + 链表

    数组是：Node<K,V>[] table;
    Node<K,V>类有一个next，就是指向链表的下一个对象。
    数组下标表示的是hash值

    链表：
    链表则是为了解决哈希冲突而存在。

    2.2.性能
    如果数组中Node元素next为null，则查找和添加等操作则仅需要一次寻址即可，
    如果包含链表，其时间复杂度为O(n)，查找时首先会遍历链表，通过比较Node对象的euals方法逐一对比进行查找。
    所以，性能考虑，HashMap中出现的链表越少，性能越好。

    2.3.优化
    HashMap性能优化的方式就是提供好的哈希算法，计算出的哈希值重复越少越好。

    ----------------------------------------------------------------------------------------

    3.ArrayList和LinkedList的区别是什么？
    a.ArrayList实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。(存储结构)
    b.对于随机访问get和set，ArrayList优于LinkedList,因为LinkedList要移动指针。
    c.对于新增和删除操作 add 和 remove，LinkedList比较占优势，因为ArrayList要移动数据。(性能方面)

    ----------------------------------------------------------------------------------------

    4.ArrayList和Vector的区别是什么？


    ----------------------------------------------------------------------------------------

    5.Array和ArrayList的区别？
    a.数据类型
    Array可以包含基本数据类型和引用数据类型。
    ArrayList只能包含引用类型、

    b.大小
    Array一经创建，大小不能更改，ArrayList可以通过内部方法自动调整容量。

    c.操作
    Array只能进行简单的数据操作，如获取和赋值。
    而ArrayList还有插入，清空等操作，相比Array支持更多的操作和特性。


    ----------------------------------------------------------------------------------------

    6.说一下HashSet的实现原理？

    ----------------------------------------------------------------------------------------

    7.List、Set、Map之间的区别是什么？

     */
}
