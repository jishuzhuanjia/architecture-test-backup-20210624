package com.zj.test.elastic_search.util;

import com.alibaba.fastjson.JSONObject;
import com.zj.test.util.TestHelper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 1.测试前提: 必须配置es /config/elasticsearch.yml
 * 1.1.transport.tcp.port: 9300     开启9300 transport通信端口
 * 1.2.cluster.name: my-application     集群名，必须配置，需要和application.yml中配置的集群名一样，否则会报错没有可用节点。
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class ElasticsearchUtilTest {

    @Autowired ElasticsearchUtil elasticsearchUtil;

    /**
     * 1.创建索引
     * public static boolean createIndex(String index)
     *
     * 1.1.创建索引名必须小写，否则报异常:
     * [indexCreatedByElasticsearchUtil] InvalidIndexNameException[Invalid index name [indexCreatedByElasticsearchUtil], must be lowercase
     *
     * 1.2.如果指定名索引已经存在，则报异常:
     * [index-created-by-elasticsearchutil/8Ky0VBdyRHC7WXsnfwPRug] ResourceAlreadyExistsException[index [index-created-by-elasticsearchutil/8Ky0VBdyRHC7WXsnfwPRug] already exists
     * ]
     *
     * 创建完成后，索引mappings:
     * "mappings": { },
     */
    @Test
    void createIndex() {
        // [indexCreatedByElasticsearchUtil] InvalidIndexNameException[Invalid index name [indexCreatedByElasticsearchUtil], must be lowercase
        //elasticsearchUtil.createIndex("indexCreatedByElasticsearchUtil");

        // 创建成功
        elasticsearchUtil.createIndex("index-created-by-elasticsearchutil");
    }

    /**
     * 2.删除索引
     * public static boolean deleteIndex(String index)
     *
     * 如果指定名的索引不存在，则会报错：
     * [index-created-by-elasticsearchutil] IndexNotFoundException[no such index
     * ]
     */
    @Test
    void deleteIndex() {
        elasticsearchUtil.deleteIndex("index-created-by-elasticsearchutil");
    }

    /**
     * 3.判断索引是否存在，存在返回true,否则返回false
     * public static boolean isIndexExist(String index)
     */
    @Test
    void isIndexExist() {
        TestHelper.println(".isIndexExist(\"index-created-by-elasticsearchutil\")",elasticsearchUtil.isIndexExist("index-created-by-elasticsearchutil"));
    }

    /**
     * 4.判断类型是否存在
     * public boolean isTypeExist(String index,String type)
     *
     * @return 如果<code>index</code>或<code>type</code>不存在，则返回false,否则返回true
     */
    @Test
    void isTypeExist() {
        // true
        TestHelper.println("isTypeExist(\"user\",\"user\")",elasticsearchUtil.isTypeExist("user","user"));
        // false
        TestHelper.println("isTypeExist(\"user\",\"user\")",elasticsearchUtil.isTypeExist("user","user3"));
        TestHelper.println("isTypeExist(\"user\",\"user\")",elasticsearchUtil.isTypeExist("usersdadsd","user"));
    }

    /**
     * 5.插入/更新
     *
     * 5.1。public static String addData(JSONObject jsonObject,String index,String type)
     * 尝试将json数据插入到index的type类型中去，由于没有指定id，es会自动为插入的数据生成唯一的32为字符串ID，如039F59A5CCEA421C9FB15E60B187BD9E
     * 需要注意的是index参数和type参数都不能为null,即不能省略，如果index和type参数都指定了，但是对应的index或type不存在，则es会自动创建。
     * 由于每次插入的数据id都不会相同，因此该方法不能用来更新数据。
     *
     * 5.2.public static String addData(JSONObject jsonObject,
     *                              String index,
     *                              String type,
     *                              String id)
     * 不同的是，可以指定插入数据的id，如果id不存在则是插入，如果id已存在，则是更新操作。
     */
    @Test
    void addData() {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("username","username-addData");
        jsonObject1.put("password","password-addData");
        //如果type不存在:
        //java.lang.IllegalArgumentException: Rejecting mapping update to [index-created-by-elasticsearchutil] as the final mapping would have more than 1 type: [user1, user]
        elasticsearchUtil.addData(jsonObject1,"index-created-by-elasticsearchutil","user");

        elasticsearchUtil.addData(jsonObject1,"index-created-by-elasticsearchutil","user","2");

        //尝试插入到不存在的索引
        //结果:成功，会自动创建索引和type
        elasticsearchUtil.addData(jsonObject1,"index11","user","2");

        //尝试是否可以省略type
        //不可以，报错:
        //org.elasticsearch.action.ActionRequestValidationException: Validation Failed: 1: type is missing;
        //elasticsearchUtil.addData(jsonObject1,"index12",null,"2");

        // 尝试是否可以省略index
        // 不可以,报错:
        // org.elasticsearch.action.ActionRequestValidationException: Validation Failed: 1: index is missing;
        //elasticsearchUtil.addData(jsonObject1,null,"user","2");
    }

    @Test
    void testAddData() {
    }

    /**
     *
     * 6.删除已有的数据
     *
     * public static void deleteDataById(String index,
     *                                   String type,
     *                                   String id)
     * index和type参数不能为null,否则报错，
     * 如果指定的文档存在，则删除，如果指定的文档不存在，则什么都不作，直接返回。
     */
    @Test
    void deleteDataById() {
        //删除一个存在的id
        //elasticsearchUtil.deleteDataById("index-created-by-elasticsearchutil","user","2");

        //尝试删除一个不存在的id
        //结果: 不会报错
        //elasticsearchUtil.deleteDataById("index-created-by-elasticsearchutil","user","sdsd22dsadsd");

        //尝试从一个不存在的index删除数据
        //报错:
        //[index-created-by-elasticsearchutil1] IndexNotFoundException[no such index
        //]
        //elasticsearchUtil.deleteDataById("index-created-by-elasticsearchutil1","user","7EBEF6C243E44DD88DB7800FEA8E0BCD");

        //尝试从一个不存在的type删除数据
        //报错:
        //java.lang.IllegalArgumentException: Rejecting mapping update to [index-created-by-elasticsearchutil] as the final mapping would have more than 1 type: [user1, user]
        //elasticsearchUtil.deleteDataById("index-created-by-elasticsearchutil","user1","7EBEF6C243E44DD88DB7800FEA8E0BCD");

        //尝试不传递index参数
        //报错:
        //org.elasticsearch.action.ActionRequestValidationException: Validation Failed: 1: index is missing;
        //elasticsearchUtil.deleteDataById(null,"user","7EBEF6C243E44DD88DB7800FEA8E0BCD");

        //尝试不传递type参数
        //报错:
        //org.elasticsearch.action.ActionRequestValidationException: Validation Failed: 1: type is missing;
        elasticsearchUtil.deleteDataById("index-created-by-elasticsearchutilnull",null,"7EBEF6C243E44DD88DB7800FEA8E0BCD");
    }

    /**
     * 7.更新。version字段会增加
     *
     * 如果index不存在，则会创建index,但是不会定义type,此时index mappings:
     * "mappings": { },没有type
     */
    @Test
    void updateDataById() {
        JSONObject updateJSONObject = new JSONObject();
        updateJSONObject.put("username","newUsername");
        elasticsearchUtil.updateDataById(updateJSONObject,"abcdefg","user","4BB0424BE36B425783AC4396F76D1101");
    }

    /**
     * 8.查找
     * public static Map<String, Object> searchDataById(String index,
     *                                                String type,
     *                                                String id,
     *                                                String fields)
     * 参数说明:
     * fields: 需要返回的字段，多个字段以','隔开，如果传递""或null,则返回所有字段。
     *
     * 如果index不存在，报错:
     * [index-created-by-elasticsearchutil787565444] IndexNotFoundException[no such index
     * ]
     *
     * 如果type不存在: 返回null。
     *
     * 如果id不存在，返回null。
     */
    @Test
    void searchDataById() {
        Map<String, Object> stringObjectMap = elasticsearchUtil.searchDataById("index-created-by-elasticsearchutil", "user", "4BB0424BE36B425783AC4396F76D110xxxx1", null);
        TestHelper.println("searchDataById(\"index-created-by-elasticsearchutil\", \"user\", \"4BB0424BE36B425783AC4396F76D1101\", \"username\")",stringObjectMap);
    }

    /**
     * 9.请求体复杂查询
     */
    @Test
    void searchDataPage() {
        // 模糊查询
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("username", "*name*");
        WildcardQueryBuilder wildcardQueryBuilder1 = QueryBuilders.wildcardQuery("password", "*word*");

        // 布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 必须
        boolQueryBuilder.must(wildcardQueryBuilder);
        boolQueryBuilder.must(wildcardQueryBuilder1);

        EsPage users = elasticsearchUtil.searchDataPage("index-created-by-elasticsearchutil", "user", 1, 5, boolQueryBuilder, "username", "", "");

        // id字段一定返回的字段
        //  {password=password-addData, id=5FDB173EC33E476BA07480A6F2DF3C33, username=username-addData}]
        TestHelper.println("elasticsearchUtil.searchDataPage(\"index-created-by-elasticsearchutil\", \"user\", 1, 5, boolQueryBuilder, \"username,password\", \"\", \"\")",users.getRecordList());
    }

    /**
     * 略
     */
    @Test
    void searchListData() {

    }
}