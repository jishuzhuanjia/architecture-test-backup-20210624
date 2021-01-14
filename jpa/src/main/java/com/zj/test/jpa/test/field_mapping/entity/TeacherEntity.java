package com.zj.test.jpa.test.field_mapping.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/14 17:38
 * @description: 数据库teacher表实体类
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Data
@Entity
@Proxy(lazy = false)
@Table(name = "teacher_table")
public class TeacherEntity {

    @Id
    @GeneratedValue
    @Column(name = "t_id")
    private Integer tId;

    Integer age;

    String name;

    // 表中没有的字段
    String address;

    // 表中没有的字段
    String favorite;

    /*
    列定义测试
     */
    @Column(columnDefinition = "varchar(20) DEFAULT \"NO\"") // 经测试,自动创建了字段: `label` varchar(20) DEFAULT 'NO',
            // @Column(columnDefinition = "label varchar(20) DEFAULT \"NO\"") // 经测试, columnDefinition定义不能包含字段名,否则报错
            String label;

    /*
    列长度测试
    length默认255

    测试结果:
    1.如果没有指定columnDefinition,仅仅@Column(length = 40 )，则自动生成字段:
    `label1` varchar(40) DEFAULT NULL,

    2.如果同时指定了columnDefinition和length,即@Column(length = 40,columnDefinition = "varchar(33) default \"none\"")
    则最终生成字段:
    `label1` varchar(33) DEFAULT 'none',

    即对于表字段长度的定义,columnDefinition优先级大于length。
    */
    @Column(length = 40, columnDefinition = "varchar(33) default \"none\"")
    String label1;

    // precision: 默认0
    // scale: 默认0
    // 以下定义,自动创建了表字段: `f` float DEFAULT NULL,似乎precision和scale没有在创建字段的是否发挥作用。
    // 注意: 为float类型设置precision和scale无效
    @Column(precision = 5, scale = 2)
    float f;

    // 以下定义,自动创建了表字段: `d` double DEFAULT NULL,,似乎precision和scale没有在创建字段的是否发挥作用。
    // 注意: 为double类型设置precision和scale无效,可以通过columnDefinition定义。
    @Column(precision = 5, scale = 2)
    double d;

    // double虽然不能通过precision和scale来指定精度,但是可以通过columnDefinition来定义。
    // 下面的定义会创建列字段: `d2` decimal(5,2) DEFAULT NULL,
    @Column(columnDefinition = "decimal(5,2) DEFAULT NULL")
    double d2;

    // precision和scale其实只对BigDecimal类型的属性有效,如下面的列定义最终会生成字段:
    // `d3` decimal(5,2) DEFAULT NULL
    // 可以看到，成功了!
    @Column(precision = 5, scale = 2)
    BigDecimal d3;

    // 测试可空
    // nullable: 默认true: 可空，会设置DEFAULT NULL
    // 以下的注解会自动生成列定义: `n` varchar(255) NOT NULL,
    // @Column(nullable = false)
    @Column(nullable = true)
    String n;

    // 测试唯一限制
    // unique: 默认false。
    // 以下的注解会自动生成列定义:
    // `u` varchar(255) DEFAULT NULL,
    // UNIQUE KEY `UK_900nepf9plnewh8e2ik4bcgmy` (`u`)
    @Column(unique = true)
    String u;

    // 日期定义
    // 默认情况下，会将java.util.Date映射成datetime类型
    // 默认情况下，会将java.sql.Date映射成date类型
    // 如果想要映射成Date，可以通过columnDefinition
    // @Column(name = "start_date",columnDefinition = "DATE DEFAULT CURRENT_DATE")会报错,日后来解决 !!!!!!!
    @Column(name = "start_date",columnDefinition = "DATE DEFAULT NULL")
    private Date date;

    // 默认情况下，Calendar会被映射成datetime类型，如:
    // `c` datetime(6) DEFAULT NULL
    Calendar c;

    // 如果将Calendar映射到Date? 解决:          // 成功
    @Temporal(TemporalType.DATE)
    Calendar c2;

    // Timestamp类型默认会创建datetime类型的列
    private Timestamp ts;

    // 将Date映射成TIME类型:      // 成功
    @Temporal(TemporalType.TIME)
    java.util.Date date2;

    // 映射到TIMESTAMP
    @Temporal(TemporalType.TIMESTAMP)
    java.util.Date date3;

    // 报错: Caused by: org.hibernate.AnnotationException: @Temporal should only be set on a java.util.Date or java.util.Calendar property
    // 原因: @Temporal 只能用于java.util.Date或java.util.Calendar
    /*
    @Temporal(TemporalType.TIMESTAMP)
    Date date4;
    */

    // 默认情况如果表中没有指定的字段，会自动创建，那么如何关闭?
    // 测试结果: 成功: 没有自动添加表字段。
    @Transient
    String s;
}
