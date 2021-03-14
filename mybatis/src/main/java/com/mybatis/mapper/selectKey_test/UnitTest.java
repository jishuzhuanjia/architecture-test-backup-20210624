package com.mybatis.mapper.selectKey_test;

import com.mybatis.MybatisApplication;
import com.mybatis.mapper.selectKey_test.po.UserPO;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/8 9:36
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = MybatisApplication.class)
@RunWith(SpringRunner.class)
public class UnitTest {
    @Autowired
    UserMapper userMapper;

    /**
     * 1.测试
     * com.tgd.mybatis.mapper.UserMapper#insertUser(com.tgd.mybatis.po.UserPO)
     *
     *
     * 2.mybatis 增、删、改影响行数如何返回？
     * 只需要在配置数据源spring.datasource.url时，添加useAffectedRows=true   // 似乎不写也可以
     * 并且Mapper方法返回类型设置为数值类型,int/Integer,long/Long都可以...
     *
     * 不必在*.xml中指定resultType或resultMap,因为<insert><delete><update>标签根本就没有resultType和resultMap属性。
     *
     *
     * 注意：增删改方法不支持PO类返回类型：
     * org.apache.ibatis.binding.BindingException: Mapper method 'com.tgd.mybatis.mapper.UserMapper.insertUser2' has an unsupported return type: class com.tgd.mybatis.po.UserPO
     */
    @Test
    public void test1(){
        UserPO userPO = new UserPO();
        userPO.setUsername("xiaocaiji002");
        userPO.setPassword("12345");
        userPO.setAge(20);
        long affectRows = userMapper.insertUser(userPO);
        TestHelper.println("受影响的行",affectRows);
    }

    /**
     * 3.mybatis selectKey的使用
     *
     * 【注意点】
     * 1.插入数据的主键不是通过Mapper方法的返回值返回，而是将主键传递给了Mapper方法的参数 // 这是一个坑，请注意
     *
     * 2.MySQL主键自增时，selectKey标签的order应该设置为AFTER,否则返回的主键是0。  // 对于非自增主键情形，暂时没遇到，先不讨论
     * 其次,<selectKey>标签放在insert语句之前和之后都可以。
     *
     * 3.如果参数有别名，select的keyProperty不要忘记加别名，否则会因为找不到指定的属性而赋值失败。
     * 如：
     * 当Mapper方法为: public int insertUser2(@Param("user") UserPO user);
     * 则xml文件中:
     * #{uId} -> 正确的是： #{user.uId}
     */
    @Test
    public void test2(){
        UserPO userPO = new UserPO();
        userPO.setUsername("xiaocaiji002");
        userPO.setPassword("12345");
        userPO.setAge(20);
        int affectRows = userMapper.insertUser2(userPO);
        TestHelper.println("selectKey返回的主键: ",userPO.getUId());
    }
}
