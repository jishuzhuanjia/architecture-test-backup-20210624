package com.zj.test.jpa.dao;

import com.zj.test.jpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/13 16:56
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

@Repository // JpaRepository类型参数2影响到后续调用JpaRepository方法时，主键的类型
            // 并且这里指定的主键参数类型需要和实体类中主键字段相同,否则报错:
            // org.springframework.dao.InvalidDataAccessApiUsageException: Provided id of the wrong type for class com.zj.test.jpa.entity.Teacher. Expected: class java.lang.Integer, got class java.lang.Long; nested exception is java.lang.IllegalArgumentException: Provided id of the wrong type for class com.zj.test.jpa.entity.Teacher. Expected: class java.lang.Integer, got class java.lang.Long
/*public interface TeacherDAO extends JpaRepository<Teacher,Long> {
}*/
public interface TeacherDAO extends JpaRepository<Teacher,Integer> {
}
