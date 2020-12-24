package com.zj.test.springmvc.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/* @author: zhoujian
 * @create-time: 2020年9月24日 20:30:50
 * @description:json Date序列化注解的使用
 * @version: 1.0
 */

public class JsonFormatTestPO {

    public Date getLoginTime() {
        return loginTime;
    }

    /*
    001.解决直接返回之间比北京时间慢8小时:
    timezone="GMT+8"

    【常用属性】
    pattern: Date对象序列化的格式。
    timezone: 时区。

    【注解位置】
    可添加在属性、setter或getter上，都有效，该注解可以放置在类上，但是无效。
    因此该注解只应该被放置在：属性或setter或getter上。
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    Date loginTime;

    @Override
    public String toString() {
        return "DateFormatTestPO{" +
                "loginTime=" + loginTime +
                '}';
    }
}
