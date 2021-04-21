package com.mybatis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class UserPO2 implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String password;
    /*001.@One时，该属性如果定义成Map<Object,Object> ppp,mybatis则框架自动返回Map<Object,Object>对象。
    *如果查询不到结果，返回null
    *
    * 如果定义成List<Map<Object,Object>>时，则mybatis返回的就是List,
    * 如果没有查询到结果，则List长度为0；
    *
    * */
    private UserPO.Banji ppp;

    private Date regist_ts;
}