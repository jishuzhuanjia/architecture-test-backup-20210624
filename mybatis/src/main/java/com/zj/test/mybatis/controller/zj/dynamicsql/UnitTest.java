package com.zj.test.mybatis.controller.zj.dynamicsql;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021年3月12日 11:04:09
 * @description: com.zj.test.mybatis.controller.zj.dynamicsql.DynamiSqlTestController改UT
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import com.zj.test.mybatis.TestApplication;
import com.zj.test.mybatis.controller.zj.dynamicsql.po.BookPO;
import com.zj.test.mybatis.mapper.zj.DynamicSqlTestMapper;
import com.zj.test.mybatis.util.zj.TestHelper;
import com.zj.test.util.TestResultTips;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class UnitTest {

    @Autowired
    DynamicSqlTestMapper testMapper;

    /*1.if标签测试*/
    @Test
    public void test1() {
        TestHelper.startTest("mybatis动态语句if测试");
        BookPO selectPO = new BookPO();
        //selectPO.setAuthorName("张磊");
        selectPO.setBookName("价值");
        TestHelper.println("查询到的数据: " + testMapper.selectWithIf(selectPO));
        TestHelper.finishTest();
    }

    /*2.foreach标签 + in测试*/
    @Test
    public void test2() {
        TestHelper.startTest("mybatis动态语句foreach测试");

        List<String> nameList = new ArrayList<String>();
        nameList.add("我的二本学生");
        nameList.add("价值");
        TestHelper.println("查询到的数据: " + testMapper.selectWithForeach(nameList));
        TestHelper.finishTest();
    }

    /**
     * 3.choose测试
     * 如果传递id则根据id查询
     * 如果传递name则根据name查询
     * 如果都没传递，默认查找名为"价值"的书
     */
    @Test
    public void test3() {
        TestHelper.startTest("mybatis动态语句choose测试");

        List<BookPO> 我的二本学生 = testMapper.selectByWhereAndChoose("我的二本学生", 2);
        TestHelper.finishTest();
    }

    /**
     * 4.测试: if choose嵌套测试
     *
     */
    @Test
    public void test4(){
        BookPO bookPO = new BookPO();
        bookPO.setBookName("我的");
        bookPO.setBookNameIsExact(0);
        List<BookPO> bookPOS = testMapper.selectByIfAndChoose(bookPO);
    }

}
