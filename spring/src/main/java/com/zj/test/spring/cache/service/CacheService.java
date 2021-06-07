package com.zj.test.spring.cache.service;

/**
 * <p>
 *
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/7 21:03
 * @is-finished: false
 *
 */
public interface CacheService {

    /**
     * 根据用户id查询用户名
     */
    public String getUsernameById(Integer id);

    /**
     * 移除用户id缓存的用户名
     */
    public Object deleteUsernameById(Integer id);

    /**
     * 更新id用户的用户名
     */
    public String updateUsernameById(Integer id);


}
