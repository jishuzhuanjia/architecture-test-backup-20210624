package com.zj.springboot.test.annotation.ConditionalOnSingleCandidate;

import com.zj.test.util.TestHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Configuration;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 23:33
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Configuration
/**
 * 指定的bean在容器中只有一个  //true
 * 或者虽然有多个但是指定首选的bean      //日后验证
 */
@ConditionalOnSingleCandidate(Bean7.class)
public class ConditionalOnSingleCandidateTest {

    public ConditionalOnSingleCandidateTest() {
        TestHelper.println("ConditionalOnSingleCandidateTest ok");
    }
}
