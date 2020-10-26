package com.zj.test.java.util;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/23 17:32
 * @description: java.util.Arrays常用api测试
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月24日16:01:21
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
     * 2.asLisdt(..)返回的List后续不能增加(add)、删 除(remove)，可以进行查询(get),更新(set)。
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

        // java.lang.UnsupportedOperationException
        //integers.remove(1);

        //TestHelper.println(integers.get(1));//ok
        //integers.set(1, 2); //ok
        // asList参数为不同类型测试
        TestHelper.printSubTitle("asList参数为不同类型测试");
        TestHelper.println(Arrays.asList(1, 1f, 2d, "hello", 'c'));
    }

    /**
     * 2.
     * ...copyOf(*,newLength)                用于数组复制的一系列方法
     * 注意: 复制得到的数组长度newLength比较灵活，可比原来的数组长度长或短或等于
     * 当newLength < 待复制的数组: 从待复制数组0位置开始复制newLength个元素。
     * 该方法的缺点就是只能从0位置开始复制元素。copyOfRange可以解决这个问题
     * <p>
     * 注：不会修改原来的数组
     */
    @Test
    public void copyOf() {
        // 基础类型数组的复制，这里用int[]测试
        int[] ints = {1, 2, 3, 4};
        TestHelper.println("待复制的数组", Arrays.toString(ints));
        TestHelper.println("待复制的数组长度", ints.length);
        int[] copyInts = Arrays.copyOf(ints, ints.length);
        TestHelper.println("复制得到的数组", Arrays.toString(copyInts));
        TestHelper.println("复制得到的数组长度", copyInts.length);
        TestHelper.println("是否为同一引用", ints == copyInts);
    }

    /**
     * 3.int[] copyOfRange(@NotNull int[] original,int from,int to)
     * 指定复制数组区间内的元素
     * <p>
     * from: 必须[0,length], 否则跑出异常
     * to: to必须>=from
     * <p>
     * 注意:
     * 1.被复制元素在原来数组中的位置是[from,to),需要注意的是to位置的元素不被复制。
     * 2.from==to: 不会复制任何元素。
     * <p>
     * 注： 不会修改原来的数组
     */
    @Test
    public void copyOfRange() {
        int[] ints = {1, 2, 3, 4};
        TestHelper.println("待复制的数组", Arrays.toString(ints));
        TestHelper.println("待复制的数组长度", ints.length);

        int[] ints1 = Arrays.copyOfRange(ints, 1, 1);
        TestHelper.println("复制得到的数组", Arrays.toString(ints1));
        TestHelper.println("复制得到的数组长度", ints1.length);
    }

    /**
     * 4.public static boolean equals(@Nullable T[] a,@Nullable T[] a2)
     * 比较两个同类型的简单类型数组是否相等, 相等的条件是长度和元素值都相等。
     */
    @Test
    public void equals() {
        int[] ints1 = {1, 2, 3, 4};
        int[] ints2 = {1, 2, 3, 4};
        int[] ints3 = {1, 2, 3, 4, 5};
        int[] ints4 = {1, 2, 3};
        TestHelper.println("equals({1,2,3,4},{1,2,3,4})", Arrays.equals(ints1, ints2));
        TestHelper.println("equals({1,2,3,4},{1,2,3,4,5})", Arrays.equals(ints1, ints3));
        TestHelper.println("equals({1,2,3,4},{1,2,3})", Arrays.equals(ints1, ints4));
    }

    /**
     * 5.与equals相比， deppEquals可用来比较复杂类型。
     */
    @Test
    public void deepEquals() {
        String[] s1 = {"hello", "world"};
        String[] s2 = {"hello", "world"};
        TestHelper.println("deepEquals(\"hello\",\"world\",\"hello\",\"world\")", Arrays.deepEquals(s1, s2));
    }

    /**
     * 6.fill系列方法用来填充替换原来的元素
     * 2个参数的fill方法是替换所有元素,如 fill(int[] a,int val)
     * 4个参数是fill方法是替换连续范围内的所有元素
     * <p>
     * 注意：fill()方法会修改原来的数组
     */
    @Test
    public void fill() {
        int[] ints = {1, 2, 3, 4, 5};
        // 替换所有元素
        Arrays.fill(ints, 5);
        TestHelper.println("fill(ints,5)", Arrays.toString(ints));

        // 替换范围元素
        Arrays.fill(ints, 1, 5, 9);
        TestHelper.println("fill(ints,1,5,9)", Arrays.toString(ints));
    }

    /**
     * 7.hasCode()              计算数组hash值
     */
    @Test
    public void testHasCode() {
        int[] ints = {1, 2, 3, 4};
        String[] ss = {"我", "爱", "Java"};
        TestHelper.println("hasCode({1,2,3,4})", Arrays.hashCode(ints));
        TestHelper.println("hasCode({\"我\",\"爱\",\"Java\"})", Arrays.hashCode(ss));
    }

    /**
     * 8.void parallelSort(@NotNull T[] a)
     * 可以对简单类型或复杂类型按照升序进行排序，该方法会对所有元素进行排序
     * <p>
     * void parallelSort(int[] a, int fromIndex, int toIndex)
     * 可以对[fromIndex,toIndex)内的元素进行升序排序
     * 注: toIndex元素不会被排序
     * 为并行算法
     */
    @Test
    public void parrelSort() {
        int[] ints = {1, 3, 42, 56, 7, 8, 5};
        Arrays.parallelSort(ints);
        TestHelper.println("parallelSort({1,3,42,56,7,8,5}) ", Arrays.toString(ints));
        int[] ints1 = {1, 3, 42, 56, 7, 8, 5};
        Arrays.parallelSort(ints1, 2, 5);
        TestHelper.println("parallelSort({1,3,42,56,7,8,5},2,4)", Arrays.toString(ints1));
    }

    /**
     * 8.void sort(@NotNull T[] a)
     * 可以对简单类型或复杂类型按照升序进行排序，该方法会对所有元素进行排序
     * <p>
     * void sort(int[] a, int fromIndex, int toIndex)
     * 可以对[fromIndex,toIndex)内的元素进行升序排序
     * 注: toIndex元素不会被排序
     * 为串行算法
     */
    @Test
    public void Sort() {
    }

    /**
     * 9.String toString(T[])       将简单或复杂类型数组转换成String
     * 返回格式形如: [2, 21, 32, 45, 5],字符串用"[]"包围，并且元素之间以','隔开。
     */
    @Test
    public void testToString() {
        int[] ints = {2, 21, 32, 45, 5};
        TestHelper.println("toString(int[])", Arrays.toString(ints));
    }

    /**
     * 10.* binarySearch(..)
     * 二分查找，被查找数组必须经过排序, 如果满足查找的元素有多个，不能保证某一个先被查找到。
     */
    @Test
    public void testBinarySearch() {
        // wait for test
    }

    /**
     * 11.sort()和parallelSort()性能对比
     * <p>
     * 数据量较少时,大约在2万条以内,sort排序更快。
     * <p>
     * 结论: 数据量少时,sort性能更佳。
     */
    @Test
    public void testSort() {
        /*
        量级10000.0: sort用时: 10ms, parallelSort用时: 10ms
        量级20000.0: sort用时: 5ms, parallelSort用时: 4ms
        量级30000.0: sort用时: 9ms, parallelSort用时: 6ms
        量级40000.0: sort用时: 12ms, parallelSort用时: 10ms
        量级50000.0: sort用时: 15ms, parallelSort用时: 8ms
        量级60000.0: sort用时: 30ms, parallelSort用时: 3ms
        量级70000.0: sort用时: 27ms, parallelSort用时: 6ms
        量级80000.0: sort用时: 30ms, parallelSort用时: 5ms
        量级90000.0: sort用时: 28ms, parallelSort用时: 3ms
        量级100000.0: sort用时: 15ms, parallelSort用时: 5ms
        量级110000.0: sort用时: 16ms, parallelSort用时: 1ms
        量级120000.0: sort用时: 14ms, parallelSort用时: 2ms
        量级130000.0: sort用时: 16ms, parallelSort用时: 1ms
        量级140000.0: sort用时: 19ms, parallelSort用时: 2ms
        量级150000.0: sort用时: 18ms, parallelSort用时: 2ms
        量级160000.0: sort用时: 24ms, parallelSort用时: 2ms
        量级170000.0: sort用时: 25ms, parallelSort用时: 3ms
        量级180000.0: sort用时: 22ms, parallelSort用时: 1ms
        量级190000.0: sort用时: 28ms, parallelSort用时: 2ms
        量级200000.0: sort用时: 27ms, parallelSort用时: 1ms
        量级210000.0: sort用时: 29ms, parallelSort用时: 3ms
        量级220000.0: sort用时: 32ms, parallelSort用时: 1ms
        量级230000.0: sort用时: 32ms, parallelSort用时: 1ms
        量级240000.0: sort用时: 32ms, parallelSort用时: 2ms
        量级250000.0: sort用时: 33ms, parallelSort用时: 1ms
        量级260000.0: sort用时: 38ms, parallelSort用时: 2ms
        量级270000.0: sort用时: 35ms, parallelSort用时: 2ms
        量级280000.0: sort用时: 38ms, parallelSort用时: 3ms
        量级290000.0: sort用时: 46ms, parallelSort用时: 5ms
        量级300000.0: sort用时: 48ms, parallelSort用时: 2ms
        量级310000.0: sort用时: 49ms, parallelSort用时: 3ms
        量级320000.0: sort用时: 80ms, parallelSort用时: 2ms
        量级330000.0: sort用时: 67ms, parallelSort用时: 5ms
        量级340000.0: sort用时: 53ms, parallelSort用时: 3ms
        量级350000.0: sort用时: 54ms, parallelSort用时: 2ms
        量级360000.0: sort用时: 77ms, parallelSort用时: 5ms
        量级370000.0: sort用时: 60ms, parallelSort用时: 8ms
        量级380000.0: sort用时: 58ms, parallelSort用时: 4ms
        量级390000.0: sort用时: 54ms, parallelSort用时: 58ms
        量级400000.0: sort用时: 61ms, parallelSort用时: 4ms
        量级410000.0: sort用时: 67ms, parallelSort用时: 5ms
        量级420000.0: sort用时: 66ms, parallelSort用时: 3ms
        量级430000.0: sort用时: 61ms, parallelSort用时: 7ms
        量级440000.0: sort用时: 56ms, parallelSort用时: 2ms
        量级450000.0: sort用时: 56ms, parallelSort用时: 2ms
        量级460000.0: sort用时: 57ms, parallelSort用时: 3ms
        量级470000.0: sort用时: 57ms, parallelSort用时: 3ms
        量级480000.0: sort用时: 60ms, parallelSort用时: 3ms
        量级490000.0: sort用时: 60ms, parallelSort用时: 3ms
        量级500000.0: sort用时: 61ms, parallelSort用时: 3ms
        量级510000.0: sort用时: 61ms, parallelSort用时: 3ms
        量级520000.0: sort用时: 64ms, parallelSort用时: 4ms
        量级530000.0: sort用时: 66ms, parallelSort用时: 3ms
        量级540000.0: sort用时: 66ms, parallelSort用时: 4ms
        量级550000.0: sort用时: 68ms, parallelSort用时: 4ms
        量级560000.0: sort用时: 68ms, parallelSort用时: 4ms
        量级570000.0: sort用时: 71ms, parallelSort用时: 3ms
        量级580000.0: sort用时: 71ms, parallelSort用时: 3ms
        量级590000.0: sort用时: 72ms, parallelSort用时: 4ms
        量级600000.0: sort用时: 74ms, parallelSort用时: 3ms
        量级610000.0: sort用时: 76ms, parallelSort用时: 3ms
        量级620000.0: sort用时: 78ms, parallelSort用时: 3ms
        量级630000.0: sort用时: 78ms, parallelSort用时: 3ms
        量级640000.0: sort用时: 80ms, parallelSort用时: 3ms
        量级650000.0: sort用时: 79ms, parallelSort用时: 4ms
        量级660000.0: sort用时: 86ms, parallelSort用时: 4ms
        量级670000.0: sort用时: 89ms, parallelSort用时: 5ms
        量级680000.0: sort用时: 87ms, parallelSort用时: 4ms
        量级690000.0: sort用时: 89ms, parallelSort用时: 5ms
        量级700000.0: sort用时: 96ms, parallelSort用时: 4ms
        量级710000.0: sort用时: 95ms, parallelSort用时: 5ms
        量级720000.0: sort用时: 92ms, parallelSort用时: 4ms
        量级730000.0: sort用时: 95ms, parallelSort用时: 4ms
        量级740000.0: sort用时: 94ms, parallelSort用时: 5ms
        量级750000.0: sort用时: 97ms, parallelSort用时: 6ms
        量级760000.0: sort用时: 98ms, parallelSort用时: 6ms
        量级770000.0: sort用时: 97ms, parallelSort用时: 6ms
        量级780000.0: sort用时: 96ms, parallelSort用时: 6ms
        量级790000.0: sort用时: 100ms, parallelSort用时: 7ms
        量级800000.0: sort用时: 103ms, parallelSort用时: 6ms
        量级810000.0: sort用时: 115ms, parallelSort用时: 7ms
        量级820000.0: sort用时: 116ms, parallelSort用时: 6ms
        量级830000.0: sort用时: 110ms, parallelSort用时: 6ms
        量级840000.0: sort用时: 104ms, parallelSort用时: 6ms
        量级850000.0: sort用时: 106ms, parallelSort用时: 6ms
        量级860000.0: sort用时: 114ms, parallelSort用时: 6ms
        量级870000.0: sort用时: 114ms, parallelSort用时: 7ms
        量级880000.0: sort用时: 120ms, parallelSort用时: 7ms
        量级890000.0: sort用时: 120ms, parallelSort用时: 6ms
        量级900000.0: sort用时: 121ms, parallelSort用时: 6ms
        量级910000.0: sort用时: 117ms, parallelSort用时: 7ms
        量级920000.0: sort用时: 118ms, parallelSort用时: 5ms
        量级930000.0: sort用时: 119ms, parallelSort用时: 6ms
        量级940000.0: sort用时: 119ms, parallelSort用时: 7ms
        量级950000.0: sort用时: 123ms, parallelSort用时: 7ms
        量级960000.0: sort用时: 138ms, parallelSort用时: 9ms
        量级970000.0: sort用时: 144ms, parallelSort用时: 6ms
        量级980000.0: sort用时: 129ms, parallelSort用时: 9ms
        量级990000.0: sort用时: 127ms, parallelSort用时: 6ms
        量级1000000.0: sort用时: 131ms, parallelSort用时: 7ms*/
        float size = 1;
        Random random = new Random();
        for (; size <= 100; size += 1) {
            int[] ints = new int[(int) (size * 10000)];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = random.nextInt(ints.length + 1);
            }
            int[] intsclone = Arrays.copyOf(ints, ints.length);
            long startTime = System.currentTimeMillis();
            Arrays.sort(ints);
            long useTime1 = System.currentTimeMillis() - startTime;
            startTime = System.currentTimeMillis();
            Arrays.parallelSort(ints);
            long useTime2 = System.currentTimeMillis() - startTime;
            TestHelper.println("量级" + (size * 10000), "sort用时: " + useTime1 + "ms, parallelSort用时: " + useTime2 + "ms");
            Arrays.parallelSort(intsclone);
        }
    }
}