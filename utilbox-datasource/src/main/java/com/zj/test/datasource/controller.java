package com.zj.test.datasource;

import com.zj.test.datasource.mapper.TestMapper;
import com.zj.test.util.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/21 11:07
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@RestController
@RequestMapping("datasource")
public class controller {

    @Autowired
    TestMapper testMapper;

    @RequestMapping("test1")
    public String test(){
        TestHelper.println(testMapper.selectTest());
        return "ok";
    }
}
