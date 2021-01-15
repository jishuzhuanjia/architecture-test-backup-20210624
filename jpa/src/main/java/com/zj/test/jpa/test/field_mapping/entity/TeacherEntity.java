package com.zj.test.jpa.test.field_mapping.entity;

import com.zj.test.jpa.test.field_mapping.enums.SexEnum;
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
@Entity() // name属性指定Bean的名称，默认为非限定类名
@Proxy(lazy = false)
/*
@Table属性说明:
name: 表名
catalog: 数据库名,如果不指定会使用spring.datasource.url指定的数据库       // true
schema:指定数据库的用户名        // 作用UNKNOWN
uniqueConstraints:指定唯一性字段约束,如为personid 和name 字段指定唯一性约束:
uniqueConstraints={ @UniqueConstraint(columnNames={"personid", "name"})}

注意:
uniqueConstraints是将多个字段形成唯一索引，而不是每个字段形成唯一索引,如下面的定义会创建唯一索引:
UNIQUE KEY `UKc16wo9imyjgbkrlwt1avn7qhr` (`name`,`address`)
 */
@Table(name = "teacher_table",catalog = "test",schema = "sch")/*,
uniqueConstraints = {@UniqueConstraint(columnNames = {"name","address"})*/

/*
@SecondaryTables: 多库多表映射,@SecondaryTable是引用表,会创建外键。
也可以指定数据库，通过@SecondaryTable catalog属性指定
 */
/*@SecondaryTables({
        @SecondaryTable(catalog = "architecture_test",name="teacher_table", pkJoinColumns={@PrimaryKeyJoinColumn(name="t_id")})})*/
// @PrimaryKeyJoinColumn: 引用表的主键字段,如果引用表不存在该字段，会自动创建
// 注意: 如果@SecondaryTable是其他库中同名表，则最终只会创建@SecondaryTable指定的表，而不会向其插入数据。
// 因此实体映射多表时，只能映射到与@Table本身的表名不同的表,并且不同库中的相同表不允许重复使用@SecondayTable重复定义。

// @SecondaryTable(catalog = "architecture_test",name = "teacher_table1",pkJoinColumns={@PrimaryKeyJoinColumn(name = "t_id")})

// 即使teacher_table1与teacher_table不同,但是已经在architecture_test表中定义过了,所以会报错:
// Caused by: org.hibernate.boot.spi.InFlightMetadataCollector$DuplicateSecondaryTableException:
//  Table with that name [teacher_table1] already associated with entity
//@SecondaryTable(catalog = "temp",name = "teacher_table1",pkJoinColumns={@PrimaryKeyJoinColumn(name = "t_id")})
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "t_id")
    private Integer tId;

    Integer age;

    String name;

    // 注意: 实体类中默认的字段都会映射到@Table指定的表，而不会映射到SecdonaryTable指定的表
    // 需要手动设置字段所属表:
    // 注意，如果table指定的表没有通过@SecondaryTable定义会报错:
    // Caused by: org.hibernate.AnnotationException: Cannot find the expected secondary table: no teacher_table1 available for com.zj.test.jpa.test.field_mapping.entity.TeacherEntit
    // @Column(table = "teacher_table1")

    // 网上说只通过@JoinColumn(table = "teacher_book")也可以，但是我失败了
    // 甚至是我同时使用@Column和@JoinColumn都失败了
    // 根据目前的测试我得出的结论是,实体类中的属性只能属于一个表名,注意是表名，而不是表。
    // @JoinColumn(table = "teacher_book")
    String book;


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

    @Enumerated(value = EnumType.ORDINAL)
    SexEnum sex;

    /*
    嵌入对象映射测试

    测试结果:
    1.jpa实体类属性默认不支持除String外的引用类型，嵌套类型定义中如果不添加@Embededdable注解,则会报错: Caused by: javax.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.MappingException: Could not determine type for: com.zj.test.jpa.test.field_mapping.entity.Name, at table: teacher_table, for columns: [org.hibernate.mapping.Column(names)]
    2.满足1的情况下,默认情况下，jpa会将嵌套对象Name的所有属性都映射到表,如果不存在对应的表字段，则会自动添加。并将嵌套属性序列化到数据库中
     */
    /*
    @AttributeOverrides: 覆盖默认的属性映射,对于没有覆盖的属性，按照jpa默认方式进行映射。
     */
    @AttributeOverrides({
            @AttributeOverride(name="firstName",column = @Column(name="f_name"))
    })
    /*@Embedded: 测试时,加不加没有区别,待以后解决*/
    Name names;

}
