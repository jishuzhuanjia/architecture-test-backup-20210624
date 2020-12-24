package com.zj.test.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zj.test.dubbo.service.DubboTestService;

/* @author: zhoujian
 * @create-time: 2020/9/28 19:45
 * @description:
 * @version: 1.0
 */

@Service()
public class DubboTestServiceImpl implements DubboTestService {
    @Override
    public void println(String string,int count) {
        for(int i=0;i<count;i++){
            System.out.println(string);
        }
    }

    @Override
    public String printJSON(DubboTestPO loginingUser,Integer loginTimes) {
        System.out.println(loginingUser);
        System.out.println("登录次数: " + loginTimes);
        return "invoke dubbo printJSON() success!";
    }
}
