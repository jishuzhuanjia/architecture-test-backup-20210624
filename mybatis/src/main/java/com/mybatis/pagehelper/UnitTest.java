package com.mybatis.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.MybatisApplication;
import com.mybatis.mapper.zj.PageHelperTestMapper;
import com.zj.test.util.TestHelper;
import com.zj.test.util.TimeHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;
import java.util.Map;

/* @author: zhoujian
 * @create-time: 2020年9月17日 12:12:36
 * @description: 1.将PageHelper分页查询的，封装到PageInfo测试
 *               2.PageInfo属性说明。
 * @version: 1.0
 */
@SpringBootTest(classes = MybatisApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UnitTest {
    @Autowired
    PageHelperTestMapper mapper;

    /**
     --------------------------------------PageInfo属性说明--------------------------------------
     @author: zhoujian
     1.PageInfo属性说明:
     {
     "total": 12,            // sql语句查询的结果条数，是总共查询的条数，而不是分页后的条数。
     "list": [               // list：本次查询的pageSize条数据，实际数据可能少于pageSize条
     {
     "password": "11111",    // 实体类的字段，下同
     "id": 1,
     "username": "1111"
     },
     {
     "password": "222",
     "id": 2,
     "username": "222"
     },
     {
     "password": "3333",
     "id": 3,
     "username": "333"
     },
     {
     "password": "444",
     "id": 4,
     "username": "4444"
     },
     {
     "password": "5555",
     "id": 5,
     "username": "555"
     }
     ],
     "pageNum": 1,               //pageNum：传递给startPage的参数，表示当前返回的页号，如果小于等于1，当1处理。
     "pageSize": 5,              //pageSize：传递给startPage的参数,表示该页最多返回的记录数。
     "size": 5,                  //size：实际返回的记录数，因为数据条数可能<pageSize，如最后一页。
     "startRow": 1,              //返回的数据在表中的开始行，从1开始。
     "endRow": 5,                //返回的数据在表中的结束行。
     "pages": 3,                 //pages: 查询到的数据总页数，等于数据库中查询到的总数据条数/pageSize [+1]?
     "prePage": 0,               //prePage：当前页的上一页，>=0
     "nextPage": 2,              //nextPage: 当前页的下一页
     "isFirstPage": true,        //是否是第一页，即pageNum==1
     "isLastPage": false,        //是否是最后一页：即pageNum==Max(查询到的总数据条数/pageSize [+1])
     "hasPreviousPage": false,   //hasPreviousPage：是否有上一页,如果当前页为第1页,则没有上一页
     "hasNextPage": true,        //是否有下一页
     "navigatePages": 8,         //页面显示的页码个数，最大为8，如果总页码超过该值，则会只能判断保留的8个页码
     "navigatepageNums": [       //显示有哪些页码
     1,
     2,
     3
     ],
     "navigateFirstPage": 1,     //页码列表中最小的页码
     "navigateLastPage": 3,      //页码列表中最大的页码
     "lastPage": 3,              //值同navigateLastPage
     "firstPage": 1              //值同navigateFirstPage
     }

     2.startPage(int pageNum,int pageSize)
     经测试:
     pageNum: <=0,当做1处理
     pageSize: <=0,则不分页查询，进行全量查询，返回所有数据,并且返回PageInfo对象pageNum和pageSiz都将是0.
     */
    @Test
    public void doSelectPageInfoTest() {
        // 将查询结果封装到PageInfo
        // 默认count=true,会计算PageInfo中total,prePage,nextPage,hasNextPage,hasPreviousPage等字段的值...
        // 如果设置count=false,将不会计算这些字段的值
        PageInfo<Object> objectPageInfo = PageHelper.startPage(1, 10,false).doSelectPageInfo(() -> mapper.testPageHelper());
    }

    /**
     * 3.Page<E> doSelectPage(ISelect select)
     * Page较PageInfo而言，不支持对于导航的统计，只统计总数(total)和总页数(pages)
     *
     * 返回类型Page字段说明(count=true):
     * Page{
     * count=true,
     * pageNum=2,                   //调用startPage传递的pageNum
     * pageSize=10,                 //调用startPage传递的pageSize
     * startRow=10,                 //与doSelectPageInfo不同的是,startRow基于0而不是1,并且下标[startRow,endRow)的记录才会被返回
     * endRow=20,                   //endRow对应的记录不会被返回
     * total=24,                    //SQL不分页情况下总查询条数
     * pages=3,                     //SQL查询到的数据按每页pageSize条记录可以得到的分页总数
     * reasonable=true,
     * pageSizeZero=false}
     * [{password=123456, last_login_time=2021-03-15 03:11:56.0, id=1255, age=1, username=default-name9}, {password=123456, last_login_time=2021-03-15 03:11:57.0, id=1256, age=1, username=default-name11}, {password=123456, last_login_time=2021-03-15 03:11:58.0, id=1257, age=1, username=default-name12}, {password=123456, last_login_time=2021-03-15 03:11:58.0, id=1258, age=1, username=default-name13}, {password=123456, last_login_time=2021-03-15 03:11:59.0, id=1259, age=1, username=default-name14}, {password=123456, last_login_time=2021-03-15 03:12:00.0, id=1260, age=1, username=default-name15}, {password=123456, last_login_time=2021-03-15 03:12:01.0, id=1261, age=1, username=default-name16}, {password=123456, last_login_time=2021-03-15 03:12:02.0, id=1262, age=1, username=default-name17}, {password=123456, last_login_time=2021-03-15 03:12:31.0, id=1263, age=1, username=default-name18}, {password=123456, last_login_time=2021-03-15 03:12:31.0, id=1264, age=1, username=default-name19}]
     *
     *
     * 返回类型Page字段说明(count=true):
     * Page<Object>: Page{count=false, pageNum=1, pageSize=10, startRow=0, endRow=10, total=-1, pages=1, reasonable=true, pageSizeZero=false}[{password=123456, last_login_time=2021-03-15 03:11:37.0, id=1245, age=1, username=user1}, {password=123456, last_login_time=2021-03-15 03:11:50.0, id=1246, age=1, username=default-name1}, {password=123456, last_login_time=2021-03-15 03:11:51.0, id=1247, age=1, username=default-name2}, {password=123456, last_login_time=2021-03-15 03:11:52.0, id=1248, age=1, username=default-name3}, {password=123456, last_login_time=2021-03-15 03:11:52.0, id=1249, age=1, username=default-name4}, {password=123456, last_login_time=2021-03-15 03:11:53.0, id=1250, age=1, username=default-name5}, {password=123456, last_login_time=2021-03-15 03:11:54.0, id=1251, age=1, username=default-name6}, {password=123456, last_login_time=2021-03-15 03:11:55.0, id=1252, age=1, username=default-name7}, {password=123456, last_login_time=2021-03-15 03:11:55.0, id=1253, age=1, username=default-name8}, {password=123456, last_login_time=2021-03-15 03:11:56.0, id=1254, age=1, username=default-name10}]
     * 即不会统计sql能够查询到的数据总数以及分页总数,在不需要统计这些信息的时候请使用startPage(pageNum,pageSize,false)
     *
     * 其中是否统计count属性的设置是通过PageHelper.startPage来设置的，默认true
     */
    @Test
    public void doSelectPage() {
        // Page
        // demo: 不统计total和pages
        /*Page<Object> objects = PageHelper.startPage(1, 10,false).doSelectPage(() -> {
            mapper.testPageHelper();
        });
        TestHelper.println("Page<Object>", objects);
        */

        //demo: 统计total和pages(默认)
        Page<Object> objects = PageHelper.startPage(0, 1).doSelectPage(() -> {
            mapper.testPageHelper();
        });

        TestHelper.println("Page<Object>", objects);
    }

    /**
     * 4.API测试
     * ----------------------------------------doCount------------------------------------
     * long doCount(ISelect select)
     * 原理根据查询的返回的结果数，统计个数，因此查询的数据越多，消耗时间越长
     * 不会进行分页查询，除非sql中显示添加了limit分页语句
     *
     * 注意：startPage()传递的pageNum和pageSize会被忽略，不会进行分页查询，除非sql中添加了limit分页语句
     */
    @Test
    public void doCountTest(){
        TimeHelper.start();
        long count = PageHelper.startPage(0, 1).doCount(() -> {
            mapper.testPageHelper();
        });
        TimeHelper.finish();

        TimeHelper.start();
        long count2 = PageHelper.startPage(0, 1).doCount(() -> {
            mapper.testPageHelper();
        });
        TimeHelper.finish();
    }
}