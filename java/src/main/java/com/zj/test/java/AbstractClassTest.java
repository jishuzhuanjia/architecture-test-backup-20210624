package com.zj.test.java;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/**
 * @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/26 14:57
 * @description: 抽象类测试
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月26日 15:05:18
 *
 * NOTE:
 * 抽象类不一定有抽象方法，abstract类不能实例化。
 *
 * 没有抽象方法的抽象类，不能够实例化。
 *
 * 带有抽象方法的抽象类，继承该类需要实现所有抽象方法，除非实现类本身也是抽象类。
 *
 * 单元测试类不能是抽象的，否则无法实例化。
 *
 * 抽象类静态方法和静态变量可访问
 *
 */
public class AbstractClassTest {
    /**
     * 1.没有抽象方法的abstract类可以实例化吗?
     * <p>
     *
     * 【结论】
     * 不能
     */
    @Test
    public void testAbstractClassCanInstantiated() {
        /**
         * 'AbstractClass' is abstract; cannot be instantiated
         */
        //AbstractClass abstractClass = new AbstractClass();

    }

    /**
     * <p>
     *     2.测试: 抽象类静态方法和静态变量是否可访问
     * </p>
     *
     * 【出入参记录】
     * [main] - 抽象类的静态方法
     * [main] - staticString
     *
     * 【结论】
     * 可以
     *
     * 【注意点】
     *
     */
    @Test
    public void test(){
        AbstractClass2.staticMethod();
        TestHelper.println(AbstractClass2.staticString);
    }
}

/**
 * 没有抽象方法的抽象类，不能够实例化
 */
abstract class AbstractClass {

}

/**
 * 带有抽象方法的抽象类，继承该类需要实现所有抽象方法，除非实现类本身也是抽象类。
 */
abstract class AbstractClass2 {
    public static String staticString="staticString";

    public static void staticMethod(){
        TestHelper.println("抽象类的静态方法");
    }

    public abstract void method1();
}

/**
 * 继承抽象类，如果子类非abstract，需要实现所有抽象方法
 */
class Class1 extends AbstractClass2 {
    @Override
    public void method1() {
        TestHelper.println("Class1.method1");
    }
}

/**
 * 继承抽象类，如果子类是abstract的，则可以不实现抽象方法。
 */
abstract class Class2 extends AbstractClass2 {

}


