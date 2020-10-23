package com.zj.test.spring.cycle_dependeny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/16 13:41
 * @description: 循环依赖测试controller
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@RequestMapping("test/spring/cycledependeny")
@RestController
public class TestController {
    @Autowired A a;
    @RequestMapping("test1")
    public void test1(){
        System.out.println(a);
        System.out.println(a.getB());
    }
}
