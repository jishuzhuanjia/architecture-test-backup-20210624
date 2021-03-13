package com.mybatis.dynamicsql;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/12 15:15
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import com.zj.test.util.TestHelper;
import com.zj.test.util.TestResultTips;
import com.mybatis.dynamicsql.po.BookPO;
import com.mybatis.mapper.zj.DynamicSqlTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test/mybatis/dynamicsql")
public class DynamiSqlTestController {

    @Autowired
    DynamicSqlTestMapper testMapper;

    /*1.if标签测试*/
    @RequestMapping("test1")
    public String test1(){
        TestHelper.startTest("mybatis动态语句if测试");
        BookPO selectPO = new BookPO();
        //selectPO.setAuthorName("张磊");
        selectPO.setBookName("价值");
        TestHelper.println("查询到的数据: " + testMapper.selectWithIf(selectPO));
        TestHelper.finishTest();
        return TestResultTips.SEE_AT_CONSOLE;
    }

    /*2.foreach标签 + in测试*/
    @RequestMapping("test2")
    public String test2(){
        TestHelper.startTest("mybatis动态语句foreach测试");

        List<String> nameList = new ArrayList<String>();
        nameList.add("我的二本学生");
        nameList.add("价值");
        TestHelper.println("查询到的数据: " + testMapper.selectWithForeach(nameList));
        TestHelper.finishTest();
        return TestResultTips.SEE_AT_CONSOLE;
    }
}
