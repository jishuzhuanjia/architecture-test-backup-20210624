package com.mybatis.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.UserPO;
import com.mybatis.mapper.zj.MappejarMapper;
import com.zj.test.util.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

/* @author: zhoujian
 * @create-time: 2020年9月17日 12:12:36
 * @description: 1.mybatis查询Example的使用
 * @version: 1.0
 */
@RestController
@RequestMapping("/test/mybatis/pagehelper")
public class Test005Controller {

    @Autowired
    MappejarMapper mappejarMapper;

    @RequestMapping("test005")
    public PageInfo<Map<Object, Object>> test003(Integer pageNum, Integer pageSize) {
        TestHelper.startTest("mybatis example使用测试");

        Example example = new Example(UserPO.class);
        example.createCriteria().andGreaterThan("id",5);

        return PageHelper.startPage(1,5).doSelectPageInfo(()-> mappejarMapper.selectByExample(example));
    }
}