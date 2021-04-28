package com.mybatis;
/* @author: zhoujian
 * @create-time: 2020/9/30 16:14
 * @description:
 * @version: 1.0
 */

import com.mybatis.mapper.zj.MappejarMapper;
import com.zj.test.util.TestHelper;
import com.zj.util.springmvc.reqeust.bean.ReqBean;
import com.zj.util.springmvc.response.bean.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/test/springmvc/util")
@RestController
public class SpringMVCUtilTestController {

    @Autowired
    MappejarMapper mapper;
    @RequestMapping("test1")
    public RespBean<List<UserPO>> test1(@RequestBody ReqBean<Map> param) {
        TestHelper.startTest("spring-util测试");
        TestHelper.startTest("参数");
        TestHelper.println(param.getParam());
        List<UserPO> userPOS = mapper.selectByExample(param.createExample(UserPO.class));
        TestHelper.startTest("查询到的结果:");
        TestHelper.println(userPOS);
        return new RespBean<List<UserPO>>(userPOS);
    }
}
