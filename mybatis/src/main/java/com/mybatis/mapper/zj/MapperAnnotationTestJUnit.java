package com.mybatis.mapper.zj;

import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mybatis.MybatisApplication;
import com.mybatis.UserPO2;

import java.util.List;
import java.util.Map;

/* @author: zhoujian
 * @create-time: 2020/9/24 13:16
 * @description:
 * @version: 1.0
 */
@SpringBootTest(classes = MybatisApplication.class)
@RunWith(SpringRunner.class)
public class MapperAnnotationTestJUnit {
    @Autowired MapperAnnotationMapper testMapper;

    /*select测试: @Select的使用*/
    @Test
    public void test1(){
        TestHelper.startTest("mapper注解测试: @select");
        List<Map<Object,Object>> selectList = testMapper.select();
        TestHelper.println("查询到结果数: " + selectList.size());
        TestHelper.println("查询结果: " + selectList);
        TestHelper.finishTest();
    }

    /*select测试2: @Select和@Results的使用*/
    @Test
    public void test2(){
        TestHelper.startTest("mapper注解测试: @Results");
        List<UserPO2> selectList = testMapper.select2();
        TestHelper.println("查询到结果数: " + selectList.size());
        TestHelper.println("查询结果: " + selectList);
        TestHelper.finishTest();
    }
}
