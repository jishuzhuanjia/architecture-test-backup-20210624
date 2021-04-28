package com.zj.test.java.lang.string.regexp;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *正则表达式测试类
 * 讨论正则表达式语法 和 String对象支持正则的三个方法测试: matches(),split(),replaceAll()
 *
 * 规律: 只有matches()方法中的正则表达式'^','$'是可选的，其他3种方式都不能带
 *
 *
 **/

public class RegExpTest {
    /* 作用：正则表达式(RegExp)用来匹配字符串中满足的字串 */

    /**
     * 1.正则表达式语法
     *
     * 正则表达式以^开头,$结尾
     * */

    /**
     * 2.RegExp自定义字符集:                          // []用来表示字符集
     * [1234]  -  1,2,3,4之一
     * [^12]  -  除了1,2
     * [1-5]  -  1,2,3,4,5
     * [a-f]  -  a到f之间的
     */

    /**
     * 3.RegExp预定义字符集:                          // 这部分不需要用[]包围
     * .   表示任意字符
     *
     * \d  [0-9]
     * \D  非数字
     *
     * \w  单词字符[0-9a-zA-Z_]  字母数字下划线
     * \W  非单词字符
     *
     * \s  匹配空白: \t \n \r \b \p
     * \S  非空白
     *
     * 注意： '\' 在java中需要使用转义字符。
     */

    /**
     * 4.RegExp数词,表示匹配字符串长度
     *
     * {m,n}                数词,表示[m,n]个
     * {n}                  表示数次n,固定次数
     *
     * ?                    出现0/1次,自此处，以下数词都不需要{}包围
     * +                    >=1次
     * *                    >=0次
     *
     * 注意：[] 和 {}之间不能有空格
     * */

    /**
     * 5.常用正则表达式写法:
     *
     * 邮政编码
     * ^[0-9][0-9][0-9][0-9][0-9][0-9]$
     * 或
     * ^\d{6}$
     *
     * 用户名规则:
     * ^\w{8,10}$                       匹配8~10位用户名
     *
     * 电话号码:
     * +86 13912345678
     * ^(\+86|0086)?\s?\d{11}$
     *
     * 日期:
     * ^\d{4}-\d{2}-\d{2}$
     * */

    /**
     *测试1: 正则表达式使用：找出所有电话号码
     *表达式不能写^$,否则会被当做查找的内容
     * */
    @Test
    public void findTels() {
        String testStr = "13951998372xx1515644447921321xx15156911119";

        //导入java.util下的Pattern

        /*
        正则表达式的使用:
        1.首先获取Pattern(模式)对象: 通过静态方法compile将正则表达式编译成模式。
        参数是正则表达式内容部分,不能包含'^'和'$',否则会当做内容部分进行匹配。

        正则表达式中的'\'需要使用转义字符。
         */
        //Pattern pattern  = Pattern.compile("^\\d{11}$");
        Pattern pattern = Pattern.compile("^\\d{11}$");

        /*
        2. 获取Matcher对象：负责匹配
        通过Pattern的matcher对象,参数是被查找的字符串。
        */
        Matcher matcher = pattern.matcher(testStr);

        /*
        3.已经找过的匹配的子串不会被再次查找,如:
        对于1515644447921321
        查到到第一个号码:15156444479
        不会再查找51564444792...以此类推
         */

        // 总共匹配到的个数
        int count = 0;
        while (matcher.find()) {
            ++count;
            /*
            格式化输出查找结果

            方法:
            start()     返回当前匹配到的子串在原字符串中开始位置
            end()       返回当前匹配到的子串在原字符串中结束位置
            start、end基于0,需要注意的是end位置的字符没有被包含在匹配到的字符串中

            group()     返回当前匹配得到的字符串

            printf格式化:
            %s 字符串
            %d 数值
            %n 换行
            */
            System.out.printf("找到\"%s\",起始位置:%d,结束位置:%d%n",
                    matcher.group(), matcher.start(), matcher.end());
        }

        TestHelper.println("匹配完成,匹配条数", count);
    }

