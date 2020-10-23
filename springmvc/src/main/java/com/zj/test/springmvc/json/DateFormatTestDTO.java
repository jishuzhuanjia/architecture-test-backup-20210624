package com.zj.test.springmvc.json;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/* @author: zhoujian
 * @create-time: 2020/9/24 16:52
 * @description: json Date反序列化注解的使用
 * @version: 1.0
 */
public class DateFormatTestDTO {
    /*001.@DateTimeFormat注解
    * 【作用】
    * 将一定格式的日期时间字符串转换成Date对象,一般用在前端传递日期时间参数时，构造Date对象。
    *
    * 【场景】
    * 一般用在json反序列化中。
    *
    * 【属性】
    * pattern: 日期时间字符串匹配模式。
    * iso: 指定该属性，使用预定义的日期时间字符串匹配模式。
    *
    * 【优先级】
    * pattern > iso: 当同时指定这两个属性时，最终起作用的匹配模式是pattern。
    *
    * 【注解可放置的位置】
    * 属性、setter、getter上皆可。该注解不允许放置在类上。
    *
    * 使用注意：
    * 需要和接口调用者，协商一致的匹配模式。因为如果不一致，会导致400错误(Bad Request),
    * 接口调用失败
    * */

    Date loginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso =DateTimeFormat.ISO.DATE)
    public Date getLoginTime() {
        return loginTime;
    }


    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "DateFormatTestDTO{" +
                "loginTime=" + loginTime +
                '}';
    }
}
