package com.zj.test.java.util.collection.list;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/10 15:23
 * @description: ArrayList测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月24日 21:45:56
 */
public class ArrayListTest {

    // 创建空ArrayList
    public List<Object> createEmptyArrayList() {
        return new ArrayList<Object>();
    }

    /**
     * author: 2025513
     *
     * 1.ArrayList添加元素
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.public abstract boolean add(E e)
     * 向List最后位置添加元素
     *
     * 2.public abstract void add(int index,E element)
     * 在指定的位置插入元素，需要注意的是index基于0(第一个位置)。
     *
     * 3.boolean addAll(Collection<? extends E> c);
     * 将集合c的元素顺序添加到List尾部。
     * 该方法不会修改c的内容。
     *
     * 4.public abstract boolean addAll(int index,Collection<? extends E> c)
     * 会从列表index位置开始，将c中的元素顺序插入到列表中。
     * 该方法不会修改c的内容。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void add() {
        TestHelper.startTest("ArrayList.add*测试");

        // 1.test1 - public abstract boolean add(E e)
        List<Object> emptyArrayList1 = createEmptyArrayList();
        emptyArrayList1.add(1);
        emptyArrayList1.add(2);
        emptyArrayList1.add(3);
        // emptyArrayList1: [1, 2, 3]
        TestHelper.println("emptyArrayList1", emptyArrayList1);

        //2.test2 -  add(int index,E element)
        emptyArrayList1.add(1, 5);
        // emptyArrayList1: [1, 5, 2, 3]
        TestHelper.println("emptyArrayList1.add(1,5)", emptyArrayList1);

        List<Object> emptyArrayList2 = createEmptyArrayList();
        emptyArrayList2.add("A");
        emptyArrayList2.add("B");
        emptyArrayList2.add("C");

        // 3.测试 boolean addAll(Collection<? extends E> c);
        emptyArrayList1.addAll(emptyArrayList2);
        TestHelper.println("emptyArrayList1.addAll([A,B,C])", emptyArrayList1);

        // 4.测试 public abstract boolean addAll(int index,Collection c)
        // 会从列表index位置开始，顺序插入c中的元素。
        emptyArrayList1.addAll(1, emptyArrayList2);
        TestHelper.println("emptyArrayList1.addAll(1,[A,B,C])", emptyArrayList1);

        /*
        4.验证addAll是否会修改参数list
        结果：不会
        */
        TestHelper.startSubTest("验证addAll是否破坏参数emptyArrayList2...");
        TestHelper.println("emptyArrayList2", emptyArrayList2);
        TestHelper.println("没有破坏");
    }

    /**
     * author: 2025513
     * 2020年11月24日 17:37:30
     *
     * 2.ArrayList删除元素
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.public abstract boolean remove(Object o)
     * 移除第一个引用等于o的对象。
     * 需要注意的是该方法只能移除0或1个元素。
     * 返回值：如果移除了元素，返回true，否则返回false。
     *
     * 2.public abstract E remove(int index)测试
     * 移除list指定位置的元素并返回被移除的元素
     *
     * 调用此方法需要保证列表对应位置元素存在，否则：
     * java.lang.IndexOutOfBoundsException: ...
     *
     * index有效值:
     * 0 <= index <= list.size-1,否则:
     * java.lang.IndexOutOfBoundsException: ...
     *
     * 3.public abstract boolean removeAll(Collection<?> c)
     * 移除所有出现在list中的c的元素都会被移除
     *
     * 可批量删除：
     * 与remove(Object)不同的是，该方法会删除所有相同的元素。
     *
     * 返回值：如果移除了元素，返回true，否则返回false。
     *
     * 4.public boolean removeIf(Predicate<? super E> filter)
     * 对元素过滤并删除，条件表达式为true时，元素会被移除。
     *
     * 与其他remove方法不相同的是，是否移除是通过元素本身进行判断。
     *
     * 可批量删除：
     * 会对每个元素进行过滤，符合的都会被删除。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void remove() {
        List<Object> emptyArrayList = createEmptyArrayList();
        emptyArrayList.add("H");
        emptyArrayList.add("e");
        emptyArrayList.add("l");
        emptyArrayList.add("l");
        emptyArrayList.add("o");
        emptyArrayList.add("W");
        emptyArrayList.add("o");
        emptyArrayList.add("r");
        emptyArrayList.add("l");
        emptyArrayList.add("d");
        TestHelper.println("emptyArrayList", emptyArrayList);

        // 1.测试public abstract boolean remove(Object o)
        emptyArrayList.remove("W");
        TestHelper.println("emptyArrayList.remove(\"W\")", emptyArrayList);

        TestHelper.startSubTest("测试remove(Object o)是否可移除多个元素");
        emptyArrayList.remove("l");
        TestHelper.println("emptyArrayList.remove(\"l\")", emptyArrayList);


        /*
        2.public abstract E remove(int index)测试
        移除list指定位置的元素并返回被移除的元素
         */
        TestHelper.println("emptyArrayList.remove(1)", emptyArrayList.remove(1));
        TestHelper.println("emptyArrayList", emptyArrayList);
        /*
        3.public abstract boolean removeAll(Collection<?> c)
        所有出现在list中的c的元素都会被移除
        与remove(Object)不同的是，该方法会删除所有相同的元素。

        返回值：如果移除了元素，返回true，否则返回false。
         */
        // 会删除所有的l和o
        TestHelper.println("emptyArrayList.removeAll(Arrays.asList(\"l\",\"o\"))",
                emptyArrayList.removeAll(Arrays.asList("l", "o")));
        TestHelper.println("emptyArrayList", emptyArrayList);

        TestHelper.println("emptyArrayList.removeAll(Arrays.asList(\"x\",\"h\"))",
                emptyArrayList.removeAll(Arrays.asList("x", "h")));
        TestHelper.println("emptyArrayList", emptyArrayList);

        /*
         4.public boolean removeIf(Predicate<? super E> filter)
         与其他remove方法不相同的是，是否移除是通过元素本身进行判断。
         每个符合要求的元素都会被删除。
         */
        // 实现删除'H'
        emptyArrayList.removeIf((a) -> {
            return a.equals("H") || a.equals("d");
        });

        TestHelper.println("emptyArrayList", emptyArrayList);
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 3.查
     * 【作用】
     * 从列表中获取元素。
     *
     * 【测试结果】
     *
     * 【结论】
     * 查只有一个方法
     * 1.public abstract E get(int index)
     * 返回指定位置的元素，如果对应位置的元素不存在，则抛出异常：
     * java.lang.IndexOutOfBoundsException: ...
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void get() {
        List<Object> emptyArrayList = createEmptyArrayList();
        emptyArrayList.addAll(Arrays.asList("A", "B", "C"));

        // 1.public abstract E get(int index)测试
        for (int i = 0; i < emptyArrayList.size(); i++) {
            TestHelper.println("get(" + i + ")", emptyArrayList.get(i));
        }

        // java.lang.IndexOutOfBoundsException: Index: 100, Size: 3
        //TestHelper.println("get(100)",emptyArrayList.get(100));

    }
}
