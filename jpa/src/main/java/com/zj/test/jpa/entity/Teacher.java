package com.zj.test.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/13 16:52
 * @description: JPA测试实体类
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Data
@Proxy(lazy = false) // 默认懒加载
/*
测试时添加该注解会出现如下错误: Persistent entity 'Teacher' should have primary key
即提示设置主键
*/
@Entity
public class Teacher {

    @Id
    @GeneratedValue // 自动生成,不会跟随表AUTO_INCREMENT,会从最小数开始生成
    Integer id;

    String name;

    Integer age;

    String sex;

    // 与mybatis-plus不同的是,jpa不要求实体类中的属性必须在表中存在
    String xsdd;
}
