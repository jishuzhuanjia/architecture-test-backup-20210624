package com.zj.test.elastic_search.util;

import com.alibaba.fastjson.JSONObject;
import com.zj.test.util.TestHelper;
import com.zj.test.util.TimeHelper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * 1.测试前提: 必须配置es /config/elasticsearch.yml
 * 1.1.transport.tcp.port: 9300     开启9300 transport通信端口
 * 1.2.cluster.name: my-application     集群名，必须配置，需要和application.yml中配置的集群名一样，否则会报错没有可用节点。
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class ElasticsearchUtilTest {

    @Autowired
    ElasticsearchUtil elasticsearchUtil;

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
        elasticsearchUtil.createIndex("index-created-by-elasticsearchutil2");
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
        TimeHelper.start();
        long startTime = System.currentTimeMillis();
        try {
            elasticsearchUtil.deleteIndex("index-created-by-elasticsearchutil2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeHelper.finish();
    }

    /**
     * 3.判断索引是否存在，存在返回true,否则返回false
     * public static boolean isIndexExist(String index)
     */
    @Test
    void isIndexExist() {
        TimeHelper.start();
        TestHelper.println("isIndexExist(\"index-created-by-elasticsearchutil\")", elasticsearchUtil.isIndexExist("index-created-by-elasticsearchutil"));
        TimeHelper.finish();
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
        TestHelper.println("isTypeExist(\"user\",\"user\")", elasticsearchUtil.isTypeExist("user", "user"));
        // false
        TestHelper.println("isTypeExist(\"user\",\"user\")", elasticsearchUtil.isTypeExist("user", "user3"));
        TestHelper.println("isTypeExist(\"user\",\"user\")", elasticsearchUtil.isTypeExist("usersdadsd", "user"));
    }

    /**
     * 5.插入/更新
     *
     *
     *
     * 5.1。public static String insertData(JSONObject jsonObject,String index,String type)
     * 尝试将json数据插入到index的type类型中去，由于没有指定id，es会自动为插入的数据生成唯一的32为字符串ID，如039F59A5CCEA421C9FB15E60B187BD9E
     * 需要注意的是index参数和type参数都不能为null,即不能省略，如果index和type参数都指定了，但是对应的index或type不存在，则es会自动创建。
     * 由于每次插入的数据id都不会相同，因此该方法不能用来更新数据。
     *
     * 用时: 50ms，很慢!
     *
     * 5.2.public static String insertData(JSONObject jsonObject,
     *                              String index,
     *                              String type,
     *                              String id)
     * 不同的是，可以指定插入数据的id，如果id不存在则是插入，如果id已存在，则是更新操作。
     *
     * 【时耗测试】
     * 单次更新：64ms 39ms 39ms 35ms 34ms
     *
     * 单次插入: 26ms 34ms 36ms
     *
     * 插入和更新用时差不多。
     *
     * 连续调用该方法，单条数据插入/更新操作可达到5ms级别。
     */
    @Test
    void insertData() {
        /*TimeHelper.start();
        // 插入100条数据，统计用时  507ms 472ms 507ms
        for (int i = 201; i <= 300; i++) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("username", "username-insertData-insert" + i);
            jsonObject1.put("password", "password-insertData-insert" + i);
            elasticsearchUtil.insertDataUseSpecifiedId(jsonObject1, "index-created-by-elasticsearchutil", "user", "insertData-id-" + i);
        }
        TimeHelper.finish();*/

        TimeHelper.start();
        //更新100条数据，统计用时 538ms 503ms
        for (int i = 101; i <= 200; i++) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("username", "username-insertData-update" + i);
            jsonObject1.put("password", "password-insertData-update" + i);
            elasticsearchUtil.insertDataUseSpecifiedId(jsonObject1, "index-created-by-elasticsearchutil", "user", "insertData-id-" + i);
        }
        TimeHelper.finish();

        //如果es索引已经设置了type且方法指定的type不存在:
        //java.lang.IllegalArgumentException: Rejecting mapping update to [index-created-by-elasticsearchutil] as the final mapping would have more than 1 type: [user1, user]
        // 否则会自动设置type

        // 如果索引之前没有完成映射，会自动映射type和字段
        // 需要注意的是自动映射的字段有往往不是我们想要的，请提前手动映射。

        //elasticsearchUtil.insertData(jsonObject1, "index-created-by-elasticsearchutil", "user", "2");

        //尝试插入到不存在的索引
        //结果:成功，会自动创建索引和type
        //elasticsearchUtil.insertData(jsonObject1, "index11", "user", "2");

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
        /*TimeHelper.start();
        //删除一个存在的id
        elasticsearchUtil.deleteDataById("index-created-by-elasticsearchutil","user","ZqeMzHcBNFW3G9O19W9z");
        TimeHelper.finish();*/

        //尝试删除一个不存在的id
        //结果: 不会报错
        elasticsearchUtil.deleteDataById("index-created-by-elasticsearchutil", "user", "xsad2222321fewfdsdsd22dsadsd");

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
        //elasticsearchUtil.deleteDataById("index-created-by-elasticsearchutilnull", null, "7EBEF6C243E44DD88DB7800FEA8E0BCD");
    }

    /**
     * 7.更新。version字段会增加
     *
     * 如果index不存在，则会创建index,但是不会定义type,此时index mappings:
     * "mappings": { },没有type
     */
    @Test
    void updateDataById() {
        // 更新一条存在的数据
        /*TimeHelper.start();
        JSONObject updateJSONObject = new JSONObject();
        updateJSONObject.put("username", "newUsername");
        updateJSONObject.put("password", "newPassword");
        elasticsearchUtil.updateDataById(updateJSONObject, "index-created-by-elasticsearchutil", "user", "baeMzHcBNFW3G9O19W-U");
        TimeHelper.finish();*/

        // 更新一条不存在的数据
        TimeHelper.start();
        JSONObject updateJSONObject2 = new JSONObject();
        updateJSONObject2.put("username", "newUsername");
        updateJSONObject2.put("password", "newPassword");
        elasticsearchUtil.updateDataById(updateJSONObject2, "index-created-by-elasticsearchutil", "user", "baeMzHcBNFW3G9O19W-Uasdasd21212waddad");
        TimeHelper.finish();


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
     *
     * 【返回数据举例】
     * {password=password-insertDataUseRandomId-insert416, username=username-insertDataUseRandomId-insert416}
     * 说明：_index,_type,_id字段不能返回，就算指定fields也不行。
     */
    @Test
    void searchDataById() {
        Map<String, Object> stringObjectMap = elasticsearchUtil.searchDataById("index-created-by-elasticsearchutil", "user", "bqeMzHcBNFW3G9O19W-Z", "_index,_type,_id,username");
        TestHelper.println("searchDataById(\"index-created-by-elasticsearchutil\", \"user\", \"bqeMzHcBNFW3G9O19W-Z\", \"username\")", stringObjectMap);
    }

    /**
     * 9.请求体复杂查询
     *
     * 高亮字段返回数据：
     * elasticsearchUtil.searchDataPage("index-created-by-elasticsearchutil", "user", 1, 5, boolQueryBuilder, "username,password", "", ""): [{id=gqeMzHcBNFW3G9O19W_z, username=username-insertDataUseRandomId-insert436}, {id=hKeMzHcBNFW3G9O19W_7, username=username-insertDataUseRandomId-insert438}, {id=jqeMzHcBNFW3G9O19m8m, username=username-insertDataUseRandomId-insert448}, {id=k6eMzHcBNFW3G9O19m83, username=username-insertDataUseRandomId-insert453}, {id=lKeMzHcBNFW3G9O19m87, username=username-insertDataUseRandomId-insert454}]
     *
     * 【高亮】
     * 将高亮字段用特定的前后缀包围，默认前后缀为 <em> </em>
     *
     * 【使用注意点】
     * 高亮字段必须作为查询的条件，否则会空指针错误。
     */
    @Test
    void searchDataAsPage() {
        // 模糊查询
        /*WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("username", "*name*");
        WildcardQueryBuilder wildcardQueryBuilder1 = QueryBuilders.wildcardQuery("password", "*word*");*/
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("username", "*");
        WildcardQueryBuilder wildcardQueryBuilder1 = QueryBuilders.wildcardQuery("password", "*");

        // 布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 全量查询
        //MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();

        // 必须
        boolQueryBuilder.must(wildcardQueryBuilder);
        boolQueryBuilder.must(wildcardQueryBuilder1);

        EsPage users = elasticsearchUtil.searchDataAsPage("index-created-by-elasticsearchutil", "user,user1,cast", 2, 5, boolQueryBuilder, "username,password", "", "password,username");

        // id字段一定返回的字段
        //  {password=password-addData, id=5FDB173EC33E476BA07480A6F2DF3C33, username=username-addData}]
        TestHelper.println("elasticsearchUtil.searchDataPage(\"index-created-by-elasticsearchutil\", \"user\", 1, 5, boolQueryBuilder, \"username,password\", \"\", \"\")", users);
    }

    @Test
    void insertDataUseRandomId() {
        TimeHelper.start();
        // 插入100条数据，统计用时  507ms 472ms 507ms
        for (int i = 401; i <= 500; i++) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("username", "username-insertDataUseRandomId-insert" + i);
            jsonObject1.put("password", "password-insertDataUseRandomId-insert" + i);
            elasticsearchUtil.insertDataUseRandomId(jsonObject1, "index-created-by-elasticsearchutil", "user");
        }
        TimeHelper.finish();
    }

    @Test
    void searchDataAsList() {
        // 模糊查询
        /*WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("username", "*name*");
        WildcardQueryBuilder wildcardQueryBuilder1 = QueryBuilders.wildcardQuery("password", "*word*");*/
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("username", "*");
        WildcardQueryBuilder wildcardQueryBuilder1 = QueryBuilders.wildcardQuery("password", "*");

        // 布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 全量查询
        //MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();

        // 必须
        boolQueryBuilder.must(wildcardQueryBuilder);
        boolQueryBuilder.must(wildcardQueryBuilder1);

        List<Map<String, Object>> results = elasticsearchUtil.searchDataAsList("index-created-by-elasticsearchutil", "user,user1,cast", 2, 5, boolQueryBuilder, "username,password", "", " password , username ");

        // id字段一定返回的字段
        //  {password=password-addData, id=5FDB173EC33E476BA07480A6F2DF3C33, username=username-addData}]
        TestHelper.println("elasticsearchUtil.searchDataPage(\"index-created-by-elasticsearchutil\", \"user\", 1, 5, boolQueryBuilder, \"username,password\", \"\", \"\")", results);
    }
}