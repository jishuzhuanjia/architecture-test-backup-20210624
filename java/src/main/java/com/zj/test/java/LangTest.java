package com.zj.test.java;

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
     * Long对象比较测试
     *
     * cache数组缓存的Long对象数值范围和Integer一样，也是[-128,127)
     * ==比较结论和Integer相同
     */
    @Test
    public void testLong() {
        Long long1 = 1L;
        Long long2 = 1L;
        TestHelper.println("long1==long2", long1 == long2);

        // 上临界值测试
        // 127
        Long long3 = 127L;
        Long long4 = 127L;
        //true
        TestHelper.println("long3==long4", long3 == long4);

        //128
        Long long5 = 128L;
        Long long6 = 128L;
        //false
        TestHelper.println("long5==long6", long5 == long6);

        //下临界测试
        // -128
        Long long7 = -128L;
        Long long8 = -128L;
        //true
        TestHelper.println("long7==long8", long7 == long8);

        Long long9 = -129L;
        Long long10 = -129L;
        // false
        TestHelper.println("long9==long10", long9 == long10);

    }

    /**
     * ==比较运算符
     * 在Java中,如果是基本数据类型,则 == 比较的是值;如果是对象类型,则 == 比较的是对象的地址。
     *
     *不同类型==测试: 基础类型之间可以直接比较，不需要类型转换
     *
     * 除了boolean，基础类型之间可以相互比较==，不需要类型转换，
     */
    @Test
    public void test() {
        // 1.==用来比较对象
        User user1 = new User();
        user1.username = "zhou jian";

        User user2 = user1;
        // true
        TestHelper.println("user1==user2", user1 == user2);

        user2 = new User();
        user2.username = "zhou jian";
        // false
        TestHelper.println("user1==user2", user1 == user2);

        // 2.==用来比较基础类型
        int i1 = 3;
        int i2 = 3;
        // true
        TestHelper.println("i1==i2", i1 == i2);

        short s1 = 3;
        // true
        TestHelper.println("i1==s1", i1 == s1);

        TestHelper.startTest("不同类型==");

        // 3.不同类型==测试: 基础类型之间可以直接比较，不需要类型转换

        // 字面量超过int范围，需要强转long
        long l = 11112213233333L;
        short s = 13;
        int i = 1333333;
        double d = 13;
        char c = 'a';
        boolean b = false;
        TestHelper.println(l == s);
        TestHelper.println(l == i);
        TestHelper.println(s == d);
        TestHelper.println(c == d);
        TestHelper.println(c == l);
        // Operator '==' cannot be applied to 'boolean', 'long'
        //TestHelper.println(b==l);
        // Operator '==' cannot be applied to 'boolean', 'short'
        //TestHelper.println(b==s);

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
        // 13&17            -- 如何快速改写成2进制？ 首先找到比13小的最接近13的以2为底的指数，在这里是2^3，写成1000，再将余数补齐
        // 1101&10001
        // 预计结果应该是:00001,即为1
        // 实际：1
        TestHelper.println("13&17", 13 & 17);

        //2.|:运算的位中只要有1则结果为1,否则为0
        //结果应为11101,即16+8+4+1=29
        //正确：29
        TestHelper.println("13&17", 13 | 17);

        // 3.~: 0换成1，1换成0
        // int值经过~运算后的结果规律：-value-1
        // 正确：-18
        TestHelper.println("~17", ~17);

        //4.^：异或，运算位不相同返回1，否则返回0
        // 1101 ^ 10001 -> 11100(28)
        // 预计结果：11100 -> 16 + 8 +4=28
        // 正确: 28
        TestHelper.println("13^17", 13 ^ 17);
    }

    // 可克隆的类
    public static class User implements Cloneable {
        public String username;
        public String password;
        public int age;

        @Override
        public Object clone() throws CloneNotSupportedException {
            if (!(this instanceof Cloneable)) {
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
    public void array_weidu() {
        int[][][][][][][] arr = new int[1][1][1][1][1][1][1];
    }

    /**
     * <p>
     *     7.测试:  前++，后++
     * </p>
     *
     * 【出入参记录】
     *
     * 【结论】
     *
     * 【注意点】
     *
     */
    @Test
    public void plusPlusTest() {
        int i = 1;
        //后++，先使用变量的值，后再执行+1操作
        TestHelper.println("i", i++);    //1
        TestHelper.println("i", i);      //2

        int j = 1;
        // 前++,先对变量进行+1操作，再使用变量值
        TestHelper.println("j", ++j);    //2
        TestHelper.println("j", j);      //2

        int k = 1;
        TestHelper.println("(k++)+(++k)", (k++) + (++k));  //4

        int l = 1;
        l = l++;                          // 右表达式先对l赋值，后被左表达式赋值覆盖。
        TestHelper.println("l", l);      //1

        int m = 1;
        m = ++m;
        TestHelper.println("m", m);      //2
    }
}
