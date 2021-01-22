package com.zj.springboot.test.annotation.ConditionalOnProperty;

import com.zj.test.util.TestHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 14:38
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
// @Component
// name和value属性是互斥的,不能同时出现,否则报错:
// The name and value attributes of @ConditionalOnProperty are exclusive,如果想要匹配值,使用havingValue
@ConditionalOnProperty(prefix = "xxx",name = "username",havingValue = "usernameInConditional",/*value = "usernameInConditional",*/
        matchIfMissing = false)
public class ConditionalOnPropertyTest {

    public ConditionalOnPropertyTest(){
        TestHelper.println("ConditionalOnPropertyTest");
    }
}
