package com.zj.springboot.test.annotation.ConditionalOnExpression;

import com.zj.test.util.TestHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 23:04
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

/**
 * @ConditionalOnExpression: 支持spel表达式
 *
 * 属性:
 * String value: default "true"
 */
// ${}中的属性名也要注意避免全局常量对表达式的影响，如${java.version}就是一个常量: 表示的是java版本,如1.8.0_251
// ==1也会比较类型
// :比较也要注意类型，否则会有转换异常
@ConditionalOnExpression("${conditional.onexpress.flag}==true&&${x.java.version:true}&&${test.string}.equals('1231231')")
// @Configuration
public class ConditionalOnExpressionTest {

    public ConditionalOnExpressionTest() {
        TestHelper.println("ConditionalOnExpressionTest ok.");
    }
}
