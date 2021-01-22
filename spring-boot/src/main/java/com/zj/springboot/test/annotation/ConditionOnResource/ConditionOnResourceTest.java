package com.zj.springboot.test.annotation.ConditionOnResource;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 23:26
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import com.zj.test.util.TestHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;

// @Configuration
/**
 * 可以用来判断类路径下是否存在指定的资源
 */
@ConditionalOnResource(resources= "classpath:com/zj/springboot/test/annotation/ConditionOnResource/1.properties")
public class ConditionOnResourceTest {

    public ConditionOnResourceTest() {
        TestHelper.println("ConditionOnResourceTest ok");
    }
}
