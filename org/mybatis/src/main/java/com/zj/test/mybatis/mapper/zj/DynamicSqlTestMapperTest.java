package com.zj.test.mybatis.mapper.zj;

import com.zj.test.mybatis.TestApplication;
import com.zj.test.mybatis.controller.zj.dynamicsql.po.BookPO;
import com.zj.test.util.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
class DynamicSqlTestMapperTest {

    @Autowired
    DynamicSqlTestMapper mapper;

    /**
     * author: 2025513
     *
     * 1.测试
     * choose + when  + otherwise测试
     *
     * 【作用】
     * 相当于java中的switch + case + default
     *
     * 【测试结果】
     *
     * 【结论】
     * 1.常用where标签包围choose，以避免关键字多余语法报错。
     *
     * 2.会按照书写的顺序来判断when标签，当找到test为true的when时停止，或者没有匹配的则使用otherwise中的语句。
     *
     * 3.otherwise标签至多只有一个，且必须放在when标签后面。
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    void selectByWhereAndChoose() {
        /*
        1.两个参数都传递时，通过id查询
        查询到的数据:
         [BookPO{bId=3, bookName='刑法学讲义', authorName='罗翔', create_time=Mon Oct 12 23:19:21 CST 2020}]
        */
        List<BookPO> bookList = mapper.selectByWhereAndChoose("我的二本学生", 3);
        TestHelper.println("查询到的数据:",bookList);

        /*
        2.没有传递id，则根据书名查询:
        查询到的数据:
        [
        BookPO{bId=1, bookName='我的二本学生', authorName='黄灯', create_time=Mon Oct 12 23:18:51 CST 2020},
        BookPO{bId=9, bookName='我的二本学生', authorName='zhoujian', create_time=Thu Nov 19 01:09:35 CST 2020}
        ]
        */
        List<BookPO> bookList2 = mapper.selectByWhereAndChoose("我的二本学生", null);
        TestHelper.println("查询到的数据:",bookList2);

        /*
        3.bookName和bId都没有传递，则默认返回书名为价值的书
        查询到的数据:
        [BookPO{bId=4, bookName='价值', authorName='张磊', create_time=Mon Oct 12 23:19:34 CST 2020}]
        */
        List<BookPO> bookList3 = mapper.selectByWhereAndChoose(null, null);
        TestHelper.println("查询到的数据:",bookList3);
    }
}