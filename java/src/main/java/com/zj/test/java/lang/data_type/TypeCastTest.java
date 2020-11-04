package com.zj.test.java.lang.data_type;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/4 16:18
 * @description: 类型转换测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月4日 16:51:03
 */
public class TypeCastTest {

    /**
     * 1.Java自动类型转换
     */
    public void autoTypeCast() {

        /*
        1.1.Java整型字面量会自动类型转换:

        整型的自动转换: java中整型字面量默认是int类型的。
        如果字面量在表达式左边类型的取值范围之内，会自动进行类型转换,
        如果不在表达式左边类型的取值范围之内，则会提示需要强制类型转换。
        */
        byte b1 = 10;// 字面量int 10在byte范围[128,127),自动转换成byte
        byte b2 = 127;
        byte b3 = (byte) 128;// 字面量int 128不在在byte范围[128,127),需要强制转换，否则报错。

        /*
        1.2.注意：
        Java浮点型字面量不会自动类型转换到float,虽然字面量的值在float的取值范围之内。

        Java中浮点型字面量默认是double类型
         */
        float f1 = (float) 1.0;//虽然字面量的值在float的取值范围之内，但是不会自动将double->float。需要强制类型转换,否则报错。

        // 如何将浮点字面量转换成float?
        float f2 = 1.0f;//在浮点数字面量后加上f,字面量就成了float类型。
    }

    /**
     * 2.强制类型转换
     */
    public void typeCast() {
        short s1 = 10;
        short s2 = 11;

        short s3 = (short) (s1 + s2);//s1+s2的类型为int,此处需要强制类型转换

        short s4 = (short) (s1 + 10);//s1+10类型为int

        int i1 = s1;//小类型可以直接赋值给大类型

        short s5 = (short) i1; //大类型赋值给小类型，需要强制类型转换。
    }
}
