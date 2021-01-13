package com.zj.test.mp;

import com.baomidou.mybatisplus.core.toolkit.AES;
import com.zj.test.util.TestHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/11 16:12
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

@SpringBootApplication
@MapperScan({"com.zj.test.mp.mapper","com.zj.test.mp.test.code_generator.teacher_module.mapper"})
public class MpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpApplication.class, args);

    }
}
