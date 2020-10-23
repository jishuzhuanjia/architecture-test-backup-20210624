package com.zj.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DubboTestProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboTestProviderApplication.class, args);
    }

    @RequestMapping("/springmvc/test1")
    public String test1(){
        return "ok";
    }
}
