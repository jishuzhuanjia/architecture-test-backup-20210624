package com.zj.test.mp.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/11 16:19
 * @description: 逻辑删除测试实体类
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Data
// 指定表名
@TableName("teacher")
public class LogicDeleteTeacher implements Serializable {

    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    Integer age;

    // 指定该字段是逻辑删除字段,如果属性名和mybatis-plus全局设置的逻辑删除字段相同,则不需要添加该注解
    @TableLogic
    // 如果实体类属性名和表中列名不相同,需要通过@TableField添加手动映射。
    @TableField("flag")
    Integer deleted;
}
