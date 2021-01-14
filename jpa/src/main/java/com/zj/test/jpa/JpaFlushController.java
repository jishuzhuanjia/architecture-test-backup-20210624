package com.zj.test.jpa;

import com.zj.test.jpa.dao.TeacherDAO;
import com.zj.test.jpa.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/14 9:44
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@RestController
@RequestMapping("/jpa/flush")
public class JpaFlushController {
    @Autowired
    TeacherDAO teacherDAO;

    @RequestMapping("test1")
    @Transactional
    public void test1(){
        Teacher teacherForFlush = new Teacher();
        teacherForFlush.setSex("男");
        teacherForFlush.setName("flush");
        teacherForFlush.setAge(20);
        teacherDAO.saveAndFlush(teacherForFlush);

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
