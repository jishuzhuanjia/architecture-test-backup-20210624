package com.zj.test.spring.annotation;

import com.zj.test.util.TestResultTips;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 15:32
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@RequestMapping("/test/spring/annotation")
@RestController

public class NotNullController {

    @RequestMapping("notnull")
    public String test1(@Valid @NotNull String content){
        System.out.println(content);
        return TestResultTips.SEE_AT_CONSOLE;
    }
}
