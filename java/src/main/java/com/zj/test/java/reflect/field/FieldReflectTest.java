package com.zj.test.java.reflect.field;

import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 11:31
 * @description: 属性反射测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
/**
 * java.lang.Class api清单：
 * 1.public Field getField(String name)
 * 获取本类或父类或继承树中实现的接口中的指定名属性
 * 只能获取public [static]修饰的属性
 *
 * 2.public Field[] getFields() throws SecurityException
 * 获取符合1的所有属性组成的集合
 *
 * 3.public Field getDeclaredField(String name)
 * 获取本类中指定名的属性
 * 只能获取本类中定义的属性，可以是[public|protected|private|缺省] [static]修饰的属性。
 *
 * 4.public Field[] getDeclaredFields() throws SecurityException
 * 获取符合3的所有属性集合
 *
 * */
public class FieldReflectTest {
    private Class testClass = ReflectTestClass.class;

    /**
     * 1.通过Class获取属性Field对象
     * */
    @Test
    public void test1() throws NoSuchFieldException {
        TestHelper.startTest("Class获取Field对象");
        /** 001.java.lang.Class.getField(name)
         * 作用：获取该类和父类以及实现的接口中的public [static]修饰的名为name的属性对象Field。
         *
         * 注意：只能获取权限修饰符为public的属性：
         * private、protected和缺省的权限修饰符修饰的属性都无法获取。
         */
        // java.lang.NoSuchFieldException: privateStaticInt
        //Field privateInt = testClass.getField("privateStaticInt");

        // java.lang.NoSuchFieldException: privateInt
        //Field privateInt = testClass.getField("privateInt");

        Field publicInt = testClass.getField("publicInt");
        Field publicStaticInt = testClass.getField("publicStaticInt");

        // 获取缺省修饰符修饰的属性
        // java.lang.NoSuchFieldException: defaultInt
        //Field defaultInt = testClass.getField("defaultInt");

        // 获取父类中的私有属性
        // java.lang.NoSuchFieldException: parentPrivateInt
        // Field parentPrivateInt = testClass.getField("parentPrivateInt");

        // 获取父类中的public属性
        // ok
        Field parentPublicInt = testClass.getField("parentPublicInt");

        // 获取父类中的public static 属性
        // ok
        Field parentPublicStaticInt = testClass.getField("parentPublicStaticInt");

        Field interfacePublicStaticInt = testClass.getField("interfacePublicStaticInt");
        // 获取父类中的缺省权限修饰符修饰的属性
        // java.lang.NoSuchFieldException: parentDefaultInt
        //Field parentDefaultInt = testClass.getField("parentDefaultInt");

        // 全部是public修饰的属性
        TestHelper.startTest("getFields()返回的值: ");
        for(Field field: testClass.getFields()){
            TestHelper.println(field);
        }

        /**
         * 002.java.lang.Class#getDeclaredField(java.lang.String)
         * 作用：只能获取本类，不能获取父类以及接口中的属性。
         *
         * 注意：可以获取[public|private|protected] [static]修饰的属性。
         */
        TestHelper.startTest("Class#getDeclaredField(java.lang.String)");
        Field privateInt = testClass.getDeclaredField("privateInt");
        Field privateStaticInteger = testClass.getDeclaredField("privateStaticInteger");
        Field defaultInt = testClass.getDeclaredField("defaultInt");

        // java.lang.NoSuchFieldException: parentPublicInt
        //Field parentPublicInt1 = testClass.getDeclaredField("parentPublicInt");

        // 全部都是本类中的属性，不包含父类中的属性
        TestHelper.startTest("getDeclaredFields()返回的值:");
        for (Field field: testClass.getDeclaredFields()){
            TestHelper.println(field);
        }
        TestHelper.finishTest();
    }

