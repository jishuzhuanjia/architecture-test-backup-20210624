package com.mybatis;
/* @author: zhoujian
 * @create-time: 2020/9/30 16:14
 * @description:
 * @version: 1.0
 */

import com.zj.util.springmvc.reqeust.bean.ReqBean;
import com.zj.util.springmvc.response.bean.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mybatis.mapper.zj.Test005Mapper;

import java.util.List;
import java.util.Map;

@RequestMapping("/test/springmvc/util")
@RestController
public class SpringMVCUtilTestController {

    @Autowired Test005Mapper mapper;

    @RequestMapping("test1")
    public RespBean<List<UserPO>> test1(@RequestBody ReqBean<Map> param) {
        TestHelper.startTest("spring-util测试");
        TestHelper.printSubTitle("参数");
        TestHelper.println(param.getParam());
        List<UserPO> userPOS = mapper.selectByExample(param.createExample(UserPO.class));
        TestHelper.printSubTitle("查询到的结果:");
        TestHelper.println(userPOS);
        return new RespBean<List<UserPO>>(userPOS);
    }
}
