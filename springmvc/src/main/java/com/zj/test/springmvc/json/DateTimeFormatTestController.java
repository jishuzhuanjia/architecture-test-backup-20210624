package com.zj.test.springmvc.json;

import com.zj.test.util.TestHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/* @author: zhoujian
 * @create-time: 2020/9/24 16:50
 * @description:
 * @version: 1.0
 */
@RequestMapping("test/springmvc/json")
@RestController
public class DateTimeFormatTestController {

    /*1.@DateTimeFormat注解测试*/
    @RequestMapping("test1")
    public void test1(DateFormatTestDTO dateFormatTestDTO){
        TestHelper.startTest("springmvc json注解@DateTimeFormat测试");
        TestHelper.println("请求参数对象: " + dateFormatTestDTO);
        TestHelper.println("hahaha");
        TestHelper.finishTest();
    }

    /*2.@JsonFormat注解测试
    * 【测试结果】
    * 页面返回{"loginTime":"2020-09-24 12:34:46"}，发现比我们北京时间2020年9月24日 20:36:49
    * 慢了8个小时
    * */
    @RequestMapping("test2")
    public JsonFormatTestPO test2(){
        TestHelper.startTest("springmvc json注解@JsonFormat测试");
        TestHelper.finishTest();
        JsonFormatTestPO po =new JsonFormatTestPO();
        po.setLoginTime(new Date());
        return po;
    }
}
