package com.zj.test.java.reflect.method;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 15:08
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 数学工具MathUtils
 */
public class MathUtils implements Mathable {
    public boolean isMathable() {
        return true;
    }

    public void varArgsMethod(int... args) {

    }

    /**
     * 加法
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * 除法,除数为0抛出异常
     */
    public static int divide(int a, int b) throws IllegalArgumentException, NullPointerException {

        if (b == 0) {
            throw new IllegalArgumentException("除数不能为0");
        }
        return a / b;
    }

    @Test
    public void test1() {
        Class<?> cls = MathUtils.class;

        Method[] declaredMethods = cls.getDeclaredMethods();

        // 遍历方法
        // 和属性Field一样，从Executable继承了许多相同的方法，如getName(),getModifiers()，这里不再赘述
        for (Method method : declaredMethods) {

            System.out.println("Method name：" + method.getName());

            // 1.获取方法参数个数
            System.out.println("Parameter count：" + method.getParameterCount());

            // 2.获取方法参数
            for (Parameter parameter : method.getParameters()) {
                System.out.println("Parameter Name：" + parameter.getName());
            }

            // 3.获取参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> class1 : parameterTypes) {
                // int类型输出int
                // 字符串类型：
                // class [Ljava.lang.String
                System.out.println(class1);
            }

            // 4.获取方法异常类型
            Class<?>[] exceptionTypes = method.getExceptionTypes();

            for (Class<?> class1 : exceptionTypes) {
                // throws后面的异常都会被返回
                // throws IllegalArgumentException,NullPointerException
                //output:
                // exceptionType: class java.lang.IllegalArgumentException
                // exceptionType: class java.lang.NullPointerException
                System.out.println("exceptionType: " + class1);
            }

            // 5.是否为动态参数，如：public void varArgsMethod(int...args)
            System.out.println("isVarArgs：" + method.isVarArgs());

            // 6.返回指定的注解是否出现
            //method.isAnnotationPresent(annotationClass)

            System.out.println("isDefault：" + method.isDefault());
            System.out.println("-----");
        }

        System.out.println("------------------------------------------");
        // 7.单独测试isDefault方法
        // 作用未知
        Class<?> cls2 = Mathable.class;

        Method[] declaredMethods2 = cls2.getDeclaredMethods();
        for (Method method : declaredMethods2) {
            System.out.println("Method name：" + method.getName());
            System.out.println("isDefault：" + method.isDefault());
        }
    }/* output:
	Method name：main
	Parameter count：1
	Parameter Name：arg0
	class [Ljava.lang.String;
	isVarArgs：false
	isDefault：false
	-----
	Method name：add
	Parameter count：2
	Parameter Name：arg0
	Parameter Name：arg1
	int
	int
	isVarArgs：false
	isDefault：false
	-----
	Method name：divide
	Parameter count：2
	Parameter Name：arg0
	Parameter Name：arg1
	int
	int
	exceptionType: class java.lang.IllegalArgumentException
	exceptionType: class java.lang.NullPointerException
	isVarArgs：false
	isDefault：false
	-----
	Method name：test1
	Parameter count：0
	isVarArgs：false
	isDefault：false
	-----
	Method name：varArgsMethod
	Parameter count：1
	Parameter Name：arg0
	class [I
	isVarArgs：true
	isDefault：false
	-----
	Method name：isMathable
	Parameter count：0
	isVarArgs：false
	isDefault：false
	-----
	------------------------------------------
	Method name：isMathable
	isDefault：false
	 * */

    // test MathUtils
    public static void main(String[] args) {
        System.out.println(MathUtils.add(23, 11));
        System.out.println(MathUtils.divide(10, 2));
        System.out.println(MathUtils.divide(10, 0));
    }
}
