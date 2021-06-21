package com.zj.test.elastic_search.test;

import com.zj.test.elastic_search.util.ElasticsearchUtil;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.HasChildQueryBuilder;
import org.elasticsearch.join.query.HasParentQueryBuilder;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *      elasticsearch6.x join类型字段测试
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/18 16:56
 * @is-finished: true 2021-6-18 17:24:53
 *
 */

@SpringBootTest()
@RunWith(SpringRunner.class)
public class JoinTypeTest {

    @Autowired
    ElasticsearchUtil elasticsearchUtil;

    /**
     * 1.has_child查询实现：查询有答案子文档的问题父文档
     *
     * 出参：
     * {
     *     "took": 30,
     *     "timed_out": false,
     *     "_shards": {
     *         "total": 5,
     *         "successful": 5,
     *         "skipped": 0,
     *         "failed": 0
     *     },
     *     "_clusters": {
     *         "total": 0,
     *         "successful": 0,
     *         "skipped": 0
     *     },
     *     "hits": {
     *         "total": 2,
     *         "max_score": 1.0,
     *         "hits": [{
     *                 "_index": "join_type_test",
     *                 "_type": "type",
     *                 "_id": "2",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "text": "this is question B",
     *                     "join_type": "question"
     *                 }
     *             }, {
     *                 "_index": "join_type_test",
     *                 "_type": "type",
     *                 "_id": "1",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "text": "this is question A",
     *                     "join_type": "question"
     *                 }
     *             }
     *         ]
     *     }
     * }
     *
     * 【使用注意点】
     * 索引需要指定join type字段，否则会报错：
     * Caused by: [structured-query-test/StsvYN85QESAaNCdecP8XQ] QueryShardException[[has_child] no join field has been configured]
     *
     * 是通过JoinQueryBuilders构建的join查询，而不是QueryBuilders。
     *
     */
    @Test
    public void hasChildQuery() {
        TransportClient transportClient = ElasticsearchUtil.getClient();
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch("join_type_test");
        //java.lang.IllegalArgumentException: [has_child] requires 'query' field
        //HasChildQueryBuilder answer = JoinQueryBuilders.hasChildQuery("answer", null, null);

        // java.lang.IllegalArgumentException: [has_child] requires 'score_mode' field
        //HasChildQueryBuilder answer = JoinQueryBuilders.hasChildQuery("answer", QueryBuilders.matchAllQuery(), null);

        // Caused by: [structured-query-test/StsvYN85QESAaNCdecP8XQ] QueryShardException[[has_child] no join field has been configured]
        // 不要紧张，这是因为所要查询的索引没有配置join type字段导致的，在添加join type字段后，报错就解决了。
        HasChildQueryBuilder hasChildQueryBuilder = JoinQueryBuilders.hasChildQuery("answer", QueryBuilders.matchAllQuery(), ScoreMode.None);

        searchRequestBuilder.setQuery(hasChildQueryBuilder);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

    }

    /**
     * <p>
     *     2.has_parent查询实现：查询有问题父文档的子文档
     * </p>
     *
     * 【出入参记录】
     * {
     *     "took": 3,
     *     "timed_out": false,
     *     "_shards": {
     *         "total": 5,
     *         "successful": 5,
     *         "skipped": 0,
     *         "failed": 0
     *     },
     *     "_clusters": {
     *         "total": 0,
     *         "successful": 0,
     *         "skipped": 0
     *     },
     *     "hits": {
     *         "total": 5,
     *         "max_score": 1.0,
     *         "hits": [{
     *                 "_index": "join_type_test",
     *                 "_type": "type",
     *                 "_id": "5",
     *                 "_score": 1.0,
     *                 "_routing": "2",
     *                 "_source": {
     *                     "text": "this is answer of question B",
     *                     "join_type": {
     *                         "name": "answer",
     *                         "parent": "2"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "join_type_test",
     *                 "_type": "type",
     *                 "_id": "6",
     *                 "_score": 1.0,
     *                 "_routing": "2",
     *                 "_source": {
     *                     "text": "this is another answer of question B",
     *                     "join_type": {
     *                         "name": "answer",
     *                         "parent": "2"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "join_type_test",
     *                 "_type": "type",
     *                 "_id": "3",
     *                 "_score": 1.0,
     *                 "_routing": "1",
     *                 "_source": {
     *                     "text": "this is answer of question A",
     *                     "join_type": {
     *                         "name": "answer",
     *                         "parent": "1"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "join_type_test",
     *                 "_type": "type",
     *                 "_id": "4",
     *                 "_score": 1.0,
     *                 "_routing": "1",
     *                 "_source": {
     *                     "text": "this is another answer of question A",
     *                     "join_type": {
     *                         "name": "answer",
     *                         "parent": "1"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "join_type_test",
     *                 "_type": "type",
     *                 "_id": "7",
     *                 "_score": 1.0,
     *                 "_routing": "1",
     *                 "_source": {
     *                     "text": "this is answer of question A",
     *                     "join_type": {
     *                         "name": "answer",
     *                         "parent": "1"
     *                     }
     *                 }
     *             }
     *         ]
     *     }
     * }
     *
     * 【结论】
     *
     * 【注意点】
     * 索引需要指定join type字段，否则会报错：
     * Caused by: [structured-query-test/StsvYN85QESAaNCdecP8XQ] QueryShardException[[has_child] no join field has been configured]
     *
     */
    @Test
    public void hasParentQuery() {
        TransportClient transportClient = ElasticsearchUtil.getClient();
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch("join_type_test");

        HasParentQueryBuilder hasParentQueryBuilder = JoinQueryBuilders.hasParentQuery("question", QueryBuilders.matchAllQuery(), false);

        searchRequestBuilder.setQuery(hasParentQueryBuilder);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

    }
}
