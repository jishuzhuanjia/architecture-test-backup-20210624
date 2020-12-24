package com.zj.test.mybatis.controller.zj.pagehelper;

import com.github.pagehelper.PageHelper;
import com.zj.test.mybatis.mapper.zj.Test003Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/* @author: zhoujian
 * @create-time: 2020/9/17 11:21
 * @description: 测试mybatis pagehelper整合成功
 * @version: 1.0
 */
@RestController
@RequestMapping("/test/mybatis/pagehelper")
public class Test003Controller {
    @Autowired
    Test003Mapper mapper;

    @RequestMapping("test003")
    public List<Map<Object, Object>> test003(Integer pageNum, Integer pageSize) {
        System.err.println("分页查询");
        System.out.println("pageNum: "+ pageNum);
        System.out.println("pageSize: "+ pageSize);

        if (pageNum != null && pageSize != null){
            /*pageHelper参数说明：
            pageNum：当前为第几页,pageNum应从1开始，如果pageNum<=0，当做1来处理。
            pageSize：一次返回的记录条数。*/
            PageHelper.startPage(pageNum, pageSize);
        }
        return mapper.testPageHelper();
    }
}
