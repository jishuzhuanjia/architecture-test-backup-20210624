package cn.zj.test.dubbo.service;

import cn.zj.test.dubbo.service.impl.DubboTestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboTestController {
    @Autowired
    DubboTestServiceImpl impl;

    @RequestMapping("/test/dubbo/test1")
    public String test1(){
        System.out.println("test1");

        impl.test1();
        return "ok";
    }


}

