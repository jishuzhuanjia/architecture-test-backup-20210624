package com.zj.test.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

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
/*
@Table: 手动映射表名

如果不指定,会按照类名进行默认动态映射:
Teacher -> teacher
TeacherTable -> teacher_table

注: name值可以是大小写混合,框架会自动处理,但是不推荐这个干。
 */
@Table(name="teacher_table")
public class Teacher {

    /*
    @Id: 指定主键字段,需要注意的是,JPA中实体类必须要添加此注解,否则会报错:
    java.lang.IllegalStateException: Failed to load ApplicationContext
    ...

     */
    @Id
    @GeneratedValue // 自动生成,不会跟随表AUTO_INCREMENT,会从最小数开始生成
    /*
    @Column(name="column"): 手动字段映射,当字段名不能和表名自动完成映射时,需要手动映射

    默认的动态映射:
    tid -> tid
    tId -> t_id

    注: name值可以是大小写混合,框架会自动处理,但是不推荐这个干。
     */
    @Column(name="t_id")
    Integer id;

    String name;

    Integer age;

    String sex;

    // 与mybatis-plus不同的是,jpa不要求实体类中的属性必须在表中存在
    String xsdd;
}
