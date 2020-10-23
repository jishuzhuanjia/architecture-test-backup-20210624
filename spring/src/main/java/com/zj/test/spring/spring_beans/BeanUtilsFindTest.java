package com.zj.test.spring.spring_beans;

import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 15:46
 * @description: BeanUtils工具类中find*方法的使用
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月21日 20:34:00
 */
public class BeanUtilsFindTest {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void getJ() {

    }

    /**
     * 2位数相加
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 2位数相加
     */
    public int add(Integer a, Integer b) {
        return a + b;
    }

    /**
     * 3位数相加
     */
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    /**
     * 3位数相加
     */
    public int add(Integer a, Integer b, Integer c) {
        return a + b + c;
    }

    @Test
    public void find_() throws NoSuchMethodException {
        TestHelper.startTest("BeanUtils.find*方法测试");
        /**
         * 1.public static Method findDeclaredMethodWithMinimalParameters(Class<?> clazz, String methodName)
         * 根据Class和方法名获取参数最少的方法
         * 如果方法找不到会返回null
         *
         * 注意：如果指定名的方法有多个最少参数的方法，会报错：
         * java.lang.IllegalArgumentException: Cannot resolve method 'add' to a unique method. Attempted to resolve to overloaded method with the least number of parameters but there were 2 candidates.
         */
        //Method add = BeanUtils.findDeclaredMethodWithMinimalParameters(BeanUtilsFindTest.class, "add");
        //TestHelper.println(add);

        TestHelper.printSubTitle("findMethod测试");
        // null
        TestHelper.println("findMethod结果", BeanUtils.findMethod(BeanUtilsFindTest.class, "add", Integer.class, int.class));

        /**
         * 2.public static PropertyDescriptor  findPropertyForMethod(Method  method)
         * 获取setter/getter所对应的属性描述对象PropertyDescriptor
         * 如果方法不是setter/getter，返回null
         *
         * */
        TestHelper.printSubTitle("findPropertyForMethod测试");
        PropertyDescriptor getI = BeanUtils.findPropertyForMethod(BeanUtilsFindTest.class.getMethod("getI"));
        TestHelper.println("findPropertyForMethod测试i属性getter结果", getI.getName());

        /**
         * 3.public reflect.Method getMethod(@NonNls @NotNull String name,
         *                                 Class<?>... parameterTypes)
         * 使用时应该传递parameterTypes参数，否则就是查找无参的名为name的方法。
         * */
        PropertyDescriptor getI1 = BeanUtils.findPropertyForMethod(BeanUtilsFindTest.class.getMethod("setI", int.class));
        TestHelper.println("findPropertyForMethod测试i属性setter结果", getI1.getName());

        /*// null
        PropertyDescriptor getI2 = BeanUtils.findPropertyForMethod(BeanUtilsFindTest.class.getMethod("getJ"));
        // java.lang.NoSuchMethodException: com.zj.test.spring.spring_beans.BeanUtilsFindTest.setI()
        TestHelper.println(getI2.getName());*/
        TestHelper.finishTest();
    }
}