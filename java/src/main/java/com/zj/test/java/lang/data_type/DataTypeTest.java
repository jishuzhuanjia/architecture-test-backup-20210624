package com.zj.test.java.lang.data_type;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/4 15:45
 * @description: 数据类型 + 进制 测试
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月4日 16:51:07
 */
public class DataTypeTest {

    /**
     * 1.进制
     *
     * 测试：尝试用不同进制的整型，来为变量赋值。
     */

    @Test
    public void test1(){
        /**
         * 1.1、10进制(decimal)
         */

        int decimalInt = 100;
        TestHelper.println("decimal 100",decimalInt);

        /**
         * 1.2、8进制(octal)
         * 八进制书写以数字0开头
         */

        int octalInt = 0144;
        TestHelper.println("octal 0144",octalInt);

        /**
         * 1.3.16进制(Hex)
         * 16进制书写以0x/0X开头，注意第一个也是数字0
         */

        int hexInt = 0x64;
        TestHelper.println("hex 0x64",hexInt);
    }
}
