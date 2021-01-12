package com.zj.test.mp.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/11 16:19
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Data
public class Teacher implements Serializable {

    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    Integer age;
}
