package com.zj.test.java.lang;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/26 13:38
 * @description: lambda表达式测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月26日 14:24:35
 */
public class LambDaTest {
    /**
     * 1.在Java8, 之前我们想要将行为传入函数，仅有的选择就是匿名内部类。Java8发布以后，lambda表达式将大量替代匿名内部类的使用
     */

    /**
     * 2.lambda表达式的功能相当强大，用()->就可以代替整个匿名内部类
     *
     * <p>入门使用</p>
     */
    @Test
    public void test1() {
        /*
        正常的代码:
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("LambDaTest.test1(): this is a thread created by normal code");
            }
        }).start();*/

        //lambda表达式实现
        new Thread(() -> {
            System.out.println("LambDaTest.test1(): this is a thread created by lambda");
        }).start();
    }

    /**
     * 3.labmda表达式代表的是一个接口,所以使用lambda表达式的函数方法参数类型需要是接口。
     * <p>
     * 注意:
     * 1.接口未实现的方法只能有一个,如对于接口:
     * public interface LambdaTestInterface {
     * <p>
     * public void sayHello();
     * <p>
     * public void eat();
     * <p>
     * }
     * <p>
     * <p>
     * 语句: LambdaTestInterface testInnerClass = ()->{
     * <p>
     * };
     * <p>
     * 会编译报错：
     * Multiple non-overriding abstract methods found in interface com.zj.test.java.lang.LambDaTest.LambdaTestInterface
     * <p>
     * 2.对于只有一个未实现方法的接口，如:
     * public interface LambdaTestInterface {
     * public void sayHello();
     * }
     * <p>
     * // 下面语句表示实现sayHello方法，但是不作任何事。
     * LambdaTestInterface testInnerClass = ()->{
     * };
     * testInnerClass.sayHello();
     * <p>
     * // 而该语句实现了sayHello方法： 打印“Hello World”
     * LambdaTestInterface testInnerClass = ()->{
     * System.out.println("Hello World");
     * };
     * testInnerClass.sayHello();
     */
    public interface LambdaTestInterface {
        public void sayHello();
    }

    @Test
    public void test2() {
        LambdaTestInterface testInnerClass = () -> {
            System.out.println("Hello World");
        };

        testInnerClass.sayHello();
    }

    /**
     * 4.lambda表达式简化代码实例1: 集合的遍历
     */
    @Test
    public void test3() {
        List<String> list = Arrays.asList("I", "Love", "You", "forever");

        //1.通过for循环获取集合元素
        //对于for(element:collection): collection不能为null,size或length可为0
        for (String s : list)
            TestHelper.println("loop", s);

        //2.通过labmda表达式获取集合元素
        //相当于实现Cosumer接口accept(T t)方法
        list.forEach((s) -> {
            TestHelper.println("lambda", s);
        });
    }

    /**
     * 5.与函数式接口Predicate配合使用
     * Predicate接口非常适用于做过滤。
     * Predicate接口常用于传递boolean表达式，与labmda一起简化开发。
     */
    @Test
    public void test4() {
        List<String> strings = Arrays.asList("Java", "Jenkis", "C", "MySQL", "jdk8");
        predicateTest(strings, x -> x.startsWith("J"));
    }

    private void predicateTest(List<String> languages, Predicate<String> condition) {
        List<String> collect = languages.stream().filter(x -> condition.test(x)).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("LambDaTest.test1(): this is a thread created by normal code");
            }
        }).start();
    }
}
