/**
 *序列化.java
 *Creation time:2019年1月16日 下午4:59:49
 *Author:zhoujian
 *QQ:2025513
 *e-mail:www.mfcdebugger@163.com
 */
package com.zj.test.java.io.serialize_unserialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**(1).简介
 * 序列化允许将一个对象写入到一个byte流中,并且可以从其他的地方把byte流中
 * 的数据读出来,重新构造一个相同的对象
 *
 *(2).作用
 * 这样就允许你将对象通过网络进行传输,并且可以持久化到数据库、文件中。序列化是RMI、EJB、JNI等技术的技术基础,
 *
 * Serializable接口
 * 注意:真的可以说这只是个接口,因为它没有任何的方法和属性，
 * 实现该接口只是简单的标记类准备支持序列化功能。
 * */

/**
 * (3).不实现Serializable接口对于对象读入(反序列化)和写出(序列化)的影响
 * 1).序列化: 如果不实现Serializable接口在序列化的过程会抛出java.io.NotSerializableException,
 * 但是代码执行没有被中断,是可以输出文件的(会按照给定的文件名生成指定的文件),会紧接着执行下面的代码。
 *
 * 2).反序列化: 可是读取对象时发生java.io.NotSerializableException且抛出java.io.WriteAbortedException,
 * 也就是读取被终止,并没有生成对象,接着下面的代码没有继续执行。
 *
 * 总结： 序列化和反序列化都需要实现Serializable接口。
 * */

/**(4).private static final serialVersionUID是否一定需要声明？
 * 不会影响到对象的序列和反序列化,因为jvm会动态生成。
 * 不过在实现了该接口后,IDE一般会提示添加。
 * 因此序列化成功与否的关键在于是否实现Serializable接口。
 * */

/**(5).能够序列化的类的条件:
 *  1.类本身实现了Serializable接口或父类实现了Serializable接口。
 *  2.类成员变量实现了序列化接口,除非是transient修饰的变量。
 *
 *(6).成员变量修饰符对序列化的影响？
 * 1。static变量可以序列化
 * 2.权限修饰符public,private等都可以序列化。
 *
 *(7).transient如何使用？
 *    1.若引用类型的成员被transient修饰,则该引用类型不用实现序列化接口,
 *    否则必须要添加transient关键字来标识,否则序列化的时候会抛出NotSerializableException。
 *    2.线程相关的属性。如Thread变量,序列化并没有意义
 *    3.需要访问IO，本地资源、网络资源等的属性。
 */

/**(8).是否子类和父类都要实现序列化接口?
 *    1.答案是否定的,只要保存的对象的类实现接口即可。 如果父类没有实现接口,则需要有默认构造函数。
 *     经测试: 如果父类不实现接口,则不会对父类中的属性进行序列化,此时反序列化过程会调用
 *    父类的默认构造方法,如果有其他构造方法而没有默认构造方法,则抛出java.io.InvalidClassException: 测试.炸瓦.集合.排序.序列化;
 *    no valid constructor,而且该被序列化的子类构造函数不会被调用。如果父类实现了序列化接口,则不会调用父类的和本身的构造函数。
 * */

/**(9).反序列化构造函数的调用
 *    1.父类没有实现接口: 此时父类中的成员变量不会被序列化,反序列化时会调用父类默认的构造函数,如果没有默认的构造函数 - 父类默认构造函数被调用,子类没有调用
 *    java.io.InvalidClassException:frametest.basicframe.集合.排序.ConstrutorInvokeTest; no valid constructor
 *
 *    2.父类有接口: 序列化会序列化父类中可序列化成员.反序列化过程会反序列化子类和父类所有的可序列化成员.此时直接通过流来创建对象而不通过构造函数
 *       - 父子构造函数都没有调用
 *    注: 以上两种情况子类中的构造函数都没有调用。
 * */
/* ------------------------------------------------------------------------------------------------- */

/** 异常 */

/*
 * 忘记调用ObjectOutputStream的writeObejct方法: 1.虽然没有调用写出方法 ,但是文件此时已经存在。 2.反序列化抛出异常
 * java.io.EOFException at
 * java.io.ObjectInputStream$BlockDataInputStream.peekByte(ObjectInputStream.
 * java:2917) at
 * java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1502) at
 * java.io.ObjectInputStream.readObject(ObjectInputStream.java:422) at
 * frametest.basicframe.集合.排序.序列化.main(序列化.java:156)
 */
public class SerializeTest extends 父类 {
    /***/
    private static String school = "qqhaer";
    public String name;
    public int age;
    public int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SerializeTest(String name, int age) {
        super(1);
        System.out.println("subclass constructor invoked!");

        this.name = name;
        this.age = age;
    }

    /***/
    // private static final long serialVersionUID = 6077818435794912002L;
    @Override
    public String toString() {
        return year + "," + name + "," + age + "," + school;
    }

    /***/

    public static void main(String[] args) {

        /* 对象序列化到本地 */

        File file = new File("C:\\Users\\Administrator\\Desktop\\1.txt");
        // try {
        // // OutputStream
        //
        // ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        //
        // ConstrutorInvokeTest cit = new ConstrutorInvokeTest("zhoujian");
        // cit.setParentName("zhouzilai");
        // System.out.println("before:" + cit.getParentName() + "," + cit.childName);
        // oos.writeObject(cit);
        // /*
        // * 序列化 xlh = new 序列化("周健",24); 序列化.school = "beida"; xlh.year = "2020";
        // * System.out.println("before"+xlh.toString()); oos.writeObject(xlh);
        // */
        //
        // // 需要关闭
        // oos.close();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        //
        // System.out.println("serialize success!");

        // 从本地读取
        // File file = new File("C:\\Users\\Administrator\\Desktop\\1");
        try {

            // 异常为类未找到异常,而不是类转化异常
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                /*
                 * 序列化 xlh = (序列化) ois.readObject(); System.out.println("after" +
                 * xlh.toString());
                 */

                ConstrutorInvokeTest cit1 = (ConstrutorInvokeTest) ois.readObject();
                System.out.println("after:" + cit1.getParentName() + "," + cit1.childName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // 需要关闭
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("unserialize success!");
    }
}

class 父类 implements Serializable {

    protected String year = "2019";

    public 父类(int i) {
        System.out.println("super class constructor invoked!");
    }
}

/** 构造函数调用测试 */
class Parent implements Serializable {

    private String parentName;

    public Parent() {
        System.out.println("Parent's Construtor called!");
    }

    public Parent(String name) {
        parentName = name;
    }

    public void setParentName(String name) {
        parentName = name;
    }

    public String getParentName() {
        return parentName;
    }
}

class ConstrutorInvokeTest extends Parent implements Serializable {

    String childName;

    /* 子类会调用父类默认构造函数,如果没有默认的构造函数需要指定其他的构造函数 */
    public ConstrutorInvokeTest(String name) {
        // super("unnamed");
        this.childName = name;
    }

    public ConstrutorInvokeTest() {// super("unnamed");
        System.out.println("Child's  Construtor called!");
    }
}