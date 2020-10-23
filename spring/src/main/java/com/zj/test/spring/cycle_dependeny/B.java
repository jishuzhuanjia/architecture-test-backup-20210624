package com.zj.test.spring.cycle_dependeny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/16 13:38
 * @description: 循环依赖测试对象B
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Component
public class B {
    private String name = "B";
    @Autowired A a;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public A getA() {
        return a;
    }
}
