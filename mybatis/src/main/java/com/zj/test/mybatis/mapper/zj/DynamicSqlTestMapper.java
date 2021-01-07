package com.zj.test.mybatis.mapper.zj;

import com.zj.test.mybatis.controller.zj.dynamicsql.po.BookPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/12 15:17
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public interface DynamicSqlTestMapper {

    /*1.if标签测试*/
    public List<BookPO> selectWithIf(BookPO book);

    /*2.foreach标签 + in测试*/
    public List<BookPO> selectWithForeach(List<String> booknameList);

    /**
     * 3.choose + when + otherwise测试
     */
    public List<BookPO> selectByWhereAndChoose(@Param("bookName") String bookName, @Param("bId")Integer bId);
}
