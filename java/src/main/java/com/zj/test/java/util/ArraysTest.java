package com.zj.test.java.util;

import com.zj.test.util.TestHelper;
import org.apache.catalina.User;
import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/23 17:32
 * @description: java.util.Arrays api测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class ArraysTest {

    /**
     * 1.public static <T> java.util.List<T> asList(@NotNull T... a)
     * 将给定的参数转换成一个List对象并返回,返回的List类型是Arrays内部实现类。
     *
     * <p>
     * 注意：
     * 1.返回类型不是java.util.ArrayList,而是Arrays实现的ArrayList同名静态内部类
     * java.util.Arrays.ArrayList<E>: 该类只提供get,set方法,该类没有提供对add的支持，
     * 如果调用add(..)方法会报错:
     * java.lang.UnsupportedOperationException
     * at java.util.AbstractList.add(AbstractList.java:148)
     * at java.util.AbstractList.add(AbstractList.java:108)
     * at com.zj.test.java.util.ArraysTest.test1(ArraysTest.java:34)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * at java.lang.reflect.Method.invoke(Method.java:498)
     * at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     * at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     * at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     * at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     * at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     * at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     * at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     * at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     * at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     * at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
     * at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
     * at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
     *
     * <p>
     * 2.asLisdt(..)返回的List后续不能添加元素。
     */
    @Test
    public void asList() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        TestHelper.println(integers);//[1, 2, 3]
        TestHelper.println(integers.size());//3
        // java.lang.ArrayIndexOutOfBoundsException: 5
        // integers.set(5,6);

        // java.util.Arrays.ArrayList不支持的操作:java.lang.UnsupportedOperationException
        //integers.add(4);
        //TestHelper.println(integers);
        //TestHelper.println(integers.size());

        // asList参数为不同类型测试
        TestHelper.printSubTitle("asList参数为不同类型测试");
        TestHelper.println(Arrays.asList(1,1f,2d,"hello",'c'));
    }

    /**
     * 2.
     * ...copyOf(*,newLength)                用于数组复制的一系列方法
     *注意: 复制得到的数组长度newLength比较灵活，可比原来的数组长度长或短或等于
     * 当newLength < 待复制的数组: 从待复制数组0位置开始复制newLength个元素。
     * 该方法的缺点就是只能从0位置开始复制元素。copyOfRange可以解决这个问题
     *
     * 注：不会修改原来的数组
     */
    @Test
    public void copyOf(){

        // 基础类型数组的复制，这里用int[]测试
        int[] ints = {1,2,3,4};
        TestHelper.println("待复制的数组",Arrays.toString(ints));
        TestHelper.println("待复制的数组长度",ints.length);
        int[] copyInts = Arrays.copyOf(ints,ints.length);
        TestHelper.println("复制得到的数组",Arrays.toString(copyInts));
        TestHelper.println("复制得到的数组长度",copyInts.length);
        TestHelper.println("是否为同一引用",ints==copyInts);
    }

    /**
     * 3.int[] copyOfRange(@NotNull int[] original,int from,int to)
     * 指定复制数组区间内的元素
     *
     * from: 必须[0,length], 否则跑出异常
     * to: to必须>=from
     *
     * 注意:
     * 1.被复制元素在原来数组中的位置是[from,to),需要注意的是to位置的元素不被复制。
     * 2.from==to: 不会复制任何元素。
     *
     * 注： 不会修改原来的数组
     */
    @Test
    public void copyOfRange(){
        int[] ints = {1,2,3,4};
        TestHelper.println("待复制的数组",Arrays.toString(ints));
        TestHelper.println("待复制的数组长度",ints.length);

        int[] ints1 = Arrays.copyOfRange(ints, 1, 1);
        TestHelper.println("复制得到的数组",Arrays.toString(ints1));
        TestHelper.println("复制得到的数组长度",ints1.length);
    }

    /**
     * 4.public static boolean equals(@Nullable T[] a,@Nullable T[] a2)
     * 比较两个同类型的简单类型数组是否相等, 相等的条件是长度和元素值都相等。
     */
    @Test
    public void equals(){
        int[] ints1 = {1,2,3,4};
        int[] ints2 = {1,2,3,4};
        int[] ints3 = {1,2,3,4,5};
        int[] ints4 = {1,2,3};
        TestHelper.println("equals({1,2,3,4},{1,2,3,4})",Arrays.equals(ints1,ints2));
        TestHelper.println("equals({1,2,3,4},{1,2,3,4,5})",Arrays.equals(ints1,ints3));
        TestHelper.println("equals({1,2,3,4},{1,2,3})",Arrays.equals(ints1,ints4));
    }


    /**
     * 5.与equals相比， deppEquals可用来比较复杂类型。
     */
    @Test
    public void deepEquals(){
        String[] s1 = {"hello","world"};
        String[] s2 = {"hello","world"};
        TestHelper.println("deepEquals(\"hello\",\"world\",\"hello\",\"world\")",Arrays.deepEquals(s1,s2));
    }

    /**
     * 6.fill系列方法用来填充替换原来的元素
     * 2个参数的fill方法是替换所有元素,如 fill(int[] a,int val)
     * 4个参数是fill方法是替换连续范围内的所有元素
     *
     * 注意：fill()方法会修改原来的数组
     */
    @Test
    public void fill(){
        int[] ints= {1,2,3,4,5};
        // 替换所有元素
        Arrays.fill(ints,5);
        TestHelper.println("fill(ints,5)",Arrays.toString(ints));

        // 替换范围元素
        Arrays.fill(ints,1,5,9);
        TestHelper.println("fill(ints,1,5,9)",Arrays.toString(ints));
    }

    /**
     * 7.hasCode()              计算数组hash值
     */
    @Test
    public void testHasCode(){
        int[] ints  = {1,2,3,4};
        String[] ss = {"我","爱","Java"};
        TestHelper.println("hasCode({1,2,3,4})",Arrays.hashCode(ints));
        TestHelper.println("hasCode({\"我\",\"爱\",\"Java\"})",Arrays.hashCode(ss));
    }

    /**
     * void parallelSort(@NotNull T[] a)
     * 可以对简单类型或复杂类型按照升序进行排序，该方法会对所有元素进行排序
     *
     * void parallelSort(int[] a, int fromIndex, int toIndex)
     * 可以对[fromIndex,toIndex)内的元素进行升序排序
     * 注: toIndex元素不会被排序
     */
    @Test
    public void parrelSort(){
        int[] ints = {1,3,42,56,7,8,5};
        Arrays.parallelSort(ints);
        TestHelper.println("parallelSort({1,3,42,56,7,8,5}) " ,Arrays.toString(ints));
        int[] ints1 = {1,3,42,56,7,8,5} ;
        Arrays.parallelSort(ints1,2,5);
        TestHelper.println("parallelSort({1,3,42,56,7,8,5},2,4)" ,Arrays.toString(ints1));
    }
}
