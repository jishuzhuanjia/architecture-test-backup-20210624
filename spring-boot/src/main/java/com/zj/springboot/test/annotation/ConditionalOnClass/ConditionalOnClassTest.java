package com.zj.springboot.test.annotation.ConditionalOnClass;

import com.zj.test.util.TestHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 22:08
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
/**
 * @ConditionalOnClass: 只有指定的类都已经加载，条件才满足
 * 注意: 并不是要求实例化
 *
 * Class[] value: 所有的类型都必须加载
 * String[] name: 和value不同的是,该属性指定的是类的全限定名
 */
// @Configuration
@ConditionalOnClass(Bean1.class)
public class ConditionalOnClassTest {

    public ConditionalOnClassTest() {
        TestHelper.println("ConditionalOnClassTest");
    }
}
