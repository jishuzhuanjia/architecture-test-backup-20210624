package com.zj.test.spring.cache.service.impl;

import com.zj.test.spring.cache.service.CacheService;
import com.zj.test.util.TestHelper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/7 21:04
 * @is-finished: false
 *
 */
@Service
public class CacheServiceImpl implements CacheService {

    // 通过参数序号来引用参数，从p0开始，如果指定的参数不存在会报错：java.lang.IllegalArgumentException: Null key returned for cache operation (maybe you are using named params on classes without debug info?) Builder[public java.lang.String com.zj.test.spring.cache.service.impl.CacheServiceImpl.getUsernameById(java.lang.Integer)] caches=[username] | key='#p1' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'
    //@Cacheable(cacheNames = "username",key = "#p1")

    /**
     * 注意：condition不能用来排除空数据的缓存，因为：
     * @Cacheable condition如果为"#result!=null"，判断是在进入方法之前进行的，此时result总是为null,会导致不缓存结果。
     *
     * condition: 满足条件的数据会被缓存。
     *
     * 而unless: 满足条件的数据不会被缓存。
     *
     * */
    //@Cacheable(cacheNames = "username", key = "#id",condition = "#result!=null")

    // 通过参数名 + 不缓存null数据
    @Cacheable(cacheNames = "username", key = "#id", unless = "#result==null")
    @Override
    public String getUsernameById(Integer id) {

        TestHelper.println("查询数据库");
        if (id == 1)
            return "username1";
        else if (id == 2)
            return "username2";
        else if (id == 3)
            return null;
        else
            return "username-other";
    }

    /**
     * 与@Cacheable不同的是：@CacheEvict condition可对返回结果进行判断，而决定是否删除缓存,
     * 如对于条件condition = "#result==null"，当返回数据为null时才会清空对应的缓存。
     *
     * 但是需要注意的是@CacheEvit condition起作用的前提是：beforeInvocation为false,因为如果beforeInvocation为true,
     * 则不管condition表达式是否满足，在调用方法之前，就会将缓存清除。
     *
     */
    @CacheEvict(cacheNames = "username", key = "#id",beforeInvocation = true,condition = "#result==null")
    @Override
    public Object deleteUsernameById(Integer id) {

        TestHelper.println("清除用户: " + id + "缓存用户名.");
        return 1 ;
    }

    /**
     * 更新缓存，当返回结果为null时，不更新缓存，这种情况下缓存还是原来的值。
     *
     * 【测试过程】
     * 1.当带上unless = "#result==null"，且方法返回为null时，com.zj.test.spring.cache.CacheUnitTest#cachePutTest()
     * 出参：
     * [main] - 查询数据库
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: username1
     * 可以看到，此时没有更新缓存，缓存还是原来的值。
     *
     * 2.当带上unless = "#result==null"，且方法返回不为null时，com.zj.test.spring.cache.CacheUnitTest#cachePutTest()
     * 出参：
     * [main] - 查询数据库
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: update-username
     * 可以看到，此时更新了缓存。
     */
    @CachePut(cacheNames = "username",key = "#id",unless = "#result==null")
    @Override
    public String updateUsernameById(Integer id) {
        return "update-username";

    }
}
