package com.zj.springboot.test.annotation.ConditionalOnJava;

import com.zj.test.util.TestHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Configuration;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 22:17
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

/**
 * @ConditionalOnJava: 把jvm版本作为条件，只有满足才会实例化本类
 *
 * 属性：
 * JavaVersion value:   作为条件的java版本
 * Range range()        指定范围，默认EQUAL_OR_NEWER(大于或等于value指定的版本),则满足条件
 *                      OLDER_THAN: 比value版本老则满足条件。
 */
@Configuration
@ConditionalOnJava(value = JavaVersion.ELEVEN,range = ConditionalOnJava.Range.OLDER_THAN)
public class ConditionalOnJavaTest {

    public ConditionalOnJavaTest() {
        TestHelper.println("success,you java version ok");
    }
}
