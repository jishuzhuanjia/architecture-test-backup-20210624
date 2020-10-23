package com.zj.test.mybatis.controller.zj.pagehelper;

import com.zj.test.util.TestResultTips;
import com.zj.test.mybatis.controller.zj.pagehelper.impl.Test002Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author: zhoujian
 * @create-time: 2020/9/17 9:30
 * @description: 1.测试mybatis是否配置正确
 *               2.limit 1语句是否正确
 * @version: 1.0
 */
@RestController
@RequestMapping("/test/mybatis/pagehelper")
public class Test002Controller {
    @Autowired
    Test002Impl test002Impl;

    /*测试结果：1.mybatis配置正确。
            2.limit语句是正确的。*/
    @RequestMapping("test002")
    public String test1(){
        test002Impl.test();
        return TestResultTips.SEE_AT_CONSOLE;
    }
}
