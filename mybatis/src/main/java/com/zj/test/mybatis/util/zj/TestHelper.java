package com.zj.test.mybatis.util.zj;

/* @author: zhoujian
 * @create-time: 2020/9/17 10:13
 * @description: 测试辅助工具类，控制台打印请使用此类提供的方法，统一输出红色文本
 * @version: 1.0
 */
public class TestHelper {
    private TestHelper(){}
    /*
     *@Param testTitle 标题：用来说明你此次测试的目的。
     * */
    public static void startTest(String testTitle){
        System.err.println("****************** " + testTitle + " ******************\n开始测试...");
    }

    /*打印行*/
    public static void println(Object contentObject){
        System.err.println(contentObject);
    }

    /*打印行
     * key: value
    */
    public static void println(Object key,Object value){
        System.err.println(key + ": "  +value);
    }

    /*打印测试结束提示*/
    public static void finishTest(){
        System.err.println("***** 测试完成 *****");
    }

    public static void printSubTitle(String subTitle){
        System.err.println("--" + subTitle);
    }
}
