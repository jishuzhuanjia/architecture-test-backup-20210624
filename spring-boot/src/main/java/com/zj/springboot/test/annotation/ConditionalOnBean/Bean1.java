package com.zj.springboot.test.annotation.ConditionalOnBean;

import com.zj.test.util.TestHelper;
import lombok.Setter;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 21:25
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
//@Component
@Setter
@MyAnnotation
@MyAnnotation2
public class Bean1 {

    public Bean1() {
        TestHelper.println("bean1");
    }
}
