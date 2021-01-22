package com.zj.springboot.test.annotation.ConditionalOnBean;

import com.zj.test.util.TestHelper;
import org.springframework.stereotype.Component;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 21:25
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Component
public class Bean3 {
    public Bean3() {
        TestHelper.println("bean3");
    }
}
