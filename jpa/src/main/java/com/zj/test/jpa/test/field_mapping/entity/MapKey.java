package com.zj.test.jpa.test.field_mapping.entity;

import lombok.Data;

import javax.persistence.Embeddable;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/16 0:43
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Data
@Embeddable
public class MapKey {
    String des;

    Integer orderNo;
}
