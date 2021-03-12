package com.zj.test.mybatis.controller.zj.dynamicsql.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/12 15:21
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Setter
@Getter
public class BookPO {
    private Integer bId;

    /**
     * 书名，可模糊可精确查找，这取决于bookNameIsExact字段
     */
    private String bookName;
    private String authorName;
    Date create_time;

    /**
     * 根据书名精确/模糊查找：
     * 为null或为0 - 模糊查找
     * 为1 - 精确查找
     */
    Integer bookNameIsExact;


    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "BookPO{" +
                "bId=" + bId +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
