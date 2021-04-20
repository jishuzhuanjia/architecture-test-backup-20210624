package com.zj.test.java.lang;

import com.zj.test.util.TestHelper;
import lombok.ToString;
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
     * 1.public static String valueOf(Object)测试
     * 将对象转换成String对象
     *
     *
     * 原理：该方法会调用Object对象的toString方法获取返回值
     *
     * 注意：想要获取正确的返回值，需要对自定义的类型实现toString方法
     */
    @Test
    public void valueOf(){
        Object sObject = null;
        // String.valueOf(null): null
        TestHelper.println("String.valueOf(null)",String.valueOf(sObject));

        // String.valueOf(1111): 1111
        TestHelper.println("String.valueOf(1111)",String.valueOf(1111));

        // String.valueOf(false): false
        TestHelper.println("String.valueOf(false)",String.valueOf(false));

        Integer i = 13;
        TestHelper.println("String.valueOf(13)",String.valueOf(i));

        User user = new User();
        user.username="zhou jian";
        user.password="123456";
        String.valueOf(user);
        // 如果没有覆盖默认toString: user: com.zj.test.java.lang.StringTest$User@2f333739
        // 覆盖后(这里使用的lombok插件生成toString):
        // user: StringTest.User(username=zhou jian, password=123456)
        TestHelper.println("user",user);
    }

    @ToString
    class User {
        String username;
        String password;
    }
}
