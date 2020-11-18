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