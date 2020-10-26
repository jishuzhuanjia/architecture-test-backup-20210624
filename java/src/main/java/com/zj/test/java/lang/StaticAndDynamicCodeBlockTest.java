package com.zj.test.java.lang;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/26 14:34
 * @description: 静态/动态代码块测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月26日 14:55:44
 */
public class StaticAndDynamicCodeBlockTest {


    /**
     * 1.动态代码块前置不能非static实例成员: Illegal forward reference
     * 因为此时变量还没有实例化
     */ {
        System.out.println("static code block");
        className = "StaticAndDynamicCodeBlockTest";
        className = className;

        // Illegal forward reference
        //int k =i;

        /**
         * 2.赋值的优先级:
         * 构造函数赋值 > 后置赋值 > int i =4 >前置赋值 > int i;
         */
        i = 7;
    }

    int i = 4;

    {
        i = 8;
    }

    public static String className;

    public static void main(String[] args) {
        System.out.println(new StaticAndDynamicCodeBlockTest().i);
    }

    public StaticAndDynamicCodeBlockTest() {
        i = 10000;
    }
}