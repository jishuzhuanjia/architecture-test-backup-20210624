package com.mybatis.mapper.annotation;

/*mapper接口使用注解的方式实现sql开发
 * 2020年9月24日 13:13:32
 * @aothor: zhoujian
 * */


import com.mybatis.UserPO;
import com.mybatis.UserPO2;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

// 测试表: user
// 使用注解的好处，可以不用添加mapper.xml文件。
public interface MapperAnnotationMapper {

    /*
    001.使用注解的方式书写sql，就不用指定resultType了，默认会和方法返回类型保持一致。
    mybatis中crud操作相关注解:
    @Select
    @Insert()
    @Delete()
    @Update()

    测试结论：
    1.如果resultType为Map，则表字段为null时，不会映射到Map，如：
    {uId=2, password=sdsd, username=sxas},          -- 注：uId为2的数据regist_ts为null
    {uId=3, regist_ts=2021-04-20 23:24:03.0, password=ddasdasd, username=sdas}

    2.1.如果resultType为Map,默认会将表字段名作为key。
    */
    @Select("select * from user")
    public List<Map<Object, Object>> selectAllColumns();

    @Select("select * from user")
    /*
    002:
    @Results测试
    作用：可用来对查询返回的列名进行更改。更改后和PO属性名对应才会被映射到po对象。
    注意：@Results中没有涉及的column name,只要列名和po属性名符合，值还是会被绑定到对象。

    小技巧：如果将Map作为查询的returnType，可以接受任何的columnName，并绑定值到Map:
    columnName: value
    */
    @Results(id="resultMap1",value={
            // 注意：PO必须要有property指定的属性(Map可以没有)，否则会报错。
            // column可以没有，不会报错
            @Result(column = "username", property = "name", jdbcType = JdbcType.VARCHAR
                    , javaType = String.class),
            // 把这条映射注解掉，仍然会自动完成映射
            /*@Result(column = "password", property = "password"),*/
            // id：是否为主键，默认false
            @Result(id = true, column = "uId", property = "id"),
            /*003:
            proeprty: po对象属性名。
            column:
            作为@One select属性的参数列,
            开发中应保证此列存在，
            因为如果该列不存在，@One查询不会报错，
            会导致最终property指定的属性为null,
            而返回奇怪的结果，开发中需要注意。

            one: 指定一个查询方法。
            注意：property必须在Po类中存在，否则报错(Map除外)
            注意：与xml中一对一查询不同，会自动映射列值到property属性中。
            */
            @Result(property = "ppp", column = "uId", one = @One(select = "com.mybatis.mapper.annotation.MapperAnnotationMapper.selectBanjiById",
                    fetchType = FetchType.EAGER))

    })
    public List<UserPO2> selectAllColumnsWithMap();

    /*
    Results复用测试

    注意：复用已有的@Results需要使用@ResultMap注解而不是@Results:
    @ResultMap(value="resultMap1")
    value: 要复用的@Results id
     */
    @Select("select * from user")
    @ResultMap(value="resultMap1")
    public List<UserPO2> selectAllColumnsWithMap2();

    /*004:该方法返回类型需要和@One属性的类型一致，否则报错。
     * 如果该方法指定的select查询不会被单独调用，而只是@One来调用的话，方法可以没有形参。
     * */
    @Select("select * from banji where userId=#{id}")
    public UserPO.Banji selectBanjiById();

    /*
    5.mybatis默认参数名
    可以从报错信息看出：
    org.mybatis.spring.MyBatisSystemException:
        nested exception is org.apache.ibatis.binding.BindingException:
            Parameter 'username1' not found.
                Available parameters are [password, param1, username, param2]

    即默认mybatis参数名是:
    1.paramN(其中N>=1)
    2.形参名
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    public List<Map<String, Object>> selectAllColumnsByParams(String username, String password);
}
