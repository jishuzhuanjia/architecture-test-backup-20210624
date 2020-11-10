package com.zj.test.java.lang;

import com.zj.test.util.TestHelper;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/9 20:57
 * @description: java语法测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class LangTest {

    /**
     * 1.Integer对象比较
     *
     * 结论: 自动装箱得到的Integer对象,只要值在-128~127之间，只要值相等，则引用相同。
     * 因为Integer.IntegerCache缓存了值在128~127之间的Integer对象，如果值在此期间，则直接返回
     * 缓存中的Integer对象。
     *
     * 需要注意的是，通过构造函数Integer(value)构造的对象是新的对象，而不是从缓存中获取。
     *
     * */
    @Test
    public void test1() {
        Integer integer1 = -128;
        Integer integer2 = -128;
        // true
        TestHelper.println(integer1 == integer2);

        Integer integer3 = -129;
        Integer integer4 = -129;
        // false
        TestHelper.println(integer3 == integer4);

        Integer integer5 = 127;
        Integer integer6 = 127;
        // true
        TestHelper.println(integer5 == integer6);

        Integer integer7 = 128;
        Integer integer8 = 128;
        // false
        TestHelper.println(integer7 == integer8);

        Integer integer9 = 11;
        Integer integer10 = 11;
        // true
        TestHelper.println(integer9 == integer10);

        // 创建新的对象, 不从缓存中获取
        Integer integer11 = new Integer(11);
        Integer integer12 = 11;
        // false
        TestHelper.println(integer11 == integer12);
    }

    /**
     * author: 2025513
     *
     * 2.位运算符测试
     * &、|、~、^的使用
     *
     * <ul>
     *     <li>1.&: 运算的位都为1，则结果为1，否则为0</li>
     *     <li>2.|:运算的位中只要有1则结果为1,否则为0</li>
     *     <li>3.~: 单目运算符，位0换成1,1换成0</li>
     *     <li>4.^: 运算的位值不相同为1,相同为0</li>
     * </ul>
     *
     * 结果:
     *
     * 结论:
     */
    @Test
    public void test2() {
        // 1.&: 运算的位都为1，则结果为1，否则为0
        // 13&17
        // 1101&10001
        // 预计结果应该是:00001,即为1
        TestHelper.println("13&17", 13 & 17); // 1

        // 2.|:运算的位中只要有1则结果为1,否则为0
        //结果应为11101,即16+8+4+1=29
        TestHelper.println("13&17", 13 | 17);// 29

        // 3.~: 0换成1，1换成0
        // int值经过~运算后的结果规律：
        // 当int值value>=0，则结果为-(value+1)
        // 当int值value<0时，则结果为-value-1
        TestHelper.println("~17", ~17);// -18

        //4.^
        // 1101 ^ 10001 -> 11100(28)
        TestHelper.println("13^17", 13 ^ 17); // 28
    }
}
