package com.zj.test.mp.controller;

import com.zj.test.mp.mapper.TeacherMapper1;
import com.zj.test.mp.po.Teacher;
import com.zj.test.util.TestHelper;
import com.zj.test.util.TestResultTips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/13 11:10
 * @description: myabtis-plus数据安全保护测试controller
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Controller
@RequestMapping("/mp/datasecure")
public class DataSecureController {

    @Autowired
    TeacherMapper1 teacherMapper;

    /**
     * 1.测试: mybatis-plus使用安全保护配置数据情况下,是否有效?
     *
     * 【测试输出】
     * 查询到的数据:
     * Teacher(id=2764817, name=name2, age=10, sex=null, teachCourse=null)
     *
     * 【结论】
     * 1.有效
     * 2.数据安全保护默认对于application.properties和application.yml文件都有效。
     */
    @RequestMapping("select")
    @ResponseBody
    public String test1(){
        TestHelper.startTest("mybatis-plus数据安全保护测试");
        Teacher teacher = teacherMapper.selectById(2764817);
        TestHelper.println("查询到的数据:\n" + teacher);
        return TestResultTips.SEE_AT_CONSOLE;
    }
}
