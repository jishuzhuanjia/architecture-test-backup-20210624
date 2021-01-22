package com.zj.springboot.test.annotation.ConditionalOnBean;

import com.zj.test.util.TestHelper;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 21:21
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

/**
 * 该注解是把容器中的bean作为条件
 * 注意: 并不一定条件Bean必须要在本类之前实例化，也可以在之后。  // 可能只是先简单判断是否有实例化注解
 *
 * Class[] value: 指定的类有实例化注解,如果指定多个Class,则必须所有类都有实例化注解,条件才符合
 *
 * String[] type: 和value作用相同，不同的是是通过指定类的全限定名而不是类型。
 *
 * annotation: annotation定义的所有注解在value和type指定的bean中至少出现一次,不必都在一个bean上。
 * 注意: annotation不能是像@Setter这样的源码级别注释，否则条件总是不会满足。
 *
 * String[] name: 指定的bean name必须都存在，不必是type和value指定的bean的name。
 * 即和value和type不影响
 *
 * SearchStrategy search:
 *
 * Class<?>[] parameterizedContainer:
 */
// type demo
// @ConditionalOnBean(type = {"com.zj.springboot.test.annotation.ConditionalOnBean.Bean2","com.zj.springboot.test.annotation.ConditionalOnBean.Bean1"})
// annotation demo
// @ConditionalOnBean(value = {Bean1.class,Bean2.class,Bean3.class},annotation = {MyAnnotation.class,MyAnnotation2.class})
// @ConditionalOnBean(value = {Bean1.class},name = {"mybean"})
// @Configuration
public class AConditionalOnBeanTest {

    public AConditionalOnBeanTest() {
        TestHelper.println("Conditions all ok.");
    }
}
