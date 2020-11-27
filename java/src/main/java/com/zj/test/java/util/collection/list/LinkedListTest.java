package com.zj.test.java.util.collection.list;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/27 23:16
 * @description: LinkedList测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class LinkedListTest {
    private LinkedList createEmptyLinkedList() {
        return new LinkedList<Object>();
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 1.LinkedList 增操作
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     *  1.public boolean add(E e)
     *  在list尾部添加元素
     *
     *  2.public void add(int index,E element)
     *  在指定的元素插入元素
     *
     *  3.public boolean addAll(java.util.Collection<? extends E> c)
     *  在LinkedList尾部将c集合元素顺序添加
     *
     *  4.public boolean addAll(int index,java.util.Collection<? extends E> c)
     *  在LinkeList指定的位置将c集合元素顺序插入
     *
     *  5.public void addFirst(E e)
     *  在List头部添加元素
     *
     *  6.public void addLast(E e)
     *  在List尾部添加元数
     *
     *  方法作用和ArrayList一样。
     *
     * 【优点】
     *
     * 【缺点】
     */
    @Test
    public void add() {
        LinkedList emptyLinkedList = createEmptyLinkedList();

        // 1.public boolean add(E e)测试
        emptyLinkedList.add("element1");
        emptyLinkedList.add("element2");
        emptyLinkedList.add("element3");
        emptyLinkedList.add("element4");

        TestHelper.println("emptyLinkedList", emptyLinkedList);

        // 2.public void add(int index,E element)测试
        emptyLinkedList.add(1, "element5");

        TestHelper.println("emptyLinkedList.add(1,\"element5\")", emptyLinkedList);

        // 3.public boolean addAll(java.util.Collection<? extends E> c)
        // 在LinkedList尾部将c集合元素顺序添加
        emptyLinkedList.addAll(Arrays.asList("element6", "element7", "element8"));
        TestHelper.println("emptyLinkedList.addAll(Arrays.asList(\"element6\",\"element7\",\"element8\"))", emptyLinkedList);

        // 4.public boolean addAll(int index,java.util.Collection<? extends E> c)
        // 在LinkeList指定的位置将c集合元素顺序插入
        emptyLinkedList.addAll(0, Arrays.asList("element9", "element10", "element11"));
        TestHelper.println("emptyLinkedList.addAll(0,Arrays.asList(\"element9\",\"element10\",\"element11\"))", emptyLinkedList);

        emptyLinkedList.addFirst("first");
        TestHelper.println("emptyLinkedList.addFirst(\"first\")", emptyLinkedList);

        emptyLinkedList.addLast("last");
        TestHelper.println("emptyLinkedList.addLast(\"last\")", emptyLinkedList);


    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 2.LinkedList - 删除操作
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.public E remove()
     * 移除并返回list的第一元素。
     *
     * 2.public boolean remove(Object o)
     * 移除第一个和o引用相等的元素，该方法只会移除0或1个元素。
     * 如果找到并移除则返回true，否则返回false。
     *
     * 3.public E remove(int index)
     * 移除并返回指定位置的元素。
     *
     * 4.public E removeFirst()
     * 移除并返回list的第一元素。
     *
     * 5.public boolean removeFirstOccurrence(Object o)
     * 移除第一个引用等于o的对象，如果存在，则返回true,否则返回false
     *
     * 6.public E removeLast()
     * 移除最后一个元素
     *
     * 7.public boolean removeLastOccurrence(Object o)
     * 移除最后一个引用等于o的元素，如果有，返回true,否则返回false。
     *
     * 8.public boolean removeAll(java.util.Collection<?> c)
     * 移除所有和c中元素引用一样的元素。和remove(E)不同的是，它会全部删除。
     *
     * 9.public boolean removeIf(java.util.function.Predicate<? super E> filter)
     * 遍历元素并进行条件删除，条件表达式为true的元素会被移除。
     * 【优点】
     * 【缺点】
     */
    @Test
    public void remove() {
        LinkedList emptyLinkedList = createEmptyLinkedList();

        emptyLinkedList.add("element1");
        emptyLinkedList.add("element1");
        emptyLinkedList.add("element1");
        emptyLinkedList.add("element2");
        emptyLinkedList.add("element2");
        emptyLinkedList.add("element2");
        emptyLinkedList.add("element3");

        TestHelper.println("emptyLinkedList", emptyLinkedList);

        // 1.public E remove()
        emptyLinkedList.remove();
        TestHelper.println("emptyLinkedList.remove()", emptyLinkedList);

        // 2.public boolean remove(Object o)
        emptyLinkedList.remove("element2");
        TestHelper.println("emptyLinkedList.remove(\"element2\")", emptyLinkedList);

        // 3.public E remove(int index)
        emptyLinkedList.remove(0);
        TestHelper.println("emptyLinkedList.remove(0)", emptyLinkedList);

        // 4.public E removeFirst()
        emptyLinkedList.removeFirst();
        TestHelper.println("emptyLinkedList.removeFirst()", emptyLinkedList);

        emptyLinkedList.add("element1");
        emptyLinkedList.add("element3");
        emptyLinkedList.add("element5");
        emptyLinkedList.add("element5");
        emptyLinkedList.add("element3");
        emptyLinkedList.add("element5");
        TestHelper.println("emptyLinkedList", emptyLinkedList);

        // 5.public boolean removeFirstOccurrence(Object o)
        emptyLinkedList.removeFirstOccurrence("element3");
        TestHelper.println("emptyLinkedList.removeFirstOccurrence(\"element3\")", emptyLinkedList);

        // 6.public E removeLast()
        emptyLinkedList.removeLast();
        TestHelper.println("emptyLinkedList.removeLast()", emptyLinkedList);

        // 7.public boolean removeLastOccurrence(Object o)
        emptyLinkedList.removeLastOccurrence("element3");
        TestHelper.println("emptyLinkedList.removeLastOccurrence(\"element3\")", emptyLinkedList);

        // 8.public boolean removeAll(java.util.Collection<?> c)
        emptyLinkedList.removeAll(Arrays.asList("element5"));
        TestHelper.println("emptyLinkedList.removeAll(Arrays.asList(\"element5\")", emptyLinkedList);

        // 9.public boolean removeIf(java.util.function.Predicate<? super E> filter)
        // 移除1或2
        emptyLinkedList.removeIf((e) -> {
            return e.equals("element2") || e.equals("element1");
        });
        TestHelper.println("emptyLinkedList.removeIf((e)->{return e.equals(\"element2\") || e.equals(\"element1\");})", emptyLinkedList);
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 3.LinkedList - 查操作
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.public E get(int index)
     * 返回指定位置的元素，index基于0。
     *
     * 2.public E getFirst()
     * 返回第一个元素，不会移除元素。
     *
     * 3.public E getLast()
     * 返回最后一个元素
     *
     * 这3个获取元素的方法都不会修改集合。
     *
     * 【优点】
     * 1.getFirst()、getLast()非常适用用来实现堆和栈。
     *
     * 【缺点】
     */
    @Test
    public void get() {
        LinkedList emptyLinkedList = createEmptyLinkedList();
        emptyLinkedList.add("element1");
        emptyLinkedList.add("element2");
        emptyLinkedList.add("element3");

        TestHelper.println("emptyLinkedList.get(1)",emptyLinkedList.get(1));
        TestHelper.println("emptyLinkedList.getFirst()",emptyLinkedList.getFirst());
        TestHelper.println("emptyLinkedList.element()",emptyLinkedList.element());
        TestHelper.println("emptyLinkedList.getLast()",emptyLinkedList.getLast());

        TestHelper.println("emptyLinkedList",emptyLinkedList);
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 4.api测试 - public boolean contains(Object o)
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     * public boolean contains(Object o)
     * 查找LinkedList中是否存在指定的元素，存在则返回true,否则返回false。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void contains(){
        LinkedList emptyLinkedList = createEmptyLinkedList();

        emptyLinkedList.add("element1");
        emptyLinkedList.add("element2");
        emptyLinkedList.add("element3");

        TestHelper.println("emptyLinkedList",emptyLinkedList);
        TestHelper.println("emptyLinkedList.contains(\"elememt2\")",emptyLinkedList.contains("element2"));
        TestHelper.println("emptyLinkedList.contains(\"elememt4\")",emptyLinkedList.contains("element4"));
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 5.LinkedList 堆栈操作
     *
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     *
     * 1.public void push(E e)
     * 将元素添加到堆栈的的前面，相当于addFirst()
     *
     * 2.public E poll() 等效于 public E pollFirst()
     * 移除并返回堆栈的第一个元素，如果list为空，返回null
     * public E pollLast()
     * 移除并返回堆栈的最后一个元素，如果list为空，返回null。
     *
     * public E pop()
     * 移除并返回堆栈的第一个元素。如果list为空，抛出异常。
     *
     * 3.public E peek() 等效 public E peekFirst()
     * 返回第一个元素，不会移除元素，如果list为空，返回null
     *
     * 4.public E peekLast()
     * 返回最后一个元素，不会移除元素，如果list为空，返回null。
     *
     * 5.public boolean offer(E e)
     * 在尾部添加元素
     * public boolean offerFirst(E e)
     * 在头部添加元素
     * public boolean offerLast(E e)
     * 在尾部添加元素
     *
     *---------------------------------
     * peek和poll都能获取元素，不同的是peek不会删除，poll会删除
     *
     * push和pop：push在list头添加元素，pop返回头部的元素。
     * 两者都可能抛出异常。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void stackTest(){
        LinkedList emptyLinkedList = createEmptyLinkedList();
        emptyLinkedList.add("element1");
        emptyLinkedList.add("element2");
        emptyLinkedList.add("element3");
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 1.public void push(E e),相当于addFirst()
        emptyLinkedList.push("element4");
        TestHelper.println("emptyLinkedList.push(\"element4\")",emptyLinkedList);

        // 2.public E poll()
        TestHelper.println("emptyLinkedList.poll()",emptyLinkedList.poll());

        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 3.public E peek()
        TestHelper.println("emptyLinkedList.peek()",emptyLinkedList.peek());
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 4.public E peekFirst(),等效于public E peek()
        TestHelper.println("emptyLinkedList.peekFirst()",emptyLinkedList.peekFirst());
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 5.public E peekLast()
        TestHelper.println("emptyLinkedList.peekLast()",emptyLinkedList.peekLast());
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 6.public E pop()
        TestHelper.println("emptyLinkedList.pop()",emptyLinkedList.pop());
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 7.public E pollFirst()
        TestHelper.println("emptyLinkedList.pollFirst()",emptyLinkedList.pollFirst());
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        emptyLinkedList.add("element10");
        emptyLinkedList.add("element11");
        emptyLinkedList.add("element12");
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 8.public E pollLast()
        TestHelper.println("emptyLinkedList.pollLast()",emptyLinkedList.pollLast());
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 9.public E peekFirst()
        TestHelper.println("emptyLinkedList.peekFirst()",emptyLinkedList.peekFirst());
        TestHelper.println("emptyLinkedList",emptyLinkedList);

        // 10.public boolean offer(E e)
        // 添加元素到尾部
        emptyLinkedList.offer("offer");
        TestHelper.println("emptyLinkedList.offer(\"offer\")",emptyLinkedList);

        // public boolean offerFirst(E e)
        // 添加元素到头部
        emptyLinkedList.offerFirst("offerFirst");
        TestHelper.println("emptyLinkedList.offerFirst(\"offerFirst\")",emptyLinkedList);

        // public boolean offerLast(E e)
        // 添加元素到尾部
        emptyLinkedList.offerLast("offerLast");
        TestHelper.println("emptyLinkedList.offerLast(\"offerLast\")",emptyLinkedList);
    }
}
