package com.zj.test.java.lang;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/17 16:54
 * @description: String测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class StringTest {

    /**
     * author: 2025513
     *
     * 1.public static String valueOf(* obj)测试
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void valueOf(){
        Object sObject = null;
        //Object -> return (obj == null) ? "null" : obj.toString();
        TestHelper.println("String.valueOf(null)",String.valueOf(sObject));

        // int -> return Integer.toString(i);
        TestHelper.println("String.valueOf(1111)",String.valueOf(1111));

        // boolean -> return b ? "true" : "false";
        TestHelper.println("String.valueOf(false)",String.valueOf(false));
    }
}
