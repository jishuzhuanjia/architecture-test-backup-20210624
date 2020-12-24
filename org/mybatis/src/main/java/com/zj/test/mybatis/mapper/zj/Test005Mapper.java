package com.zj.test.mybatis.mapper.zj;

import com.zj.test.mybatis.BaseMapper;
import com.zj.test.mybatis.po.zj.UserPO;

/*添加该注解，会被mybatis扫描到，而不需要在MapperScan的包中
注意：这个注解不能用来指定mapper jar开发模式中的mapper接口位置,否则：
tk.mybatis.mapper.MapperException: 无法获取实体类test.po.User对应的表名!
*/
//@Mapper
public interface Test005Mapper extends BaseMapper<UserPO> {

}