    /**
     * 2.Field对象常用api测试
     *
     * api清单
     * public String getName()              获取属性名
     *
     * public reflect.AnnotatedType getAnnotatedType()          通过AnnotatedType.getType()可获取Type对象表示参数类型
     * public Class<?> getType()                                获取参数类型，返回类型为Class
     *
     * public int getModifiers()                                修饰符枚举，不同的值代表不同的关键字组合，2表示private
     *
     * public annotation.Annotation[] getAnnotations()          获取属性上所有的注解，如果没有返回length为0的非null数组
     * public T getAnnotation(@NotNull Class<T>)                根据注解的类型获取指定的注解，找不到返回null
     * public T[] getAnnotationsByType(@NotNull Class T)        根据注解类型获取指定的注解数组，如果找不到返回Length为0的非null数组
     *
     * public boolean isAccessible()                            是否跳过Java语言访问检查,默认false，表示强制执行Java语言访问检查
     *
     * public boolean isEnumConstant()                          属性是否为enum类型常量，不包括enum类型的属性
     *
     * public String toGenericString()                          通用字符串表示，如：
     *                                                          private int com.zj.test.java.reflect.field.ReflectTestClass.privateInt
     * public String toString()                                 输出同toGenericString
     */
    @Test
    public void test2() throws NoSuchFieldException {
        TestHelper.startTest("Field对象的使用");
        // 获取Field对象: private int privateInt
        Field privateInt = testClass.getDeclaredField("privateInt");
        TestHelper.println("测试Field对象", privateInt);
        // api调用展示
        TestHelper.println("getName" , privateInt.getName());
        TestHelper.println("getAnnotatedType" , privateInt.getAnnotatedType());
        // int
        // getAnnotatedType.getType: 返回类型为java.lang.reflect.Type
        TestHelper.println("getAnnotatedType.getType" , privateInt.getAnnotatedType().getType());
        TestHelper.println("getModifiers" , privateInt.getModifiers());
        TestHelper.println("方法修饰符", Modifier.toString(privateInt.getModifiers()));
        // Class<?> getType
        // 获取属性类型
        // 返回的是Class对象
        TestHelper.println("getType" , privateInt.getType());
        // public annotation.Annotation[] getAnnotations()
        // 获取属性上所有的注解
        // 如果没有注解返回的数组!=null，size为0
        TestHelper.println("getAnnotations.length" , privateInt.getAnnotations().length);
        // 根据注解类型获取属性上的注解，包括指定注解类型的子注解类型
        // 如果没有找到返回null
        // 因为相同的注解不允许重复添加到属性上
        TestHelper.println("getAnnotation(Class)找到" , privateInt.getAnnotation(Value.class));
        TestHelper.println("getAnnotation(Class)没有找到" , privateInt.getAnnotation(ResponseBody.class));
        TestHelper.println("getAnnotationsByType(Class).length" , privateInt.getAnnotationsByType(ResponseBody.class).length);
        // false
        TestHelper.println("isAccessible", privateInt.isAccessible());
        // false
        TestHelper.println("非枚举类型属性isEnumConstant", privateInt.isEnumConstant());

        // 测试枚举类型属性isEnumConstant()返回值
        Field interfacePublicStaticInt = FieldReflectTestEnum.class.getField("MALE");
        TestHelper.println("枚举类型常量isEnumConstant",interfacePublicStaticInt.isEnumConstant());
        TestHelper.println("枚举类型属性isEnumConstant",FieldReflectTestEnum.class.getDeclaredField("other").isEnumConstant());
        // private int com.zj.test.java.reflect.field.ReflectTestClass.privateInt
        TestHelper.println("toString",privateInt.toString());
        // private int com.zj.test.java.reflect.field.ReflectTestClass.privateInt
        TestHelper.println("toGenericString",privateInt.toGenericString());
    }

    @Test
    public void test3() throws NoSuchFieldException, IllegalAccessException {
        TestHelper.println("Field演示");
        ReflectTestClass reflectTestClass = new ReflectTestClass();
        reflectTestClass.publicInt=100;
        TestHelper.println("被获取属性的对象: " + reflectTestClass);
        TestHelper.println("被获取属性的类型: " + "int");
        Field publicInt = ReflectTestClass.class.getField("publicInt");
        TestHelper.println("get",publicInt.get(reflectTestClass));
        // int无法转换成boolean
        // java.lang.IllegalArgumentException: Attempt to get int field "com.zj.test.java.reflect.field.ReflectTestClass.publicInt" with illegal data type conversion to boolean
        //TestHelper.println("getBoolean",publicInt.getBoolean(reflectTestClass));
        // ok
        // int可以转换成float,因为小类型转大类型
        // 但是float不能转换成int
        TestHelper.println("getFloat",publicInt.getInt(reflectTestClass));
        TestHelper.println("getDouble",publicInt.getDouble(reflectTestClass));
        TestHelper.println("getLong",publicInt.getLong(reflectTestClass));

        TestHelper.finishTest();
    }
}
