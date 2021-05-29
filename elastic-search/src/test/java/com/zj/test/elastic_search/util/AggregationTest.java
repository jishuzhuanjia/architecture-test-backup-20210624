package com.zj.test.elastic_search.util;

import com.zj.test.elastic_search.EsApplication;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.support.ValuesSource;
import org.elasticsearch.search.aggregations.support.ValuesSourceAggregationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *      聚合查询测试
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/5/27 16:02
 * @is-finished: false
 *
 */
@SpringBootTest(classes = EsApplication.class)
@RunWith(SpringRunner.class)
public class AggregationTest {

    @Autowired ElasticsearchUtil elasticsearchUtil;

    /**
     * <p>
     *     1.测试: es字段区间聚合测试
     * </p>
     *
     * 【测试出入参记录】
     * 1.只有一个范围聚合
     *
     * --入参
     * {
     *     "aggregations": {
     *         "age_aggregation": {
     *             "range": {
     *                 "field": "age",
     *                 "missing": 1,
     *                 "ranges": [{
     *                         "from": 1.0,
     *                         "to": 18.0
     *                     }, {
     *                         "from": 18.0,
     *                         "to": 40.0
     *                     }, {
     *                         "from": 40.0,
     *                         "to": 60.0
     *                     }
     *                 ],
     *                 "keyed": false
     *             }
     *         }
     *     }
     * }
     *
     * -- 出参
     * {
     *     "took": 2,
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
     *         "total": 87,
     *         "max_score": 1.0,
     *         "hits": [{
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "VJjr2ngBAg1PKR2UKZuD",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "name": "周二",
     *                     "teachers_nested": {
     *                         "name": "杨久平",
     *                         "subject": "数学"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "t--c2HgB5xzHVj-T8tsj",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "teachers_default": [{
     *                             "name": "mayun",
     *                             "subject": "吹牛"
     *                         }, {
     *                             "name": "马化腾",
     *                             "subject": "资本家"
     *                         }
     *                     ]
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "YJgG23gBAg1PKR2U3Zt1",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "name": "周xasds",
     *                     "teachers_nested": {
     *                         "name": "杨久平",
     *                         "subject": "d"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "VZjr2ngBAg1PKR2UmJsU",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "name": "周三",
     *                     "teachers_nested": {
     *                         "name": "邓宁",
     *                         "subject": "数学"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "Wpj42ngBAg1PKR2UXptd",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "name": "xxxxxxxx",
     *                     "teachers_nested": {
     *                         "name": "e",
     *                         "subject": "数学"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "w--j2HgB5xzHVj-TK9uu",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "teachers_default": [{
     *                             "name": "ma hua teng",
     *                             "subject": "小学生收割艺术"
     *                         }
     *                     ]
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "qVI8tXgBZ2Ot5CV1VZpn",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "username": "刘强东1",
     *                     "password": "123456",
     *                     "hobbies": "make money computer games"
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "vlJntXgBZ2Ot5CV1-Zo7",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "username": "movie大写",
     *                     "password": "123456",
     *                     "hobbies": "MOVIE"
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "v1JntXgBZ2Ot5CV1_JqG",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "username": "movie大写",
     *                     "password": "123456",
     *                     "hobbies": "MOVIE"
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "tlJQtXgBZ2Ot5CV115rO",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "username": "",
     *                     "password": "123456",
     *                     "hobbies": "movie"
     *                 }
     *             }
     *         ]
     *     },
     *     "aggregations": {
     *         "age_aggregation": {
     *             "buckets": [{
     *                     "key": "1.0-18.0",
     *                     "from": 1.0,
     *                     "to": 18.0,
     *                     "doc_count": 82
     *                 }, {
     *                     "key": "18.0-40.0",
     *                     "from": 18.0,
     *                     "to": 40.0,
     *                     "doc_count": 5
     *                 }, {
     *                     "key": "40.0-60.0",
     *                     "from": 40.0,
     *                     "to": 60.0,
     *                     "doc_count": 0
     *                 }
     *             ]
     *         }
     *     }
     * }
     *
     * --测试2：范围聚合 + 枚举聚合
     *
     * --入参
     * {
     *     "aggregations": {
     *         "age_aggregation": {
     *             "range": {
     *                 "field": "age",
     *                 "missing": 1,
     *                 "ranges": [{
     *                         "from": 1.0,
     *                         "to": 18.0
     *                     }, {
     *                         "from": 18.0,
     *                         "to": 40.0
     *                     }, {
     *                         "from": 40.0,
     *                         "to": 60.0
     *                     }
     *                 ], {
     *                     "aggregations": {
     *                         "age_aggregation": {
     *                             "range": {
     *                                 "field": "age",
     *                                 "missing": 1,
     *                                 "ranges": [{
     *                                         "from": 1.0,
     *                                         "to": 18.0
     *                                     }, {
     *                                         "from": 18.0,
     *                                         "to": 40.0
     *                                     }, {
     *                                         "from": 40.0,
     *                                         "to": 60.0
     *                                     }
     *                                 ],
     *                                 "keyed": false
     *                             }
     *                         },
     *                         "password_aggregation": {
     *                             "terms": {
     *                                 "field": "password",
     *                                 "size": 10,
     *                                 "min_doc_count": 1,
     *                                 "shard_min_doc_count": 0,
     *                                 "show_term_doc_count_error": false,
     *                                 "order": [{
     *                                         "_count": "desc"
     *                                     }, {
     *                                         "_key": "asc"
     *                                     }
     *                                 ]
     *                             }
     *                         }
     *                     }
     *                 }
     *                 "keyed": false
     *             }
     *         },
     *         "hobbies_aggregation": {
     *             "terms": {
     *                 "field": "hobbies",
     *                 "size": 10,
     *                 "min_doc_count": 1,
     *                 "shard_min_doc_count": 0,
     *                 "show_term_doc_count_error": false,
     *                 "order": [{
     *                         "_count": "desc"
     *                     }, {
     *                         "_key": "asc"
     *                     }
     *                 ]
     *             }
     *         }
     *     }
     * }
     *
     * --出参
     * {
     *     "took": 25,
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
     *         "total": 87,
     *         "max_score": 1.0,
     *         "hits": [{
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "VJjr2ngBAg1PKR2UKZuD",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "name": "周二",
     *                     "teachers_nested": {
     *                         "name": "杨久平",
     *                         "subject": "数学"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "t--c2HgB5xzHVj-T8tsj",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "teachers_default": [{
     *                             "name": "mayun",
     *                             "subject": "吹牛"
     *                         }, {
     *                             "name": "马化腾",
     *                             "subject": "资本家"
     *                         }
     *                     ]
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "YJgG23gBAg1PKR2U3Zt1",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "name": "周xasds",
     *                     "teachers_nested": {
     *                         "name": "杨久平",
     *                         "subject": "d"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "VZjr2ngBAg1PKR2UmJsU",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "name": "周三",
     *                     "teachers_nested": {
     *                         "name": "邓宁",
     *                         "subject": "数学"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "Wpj42ngBAg1PKR2UXptd",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "name": "xxxxxxxx",
     *                     "teachers_nested": {
     *                         "name": "e",
     *                         "subject": "数学"
     *                     }
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "w--j2HgB5xzHVj-TK9uu",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "teachers_default": [{
     *                             "name": "ma hua teng",
     *                             "subject": "小学生收割艺术"
     *                         }
     *                     ]
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "qVI8tXgBZ2Ot5CV1VZpn",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "username": "刘强东1",
     *                     "password": "123456",
     *                     "hobbies": "make money computer games"
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "vlJntXgBZ2Ot5CV1-Zo7",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "username": "movie大写",
     *                     "password": "123456",
     *                     "hobbies": "MOVIE"
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "v1JntXgBZ2Ot5CV1_JqG",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "username": "movie大写",
     *                     "password": "123456",
     *                     "hobbies": "MOVIE"
     *                 }
     *             }, {
     *                 "_index": "structured-query-test",
     *                 "_type": "type",
     *                 "_id": "tlJQtXgBZ2Ot5CV115rO",
     *                 "_score": 1.0,
     *                 "_source": {
     *                     "username": "",
     *                     "password": "123456",
     *                     "hobbies": "movie"
     *                 }
     *             }
     *         ]
     *     },
     *     "aggregations": {
     *         "password_aggregation": {
     *             "doc_count_error_upper_bound": 0,
     *             "sum_other_doc_count": 0,
     *             "buckets": [{
     *                     "key": "123456",
     *                     "doc_count": 36
     *                 }, {
     *                     "key": "mayun123",
     *                     "doc_count": 1
     *                 }
     *             ]
     *         },
     *         "age_aggregation": {
     *             "buckets": [{
     *                     "key": "1.0-18.0",
     *                     "from": 1.0,
     *                     "to": 18.0,
     *                     "doc_count": 82
     *                 }, {
     *                     "key": "18.0-40.0",
     *                     "from": 18.0,
     *                     "to": 40.0,
     *                     "doc_count": 5
     *                 }, {
     *                     "key": "40.0-60.0",
     *                     "from": 40.0,
     *                     "to": 60.0,
     *                     "doc_count": 0
     *                 }
     *             ]
     *         }
     *     }
     * }
     *
     * 【结论】
     *
     * 【注意点】
     * Range aggregation是对字段[value1,value2)之间的数据进行统计，
     * 如果es文档对应字段missing，则会出现聚合总数小于hits total的情况，
     * 对于missing的字段，可以通过设置missing来指定默认值。
     *
     */
    @Test
    public void test(){

    }
    @Test
    public void rangeAggregationAndTermsAggregation(){
        TransportClient transportClient = ElasticsearchUtil.getClient();

        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch("structured-query-test");
        searchRequestBuilder.setTypes("type");

        // 1.聚合查询,字段范围聚合
        // field() - 必须指定字段，否则：Failed to execute phase [query], all shards failed; shardFailures {[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][0]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][1]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][2]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][3]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][4]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }
        ValuesSourceAggregationBuilder builder;
        builder = AggregationBuilders.range("age_aggregation").field("age");

        // RangeAggregationBuilder extends ValuesSourceAggregationBuilder
        RangeAggregationBuilder rangeAggregationBuilder = (RangeAggregationBuilder) builder;
        // 值区间，左闭右开
        // 相当于查询age在[1,18)的数据
        rangeAggregationBuilder.addRange(1,18);
        rangeAggregationBuilder.addRange(18,40);
        rangeAggregationBuilder.addRange(40,60);
        // 对于missing字段，设置其默认值，使得聚合统计的数据总数等于查询hits total数。
        rangeAggregationBuilder.missing(1);
        searchRequestBuilder.addAggregation(builder);


        // 2.枚举聚合：会自动统计不同值和对应的数据数
        //

        // 必须指定字段，否则：Failed to execute phase [query], all shards failed; shardFailures {[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][0]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][1]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][2]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][3]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][4]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalStateException[value source config is invalid; must have either a field context or a script or marked as unwrapped]; }

        //枚举聚合字段不能是text,否则报错：Failed to execute phase [query], all shards failed; shardFailures {[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][0]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalArgumentException[Fielddata is disabled on text fields by default. Set fielddata=true on [hobbies] in order to load fielddata in memory by uninverting the inverted index. Note that this can however use significant memory. Alternatively use a keyword field instead.]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][1]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalArgumentException[Fielddata is disabled on text fields by default. Set fielddata=true on [hobbies] in order to load fielddata in memory by uninverting the inverted index. Note that this can however use significant memory. Alternatively use a keyword field instead.]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][2]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalArgumentException[Fielddata is disabled on text fields by default. Set fielddata=true on [hobbies] in order to load fielddata in memory by uninverting the inverted index. Note that this can however use significant memory. Alternatively use a keyword field instead.]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][3]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalArgumentException[Fielddata is disabled on text fields by default. Set fielddata=true on [hobbies] in order to load fielddata in memory by uninverting the inverted index. Note that this can however use significant memory. Alternatively use a keyword field instead.]; }{[qA7bYj0jTza5vCT6pjlU9g][structured-query-test][4]: RemoteTransportException[[qA7bYj0][127.0.0.1:9300][indices:data/read/search[phase/query]]]; nested: IllegalArgumentException[Fielddata is disabled on text fields by default. Set fielddata=true on [hobbies] in order to load fielddata in memory by uninverting the inverted index. Note that this can however use significant memory. Alternatively use a keyword field instead.]; }
        // TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("hobbies_aggregation").field("hobbies");

        // password, 类型: keyword
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("password_aggregation").field("password");

        searchRequestBuilder.addAggregation(termsAggregationBuilder);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
    }
}
