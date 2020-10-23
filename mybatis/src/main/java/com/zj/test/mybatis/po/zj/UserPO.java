package com.zj.test.mybatis.po.zj;

/* @author: zhoujian
 * @create-time: 2020/9/17 13:44
 * @description:
 * @version: 1.0
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/*相关注解的作用:
*
* @Table: 如果表名不等于类名, 用来指定表名。因此如果表名不等于类名，不能省略该注解。
*
*
* */
//@Data
@Table(name = "user")/*指定表名*/
@Setter/*添加setter方法*/
@Getter/*添加geeter方法*/
@Entity/*被JPA管理的实体，将映射到指定的数据库，省略，目前没有发现问题，目前不知道其具体作用*/
public class UserPO implements Serializable {
    @Id
    @Column
    private Integer id;
    //001.如果指定的列名在数据库中不存在，会报错：
    //java.sql.SQLSyntaxErrorException: Unknown column 'username1' in 'field list'
    @Column(name = "username")
    private String name;

    // 002.注意：如果不指定列名，列数据库中必须存在和属性名一样的列，否则报错：
    // java.sql.SQLSyntaxErrorException: Unknown column 'password1' in 'field list'
    @Column
    private String password;

    //004.无论是否定义@Column注解,该属性对应的列必须在数据库中，否则都会报错
    // 因为这些属性都会被添加到select语句中。
    // 因此对于数据库表的实体类，不要添加无关的属性。
    //private Integer age;

    // 005.PO类中可以没有表中的字段。但是这些表的字段在增删改查时会被忽略。

    @Column
    private Integer age;

    private Banji banji;


    @Column(name="last_login_time")
    Date lastLoginTime;


    @Override
    public String toString() {
        return "UserPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

    @Setter
    @Getter
    public static class Banji{
        int id;
        String banji;
        int userId;

        @Override
        public String toString() {
            return "Banji{" +
                    "id=" + id +
                    ", banji='" + banji + '\'' +
                    ", userId=" + userId +
                    '}';
        }
    }
}
