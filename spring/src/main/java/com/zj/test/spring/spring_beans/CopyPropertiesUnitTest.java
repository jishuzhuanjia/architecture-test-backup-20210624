package com.zj.test.spring.spring_beans;

import com.zj.test.spring.spring_beans.po.UserDO;
import com.zj.test.spring.spring_beans.po.UserDOEditable;
import com.zj.test.spring.spring_beans.po.UserDTO;
import com.zj.test.util.TestHelper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/20 10:09
 * @description: spring-beans.jar BeanUtils工具测试
 * @version: 1.0
 * @finished: true
 * @finished-time: 2021年3月23日 11:13:10
 */
public class CopyPropertiesUnitTest {
    /**
     * 1.copyProperties()测试
     *
     * 1.1.需要属性名相同 + 同类型(包装类和简单类视为同一类型)
     * 【一些转换总结】
     * <p>
     * int <-> Integer
     * <p>
     * short 不能转 int
     * Short 不能转 int
     * <p>
     * int/Integer 不能转 boolean
     * <p>
     * 注意包装类转换到简单类型要防止null，否则：
     * Exception in thread "main" org.springframework.beans.FatalBeanException: Could not copy property 'j' from source to target; nested exception is java.lang.IllegalArgumentException
     *
     * 1.2.1.属性注意需要注意的点
     * source类必须要有getter方法，target必须要有getter和setter方法，否则映射值会失败
     * */
    @Test
    public void copyProperties() {
        /**
         * 001.copyProperties(source,target)
         * 作用：将source中的属性值，复制给target中的同名属性。
         *
         * 注意：
         * 1.source、target为对象。
         * 2.只会映射同名同类型的属性。
         * */

        UserDTO userDTO = new UserDTO();
        userDTO.username="zhoujian";
        userDTO.password="312";
        userDTO.age=25;
        userDTO.phone="15156444479";

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        TestHelper.println("userDO",userDO);

        /**
         * 002.copyProperties(source,target,editable)
         *
         * 作用：
         * 1.取target.class和editable.class类的属性交集。
         * 2.将source属性赋值给1结果中的同名属性，就是最终的结果
         *
         * 注意：editable类必须为target类的父类(包括Object.class)，或者相同类.
         * 即 target instanceof editable
         *
         * */
        /*String username="zhoujian";
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("zhoujian");
        userDTO.setPassword("312");
        userDTO.setAge(25);
        userDTO.setPhone("15156444479");

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO, UserDOEditable.class);
        TestHelper.println("userDO",userDO);*/

        /**
         * 003.copyProperties(Object source, Object target, String... ignoreProperties)
         * 将source属性复制给target的同名属性，ignoreProperties可以指定忽略的属性
         *
         * 注意:
         * 1.ignoreProperties属性名需要和类中属性名大小写一致，否则不会被忽略。
         *
         * 例：忽略password字段。
         * */
        /*UserDTO userDTO = new UserDTO();
        userDTO.setUsername("zhoujian");
        userDTO.setPassword("312");
        userDTO.setAge(25);
        userDTO.setPhone("15156444479");

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO,"password");
        TestHelper.println("userDO",userDO);*/
    }
}