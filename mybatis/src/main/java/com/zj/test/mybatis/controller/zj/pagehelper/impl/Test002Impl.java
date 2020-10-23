package com.zj.test.mybatis.controller.zj.pagehelper.impl;

import com.zj.test.mybatis.mapper.zj.Test002Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/* @author: zhoujian
 * @create-time: 2020/9/17 9:26
 * @description:
 * @version: 1.0
 */
@Service
public class Test002Impl{

    @Autowired
    Test002Mapper mapper;

    public void test() {
        List<Map<Object,Object>> result  = mapper.testSelect();
        System.out.println(result);
        System.out.println("共查询: " + result.size() +"条记录");
    }
}
