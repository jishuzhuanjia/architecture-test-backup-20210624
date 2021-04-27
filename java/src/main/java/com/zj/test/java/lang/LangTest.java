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
     * 因为IntegerCache缓存了值在128~127之间的Integer对象，如果值在此期间，则直接返回
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

    // 可克隆的类
    public static class User implements Cloneable{
        String username;
        String password;
        int age;

        @Override
        public Object clone() throws CloneNotSupportedException {
            if(!(this instanceof Cloneable)){
                throw new CloneNotSupportedException("类必须实现Cloneable接口");
            }

            User newUser = new User();
            newUser.username = this.username;
            newUser.password = this.password;
            newUser.age = this.age;
            return newUser;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    /**
     * author: 2025513
     *
     * 3.克隆(clone)测试
     *
     * 结果:
     *
     * 结论:
     * 想要实现克隆，需要重写Object的clone方法，默认情况下clone方法是protected修饰的。
     * 如果有需要，可以改成public修饰。
     *
     * 注意：
     * 没有实现Cloneable接口不会抛出CloneNotSupportedException,
     * 需要我们重写clone()方法的时候抛出,否则不会抛出
     */
    @Test
    public void test3() {
        TestHelper.startTest("对象clone测试");
        User user = new User();
        user.username = "zhoujian";
        user.password = "123456";
        user.age = 25;
        TestHelper.println("被克隆的对象", user);

        try {
            Object clone = user.clone();
            TestHelper.println("克隆得到的对象", user);
            TestHelper.println("是否为同一引用", clone == user);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * author: 2025513
     *
     * 4.测试System.out.println()耗时
     *
     * 测试思路:
     *
     * 结果:
     * 79481573388100
     * 耗时: 1.3774ms
     * 79482577135900
     * 耗时: 0.0694ms
     * 79483577454300
     * 耗时: 0.1062ms
     * 79484577791000
     * 耗时: 0.0657ms
     * 79485578031000
     * 耗时: 0.0883ms
     * 79486578485800
     * 耗时: 0.0646ms
     * 79487578676800
     * 耗时: 0.0784ms
     * 79488578957200
     * 耗时: 0.0777ms
     * 79489579505000
     * 耗时: 0.0873ms
     * 79490580032900
     * 耗时: 0.0666ms
     * 79491580349900
     * 耗时: 0.0722ms
     * 79492580536900
     * 耗时: 0.0629ms
     * 79493581283300
     * 耗时: 0.0674ms
     * 79494581598800
     * 耗时: 0.0781ms
     * 79495581658100
     * 耗时: 0.0806ms
     * 79496581834600
     * 耗时: 0.0693ms
     * 79497582650100
     * 耗时: 0.0714ms
     *
     * 结论: 打印不是非常耗时，10^-2 ~ 10^0 ms级别
     */
    @Test
    public void test4() {
        while (true) {
            long start = System.nanoTime();
            TestHelper.println(start);
            long end = System.nanoTime();
            TestHelper.println("耗时: " + (end - start) / 1000000d + "ms");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * author: 2025513
     *
     * 5.测试: System.currentTimeMillis()和System.nanoTime()区别
     *
     * public static long nanoTime()
     * 此方法只能用于测量经过的时间，与任何其他系统或壁钟时间概念无关。
     * 返回的值表示某一固定但任意的起始时间(可能是将来，因此值可能是负的)之后的纳秒。
     * Java虚拟机实例中对该方法的所有调用都使用相同的原点;其他虚拟机实例可能使用不同的源。
     *
     * public static long currentTimeMillis()
     * 当前时间与UTC 1970年1月1日午夜之间的差值(以毫秒计)。
     *
     * 测试思路:
     *
     * 结果:
     *
     * 结论:
     * 1.nanoTime的计时起点是随机的，不同的虚拟机实例可能使用不同的起点。
     *
     * 2.currentTimeMillis在不同的机器、虚拟机实例上，都是相同的起点，单位是ms。
     */
    @Test
    public void test5() {
        while (true) {
            // 1605078568067        1605078569068
            long currentTimeMillis = System.currentTimeMillis();
            // 79862073337300       79863074030000
            long nanoTime = System.nanoTime();

            TestHelper.println("System.currentTimeMillis()", currentTimeMillis);
            TestHelper.println("System.nanoTime()", nanoTime);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * author: jishuzhuanjia
     * qq: 2025513
     *
     * 6.测试数组的最大维度
     *
     * 【作用】
     *
     * 【测试结果】
     * 暂停测试
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void array_weidu(){
        int[][][][][][][] arr = new int[1][1][1][1][1][1][1];
    }
}
