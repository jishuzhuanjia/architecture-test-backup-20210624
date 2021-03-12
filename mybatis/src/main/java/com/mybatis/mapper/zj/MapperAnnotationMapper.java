package com.mybatis.mapper.zj;

/*mapper接口使用注解的方式实现sql开发
 * 2020年9月24日 13:13:32
 * @aothor: zhoujian
 * */


import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import com.mybatis.UserPO;
import com.mybatis.UserPO2;

import java.util.List;
import java.util.Map;

// 测试表: user
// 使用注解的好处，可以不用添加mapper.xml文件。
public interface MapperAnnotationMapper {

    /*/
    /*001.使用注解的方式书写sql，就不用指定resultType了，默认会和方法返回类型保持一致。
    mybatis中crud操作相关注解:
    @Select
    @Insert()
    @Delete()
    @Update()*/
    @Select("select * from user")
    public List<Map<Object, Object>> select();

    @Select("select * from user")
    /*
    002:
    @Results
    作用：可用来对查询返回的列名进行更改。更改后和PO属性名对应才会被映射到po对象。
    注意：@Results中没有涉及的column name,只要列名和po属性名符合，值还是会被绑定到对象。

    小技巧：如果将Map作为查询的returnType，可以接受任何的columnName，并绑定值到Map:
    columnName: value
    */
    @Results({
            // 注意：PO必须要有property的属性(Map可以没有)，否则会报错。
            @Result(column = "username", property = "name", jdbcType = JdbcType.VARCHAR
                    , javaType = String.class),
            @Result(column = "password", property = "password"),
            // id：是否为主键，默认false
            @Result(id = true, column = "id", property = "id"),
            /*003:
            proeprty: po对象属性名。
            column: 作为@One selecet属性的参数列
            one: 指定一个查询方法。
            注意：property必须在Po类中存在，否则报错(Map除外)
            注意：如果指定的property不存在，会报错。

            注意：与xml中一对一查询不同，会自动映射列值到property属性中。
            */
            @Result(property = "ppp", column = "id", one = @One(select = "test.mapper.zj.MapperAnnotationMapper.selectBanjiById",
                    fetchType = FetchType.EAGER))

    })
    public List<UserPO2> select2();

    /*004:该方法返回类型需要和@One属性的类型一致，否则报错。*/
    @Select("select * from banji where userId=#{id}")
    public UserPO.Banji selectBanjiById();
}
