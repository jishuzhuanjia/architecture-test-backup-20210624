package com.zj.test.spring.annotation;

import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 15:21
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service
@Valid
public class NotNullTestServiceImpl {

    public void println(@NotNull String content, int count){
        for (int i = 0; i < count; i++) {
            System.out.println(content);
        }
    }
}
