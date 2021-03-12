package com.mybatis.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.mapper.zj.Test003Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/* @author: zhoujian
 * @create-time: 2020年9月17日 12:12:36
 * @description: 1.将PageHelper分页查询的接口，封装到PageInfo测试
 *               2.PageInfo属性说明。
 * @version: 1.0
 */
@RestController
@RequestMapping("/test/mybatis/pagehelper")
public class Test004Controller {
    @Autowired
    Test003Mapper mapper;

    /*002.PageInfo属性说明
     @author: zhoujian
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
        "pageSize": 5,          //pageSize：传递给startPage的参数。
        "size": 5,              //size：实际返回的记录数，因为数据条数可能<pageSize，如最后一页。
        "startRow": 1,          //返回的数据在数据库中的开始行，从1开始。
        "endRow": 5,            //返回的数据在数据库中的结束行。
        "pages": 3,               //pages: 总页数，即数据库中查询到的总数据条数/pageSize [+1]
        "prePage": 0,             //prePage：当前页的上一页，>=0
        "nextPage": 2,            //nextPage: 当前页的下一页
        "isFirstPage": true,     // 是否是第一页，即pageNum==1
        "isLastPage": false,    //是否是最后一页：即pageNum==Max(查询到的总数据条数/pageSize [+1])
        "hasPreviousPage": false,  //hasPreviousPage：是否有上一页
        "hasNextPage": true,       //是否有下一页
        "navigatePages": 8,      //页面显示的页码个数，最大为8，如果总页码超过该值，则会只能判断保留的8个页码
        "navigatepageNums": [     //显示有哪些页码
                1,
                2,
                3
        ],
       "navigateFirstPage": 1,       //页码列表中最小的页码
        "navigateLastPage": 3,      //页码列表中最大的页码
        "lastPage": 3,              //值同navigateLastPage
        "firstPage": 1              //值同navigateFirstPage
    }*/
    @RequestMapping("test004")
    public PageInfo<Map<Object, Object>> test003(Integer pageNum, Integer pageSize) {
        System.err.println("分页查询");
        System.out.println("pageNum: " + pageNum);
        System.out.println("pageSize: " + pageSize);

        if (pageNum != null && pageSize != null) {
            /*pageHelper参数说明：
            pageNum：当前为第几页,pageNum应从1开始，如果pageNum<=0，当做1来处理。
            pageSize：一次返回的记录条数。*/

            // 001.将查询结果封装到PageInfo
            return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> mapper.testPageHelper());
        }
        return null;
    }
}