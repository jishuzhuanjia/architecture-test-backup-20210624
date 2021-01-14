package com.zj.test.jpa.test.field_mapping.dao;

import com.zj.test.jpa.test.field_mapping.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/14 17:44
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Repository
public interface FieldMappingDAO extends JpaRepository<TeacherEntity,Integer> {
}
