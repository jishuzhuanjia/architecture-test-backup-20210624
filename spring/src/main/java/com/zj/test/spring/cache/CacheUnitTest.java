package com.zj.test.spring.cache;

import com.zj.test.spring.Application;
import com.zj.test.spring.cache.service.impl.CacheServiceImpl;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *      spring cache缓存测试
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/7 21:02
 * @is-finished: false
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class CacheUnitTest {

    @Autowired
    CacheServiceImpl cacheService;

    /**
     * <p>
     *     1.测试: @Cacheable入门测试
     * </p>
     *
     * 【出入参记录】
     * --1.开启缓存前：即启动类不添加@EnableCacheing
     * [main] - 查询数据库
     * [main] - id 1用户名: username1
     * [main] - 查询数据库
     * [main] - id 1用户名: username1
     * [main] - 查询数据库
     * [main] - id 1用户名: username1
     * [main] - 查询数据库
     * [main] - id 2用户名: username2
     * [main] - 查询数据库
     * [main] - id 2用户名: username2
     * [main] - 查询数据库
     * [main] - id 2用户名: username2
     * [main] - 查询数据库
     * [main] - id 99用户名: username-other
     * [main] - 查询数据库
     * [main] - id 99用户名: username-other
     * [main] - 查询数据库
     * [main] - id 99用户名: username-other
     * 可以看到，没有缓存结果。
     *
     * --2.开启缓存：EnableCacheing
     * [main] - 查询数据库
     * [main] - id 1用户名: username1
     * [main] - id 1用户名: username1
     * [main] - id 1用户名: username1
     * [main] - 查询数据库
     * [main] - id 2用户名: username2
     * [main] - id 2用户名: username2
     * [main] - id 2用户名: username2
     * [main] - 查询数据库
     * [main] - id 99用户名: username-other
     * [main] - id 99用户名: username-other
     * [main] - id 99用户名: username-other
     * 可以看到，对相同用户的用户名进行查询时，只会查询数据库一次。
     *
     * 【结论】
     *
     * 【注意点】
     *
     */
    @Test
    public void cacheableTest() {

        TestHelper.println("id 1用户名", cacheService.getUsernameById(1));
        TestHelper.println("id 1用户名", cacheService.getUsernameById(1));
        TestHelper.println("id 1用户名", cacheService.getUsernameById(1));

        TestHelper.println("id 2用户名", cacheService.getUsernameById(2));
        TestHelper.println("id 2用户名", cacheService.getUsernameById(2));
        TestHelper.println("id 2用户名", cacheService.getUsernameById(2));

        TestHelper.println("id 99用户名", cacheService.getUsernameById(99));
        TestHelper.println("id 99用户名", cacheService.getUsernameById(99));
        TestHelper.println("id 99用户名", cacheService.getUsernameById(99));
    }

    /**
     * <p>
     *     2.测试: @CacheEvict清除缓存测试
     * </p>
     *
     * 【出入参记录】
     * [main] - 查询数据库
     * [main] - id 1用户名: username1
     * [main] - id 1用户名: username1
     * [main] - 清除用户: 1缓存用户名.
     * [main] - 查询数据库
     * [main] - id 1用户名: username1
     *
     * 【结论】
     *
     * 【注意点】
     *
     */
    @Test
    public void evictTest() {
        TestHelper.println("id 1用户名", cacheService.getUsernameById(1));
        TestHelper.println("id 1用户名", cacheService.getUsernameById(1));

        cacheService.deleteUsernameById(1);

        // 如果缓存删除成功，则会从数据库查询用户名
        TestHelper.println("id 1用户名", cacheService.getUsernameById(1));
    }

    /**
     * <p>
     *     3.@Cacheable unless属性测试
     * </p>
     *
     * 【出入参记录】
     *
     * 【结论】
     * 1.unless: 满足unless条件的数据不会被缓存。
     * 2.unless可用来设置不缓存null数据。
     *
     * 【注意点】
     *
     */
    @Test
    public void CacheableUnlessTest() {
        // 检测缓存是否有效
        TestHelper.println("用户id 1用户名", cacheService.getUsernameById(1));
        TestHelper.println("用户id 1用户名", cacheService.getUsernameById(1));

        TestHelper.println("用户id 3用户名", cacheService.getUsernameById(3));
        TestHelper.println("用户id 3用户名", cacheService.getUsernameById(3));

    }

    /**
     * <p>
     *     4.测试: 使用@CachePut更新缓存
     * </p>
     *
     * 【出入参记录】
     *
     * --出参
     * [main] - 查询数据库
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: update-username
     *
     * -- @CachePut unless条件讨论出入参：
     1.当带上unless = "#result==null"，且方法返回为null时，com.zj.test.spring.cache.CacheUnitTest#cachePutTest()
     * 出参：
     * [main] - 查询数据库
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: username1
     * 此时没有更新缓存，还是原来的值。
     *
     * 2.当带上unless = "#result==null"，且方法返回不为null时，com.zj.test.spring.cache.CacheUnitTest#cachePutTest()
     * 出参：
     * [main] - 查询数据库
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: username1
     * [main] - 用户id 1的用户名: update-username
     * 此时缓存更新成功。
     *
     * 【结论】
     * 1.@Cacheable 和 @CachePut比较：
     * @Cacheable 的逻辑是：查找缓存 - 有就返回 -没有就执行方法体 - 将结果缓存起来；
     * @CachePut 的逻辑是：执行方法体 - 将结果缓存起来；
     * 所以 @Cacheable 适用于查询数据的方法，@CachePut 适用于更新数据的方法。
     *
     * 【注意点】
     *
     */
    @Test
    public void cachePutTest(){

        TestHelper.println("用户id 1的用户名",cacheService.getUsernameById(1));
        TestHelper.println("用户id 1的用户名",cacheService.getUsernameById(1));

        // 更新缓存
        cacheService.updateUsernameById(1);

        TestHelper.println("用户id 1的用户名",cacheService.getUsernameById(1));
    }
}
