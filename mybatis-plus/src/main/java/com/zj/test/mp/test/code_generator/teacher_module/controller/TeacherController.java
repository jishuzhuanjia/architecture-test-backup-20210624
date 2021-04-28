package com.zj.test.mp.test.code_generator.teacher_module.controller;


import com.zj.test.mp.mapper.TeacherMapper1;
import com.zj.test.util.TestHelper;
import com.zj.test.util.TestResultTips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-01-13
 */
@RestController
// 规则: /生成时输入的模块名/生成时输入的表名
@RequestMapping("/teacher_module/teacher")
public class TeacherController{

    @Autowired
    TeacherMapper1 teacherMapper1;

    // mybatis-plus没有为我们生成具体的处理器, 这一部分留给我们自定义

    @RequestMapping("test1")
    public String test1(){
        TestHelper.println("查询到了数据",teacherMapper1.selectById(2765862));
        return TestResultTips.SEE_AT_CONSOLE;
    }



}
