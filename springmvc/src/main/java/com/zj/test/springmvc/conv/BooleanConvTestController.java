package com.zj.test.springmvc.conv;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/20 10:52
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@RequestMapping("test/springmvc/conv")
@RestController
public class BooleanConvTestController {
    @RequestMapping("test1")
    public String test1(Boolean virtual){
        System.out.println(virtual);
        return "ok";
    }
}
