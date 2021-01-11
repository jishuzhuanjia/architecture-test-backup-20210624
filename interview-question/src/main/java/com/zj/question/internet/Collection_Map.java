package com.zj.question.internet;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/28 15:41
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class Collection_Map {
    /**
    ################################ 1.Java集合框架面试题 #####################################

    1.HashMap 和 HashTable 的区别？
    HashMap是map接口的实现类(继承自 AbstractMap 类)，是将值映射到键的对象，其中键和值都是对象，并且不能包含重复键，但可以包含重复值。
    HashMap 不是线程安全的
    HashMap 允许 null key和 null value，而 HashTable 不允许。

    Hashtable是线程安全Map。
    HashMap 是 HashTable 的轻量级实现，他们都完成了Map 接口，
    主要区别在于HashMap 允许 null key 和 null value, 由于非线程安全, 效率上可能高于Hashtable。

    区别:
    1.1.继承不同
    HashMap继承自抽象类 AbstractMap<K,V>,是 java 1.2 引入的一个Map接口的实现。
    而Hashtable继承自抽象类 Dictionary<K,V>, 是 java 1.0 引入的一个Map接口的实现。

    1.2.key和value可用值不同
    HashMap 允许将 null 作为一个entry 的 key 或者value,
    而 Hashtable 不允许(key 和 value 都不允许,否则报空指针异常)。

    1.3.HashMap 把 Hashtable 的contains方法去掉了，改成containsValue 和 containsKey。因为contains 方法容易让人引起误解。
    // 2不正确。
    // 注：不应该说HashMap将Hashtable的contains方法去掉了，因为二者没有继承关系。
    // 应该说Hashtable自己添加了一个contains方法，其实Hashtable也有containsValue和containsKey方法。
    // 标注人：zhoujian 2025513

    1.4.线程安全
    HashTable 的方法是线程安全的的, 而HashMap不是, 在多个线程访问 Hashtable 时, 不需要单独为它的方法调用实现同步,
    而 HashMap 就必须为之实现同步。
    ----------------------------

    2.说一下HashMap的底层结构？
    2.1.底层数据结构是数组 + 链表

    数组是：Node<K,V>[] table;
    Node<K,V>类有一个next，就是指向链表的下一个对象。
    数组下标表示的是键对象的hash值。

    链表：
    链表则是为了解决哈希冲突而存在。

    2.2.性能
    如果数组中Node元素next为null，则查找和添加等操作则仅需要一次寻址即可，
    如果包含链表，其时间复杂度为O(n)，查找时首先会遍历链表，通过比较Node对象的euals方法逐一对比进行查找。
    所以，性能考虑，HashMap中出现的链表越短，性能越好。

    2.3.优化
    HashMap性能优化的方式就是提供好的哈希算法，计算出的哈希值重复越少越好。

    --------------------

    3.ArrayList和LinkedList的区别是什么？
    3.1.存储结构
    ArrayList实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。(存储结构)

    3.2.性能
    1..对于随机访问 get 和 set, ArrayList 优于 LinkedList, 因为LinkedList要移动指针。
    2.对于新增和删除操作 add 和 remove，LinkedList比较占优势，因为ArrayList要移动数据。
    ---------------------

    4.ArrayList 和 Vector 的区别是什么？
    4.1.线程安全
    Vector是线程安全的, ArrayList是线程不安全的。

    NOTE:
    Hahtable 和 Vector
    两者都是从1.0就开始提供了，是Java一开始就有的，是线程安全的。
    HashMap 和 ArrayList
    从java 1.2开始提供的， 是线程不安全的。
    ----------------------------------------------------------------------------------------

    5.Array和ArrayList的区别？
    5.1.数据类型
    Array可以包含基本数据类型和引用数据类型。
    ArrayList只能包含引用类型、

    5.2.大小
    Array一经创建，大小不能更改，ArrayList可以通过内部方法自动调整容量。

    5.3.操作
    Array只能进行简单的数据操作，如查找和赋值。
    而ArrayList还有插入，清空等操作，相比Array支持更多的操作和特性。

    ----------------------------------------------------------------------------------------

    6.说一下HashSet的实现原理？

    ----------------------------------------------------------------------------------------

    7.List、Set、Map之间的区别是什么？

     */
}
