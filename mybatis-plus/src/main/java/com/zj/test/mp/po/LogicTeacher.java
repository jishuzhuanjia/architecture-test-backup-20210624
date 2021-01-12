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
public class LogicTeacher implements Serializable {

    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    Integer age;

    @TableLogic// 指定该字段是逻辑删除字段
    @TableField("flag")
    Integer deleted;
}
