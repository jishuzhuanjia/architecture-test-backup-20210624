package com.zj.test.spring.spring_beans;

import com.zj.test.util.TestHelper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 15:46
 * @description: BeanUtils工具类中find*方法的使用
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月21日 20:34:00
 */
@Setter
@Getter
public class FindUnitTest {
    String username;

    private int i;

    public int add(int j){
        return this.i + j;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int add(Integer a, Integer b) {
        return a + b;
    }

    public int add(Integer a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public int add(Integer a, Integer b, Integer c) {
        return a + b + c;
    }

    /**
     * 1.public static Method findDeclaredMethodWithMinimalParameters(Class<?> clazz, String methodName)
     * [Description]
     * 根据Class和方法名获取形参最少的方法
     *
     * [Return]
     * 如果指定名的方法找不到会返回null
     *
     * [Tips]
     * 如果指定名的方法有多个最少参数的方法，会报错：
     * java.lang.IllegalArgumentException: Cannot resolve method 'add' to a unique method. Attempted to resolve to overloaded method with the least number of parameters but there were 2 candidates.
     */
    @Test
    public void findDeclaredMethodWithMinimalParameters() {

        Method addMethod = BeanUtils.findDeclaredMethodWithMinimalParameters(FindUnitTest.class, "add");
        // public int com.zj.test.spring.spring_beans.FindUnitTest.add(int)
        TestHelper.println(addMethod);
    }

    /**
     * 2.Method  findMethod(Class <?> clazz,String  methodName,Class <?>... paramTypes)
     * [Description]
     * 根据方法名和形参类型查找类中的方法
     *
     * [Return]
     * 如果没有找到指定的方法，返回null
     *
     * [Tips]
     * 1.在传递形参类型时，int.class和Integer.class是不同的类型。
     */
    @Test
    public void findMethod(){
        TestHelper.println("findMethod", BeanUtils.findMethod(FindUnitTest.class, "add", Integer.class, int.class));
    }

    /**
     * 3. PropertyDescriptor  findPropertyForMethod(Method  method)
     * [Description]
     * 使用时应该传递parameterTypes参数，否则就是查找无参的名为name的方法。
     *
     * [Return]
     *
     *
     * [tips]
     *
     *
     */
    @Test
    public void getMethod() throws NoSuchMethodException {
        PropertyDescriptor getI1 = BeanUtils.findPropertyForMethod(FindUnitTest.class.getMethod("add", int.class));
        TestHelper.println("findPropertyForMethod测试i属性setter结果", getI1.getName());

    }

    @Test
    public void find_() throws NoSuchMethodException {

        TestHelper.startTest("findPropertyForMethod测试");
        PropertyDescriptor getI = BeanUtils.findPropertyForMethod(FindUnitTest.class.getMethod("getI"));
        TestHelper.println("findPropertyForMethod测试i属性getter结果", getI.getName());

        /*// null
        PropertyDescriptor getI2 = BeanUtils.findPropertyForMethod(BeanUtilsFindTest.class.getMethod("getJ"));
        // java.lang.NoSuchMethodException: com.zj.test.spring.spring_beans.BeanUtilsFindTest.setI()
        TestHelper.println(getI2.getName());*/
        TestHelper.finishTest();
    }
}