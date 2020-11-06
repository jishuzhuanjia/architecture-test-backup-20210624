package com.zj.test.java.io.serialize_unserialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @CreateTime：2020年2月9日 下午3:41:39
 * @Author：zhoujian
 * @QQ：2025513
 * @FileDescription：反序列化构造函数调用顺序
 * @IsFinished：false
 */

public class UnSerializeTest {


    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\B.txt");

        B b = new B();
        b.setiA(100);
        b.setbA(true);
        b.sB = "new String in B";

        try {
            //1.序列化
            FileOutputStream fos = new FileOutputStream(file);

            //此时并没有调用ops.writeObject(b),但是已经在桌面生成了B.txt文件了,且大小为4b;
            // 因为此时并没有开始序列化，所以就算类B没有实现序列化接口也不会报错
            ObjectOutputStream ops = new ObjectOutputStream(fos);

            // 开始序列化，如果类没有实现序列化接口，会报错
            // java.io.NotSerializableException: basic.javabasic.advance.day05Serialize.B
            // 此时B.txt文件大小增大为991字节
            // 添加序列化接口后，报错消失
            ops.writeObject(b);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println("开始序列化");
        //2.反序列化
        B b2;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            b2 = (B) objectInputStream.readObject();

            System.out.println("sB:" + b2.sB);
            System.out.println("iA:" + b2.getiA());
            System.out.println("bA:" + b2.bA);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //3.输出：
        // A constructor called
        // B constructor called
        // 开始序列化
        // A constructor called
        // sB:new String in B
        // iA:0
        // bA:false

        // 可以看到A中的属性没有被序列化，当我们为A实现Serializable接口后:
        // A constructor called
        // B constructor called
        // 开始序列化
        // sB:new String in B
        // iA:100
        // bA:true
        // 此时A中的属性也被序列化了。

        /**
         * 4.注意：
         * 1.反序列化过程中，子类构造函数没有调用。
         * 2.如果父类中定义了其他有参的构造函数，必须添加无参的构造函数。因为反序列化会调用默认构造函数。
         * */
    }

}

//序列化父类
class A implements Serializable {
    private int iA;

    public int getiA() {
        return iA;
    }

    public void setiA(int iA) {
        this.iA = iA;
    }

    boolean bA;

    public boolean isbA() {
        return bA;
    }

    public void setbA(boolean bA) {
        this.bA = bA;
    }

    public A(String s) {
        System.out.println("A constructor called");
    }

    public A() {
        System.out.println("A constructor called");
    }
}

//序列化子类
class B extends A implements Serializable {
    String sB = "String in B";

    // 实例化B时，首先会调用父类的构造函数，然后才执行子类构造函数。
    public B() {
        System.out.println("B constructor called");
    }
}