    /**
     *测试2: 支持正则表达式的String方法
     * matches()        判断字符串是否符合RegExp,需要注意的是它并不是用来查找子串的
     *
     * 验证用户输入
     *
     * 注意：此方法的正则表达式写不写^$都可以
     * */
    @Test
    public void matches() {
        // 验证字符串是否是11位手机号码
        String testStr1 = "13957896521";
        String testStr2 = "1395789652";

        // 1.matches方法接受的正则表达式, '^'和'$'是可选的
        TestHelper.println("\"" + testStr1 + "\"" + "是否符合正则表达式^\\d{11}$", testStr1.matches("^\\d{11}$"));
        // 13957896521是否符合正则表达式: true
        TestHelper.println("\"" + testStr1 + "\"" + "是否符合正则表达式\\d{11}", testStr1.matches("\\d{11}"));
        // 1395789652是否符合正则表达式: false
        TestHelper.println("\"" + testStr2 + "\"" + "是否符合正则表达式\\d{11}", testStr2.matches("\\d{11}"));
    }

    /** 测试3：
     * String支持正则的方法: split
     *
     * split()              按照指定的正则表达式，将字符串分割成数个子字符串，并返回它们的数组。
     *                      如果无法完成分割则返回原串
     *
     * 测试: 从有规则的字符串中分离出多个手机号码
     *
     * 注意:
     * 此方法的正则表达式不能写'^'和'$',否则会被当做查找的内容
     * 参数是用来分隔的字符串正则表达式
     * 正则表达式也可以是普通的字符串
     *
     *  replaceAll()
     *  此方法的正则表达式也不能写'^'和'$',否则会被当做查找的内容
     *  */
    @Test
    public void split() {
        String test = "16651622365ex15156911119ef13951998372";

        //1.[] 和 {}之间不能有空格
        //如果无法进行分组则返回原串
        //不能写 ^ $　　
        String regEx = "[a-zA-Z]{2}";

        //空白也可以作为结果
        //注意:参数的正则表达式指的是用来分隔的字符串。
        String[] tels = test.split(regEx);

        //System.out.println(Arrays.toString(tels));

        for (String tel : tels) {
            System.out.println(tel);
        }
    }

    /**
     * 测试4.String支持正则的方法: replaceAll
     *
     * public String replaceAll(String regex,String replacement)
     * 将字符串中匹配正则表达式的子串用指定的内容替换。
     *
     * 注意:
     * 正则表达式参数regex不能添加'^'和'$',否则会被当做内容能进行匹配。
     * 如果没有找到匹配的子串，则返回原字符串。
     */
    @Test
    public void replaceAll() {
        String test = "16651622365ex15156911119ef13951998372";

        TestHelper.println(test.replaceAll("[a-zA-Z]{2}", ","));

        // 不能添加'^'和'$'，否则会被当做内容进行匹配
        TestHelper.println(test.replaceAll("^[a-zA-Z]{2}$", ","));
    }

    /**
     * author: 2025513
     *
     * 测试5: 正则表达式字符集测试
     *
     * 结果:
     *
     * 结论:
     *  匹配空格:       " "
     *  匹配换行:       "\n"
     *  匹配制表符:      "\t"而不是" "(通过键盘Tab)
     */
    @Test
    public void regExChar() {

        // \d测试
        // 如果省略数词，则为1
        TestHelper.println("\"5\".matches(\"\\\\d\")", "5".matches("\\d"));
        TestHelper.println("\"10101022920\".matches(\"\\\\d\")", "10101022920".matches("\\d"));//false
        TestHelper.println("\"A\".matches(\"\\\\d\")", "A".matches("\\d"));//false

        // \w测试
        // 结果:匹配字母,数字,_
        TestHelper.println("\"A\".matches(\"\\\\w\")", "A".matches("\\w"));//true
        TestHelper.println("\"a\".matches(\"\\\\w\")", "a".matches("\\w"));//true
        TestHelper.println("\"1\".matches(\"\\\\w\")", "1".matches("\\w"));//true
        TestHelper.println("\"_\".matches(\"\\\\w\")", "_".matches("\\w"));//true
        TestHelper.println("\"!\".matches(\"\\\\w\")", "!".matches("\\w"));//false
        TestHelper.println("\"@\".matches(\"\\\\w\")", "@".matches("\\w"));//false
        TestHelper.println("\"$\".matches(\"\\\\w\")", "$".matches("\\w"));//false

        // 匹配任意字符
        TestHelper.println("\"^\".matches(\".\")", "^".matches("."));//true

        // 空白匹配 \s
        TestHelper.println("\" \".matches(\"\\\\s\")", " ".matches("\\s"));//true
        TestHelper.println("\"    \".matches(\"\\\\s\")", "    ".matches("\\s"));//false
        TestHelper.println("\"\\n\".matches(\"\\\\s\")", "\n".matches("\\s"));//true
        TestHelper.println("\"\\t\".matches(\"\\\\s\")", "\t".matches("\\s"));//true
    }
}
