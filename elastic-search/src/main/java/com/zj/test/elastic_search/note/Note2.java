package com.zj.test.elastic_search.note;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/27 23:31
 * @description: 笔记部分2
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class Note2 {
    /**
     * 1.轻量检索实例: 一个简单的搜索，全量查询
     * 语法: 用_search代替id
     * 搜索我们Note1中添加的所有文档
     * C:\Users\Administrator>curl -X GET "localhost:9200/megacorp/employee/_search?pretty"
     * {
     *   "took" : 2,
     *   "timed_out" : false,
     *   "_shards" : {
     *     "total" : 1,
     *     "successful" : 1,
     *     "skipped" : 0,
     *     "failed" : 0
     *   },
     *   "hits" : {
     *     "total" : {
     *       "value" : 2,
     *       "relation" : "eq"
     *     },
     *     "max_score" : 1.0,
     *     "hits" : [                       // hits返回的就是搜索到的数据，只会搜索最新的版本
     *       {
     *         "_index" : "megacorp",
     *         "_type" : "employee",
     *         "_id" : "1",
     *         "_score" : 1.0,
     *         "_source" : {
     *           "first_name" : "John ",
     *           "last_name" : "Smith",
     *           "age" : 25,
     *           "about" : "I love to go rock climbing ",
     *           "interests " : [
     *             "sports ",
     *             "musicxxxx "
     *           ]
     *         }
     *       },
     *       {
     *         "_index" : "megacorp",
     *         "_type" : "employee",
     *         "_id" : "2",
     *         "_score" : 1.0,
     *         "_source" : {
     *           "first_name" : "John ",
     *           "last_name" : "Smith",
     *           "age" : 25,
     *           "about" : "I love to go rock climbing ",
     *           "interests " : [
     *             "sports ",
     *             "musicxxxx "
     *           ]
     *         }
     *       }
     *     ]
     *   }
     * }
     *
     * 2.高亮搜索：带有条件的搜索
     *
     *
     * C:\Users\Administrator>curl -X GET "localhost:9200/megacorp/employee/_search?q=last_name:Smith2&pretty"
     * {
     *   "took" : 23,
     *   "timed_out" : false,
     *   "_shards" : {
     *     "total" : 1,
     *     "successful" : 1,
     *     "skipped" : 0,
     *     "failed" : 0
     *   },
     *   "hits" : {
     *     "total" : {
     *       "value" : 1,
     *       "relation" : "eq"
     *     },
     *     "max_score" : 0.6931471,
     *     "hits" : [
     *       {
     *         "_index" : "megacorp",
     *         "_type" : "employee",
     *         "_id" : "1",
     *         "_score" : 0.6931471,
     *         "_source" : {
     *           "first_name" : "John ",
     *           "last_name" : "Smith2",
     *           "age" : 25,
     *           "about" : "I love to go rock climbing ",
     *           "interests " : [
     *             "sports ",
     *             "musicxxxx "
     *           ]
     *         }
     *       }
     *     ]
     *   }
     * }
     *
     * tip: 参数传递格式q=last_name:Smith2                // 可以看出不需要引号
     * last_name        就是条件属性名
     * :                属性和值的分隔符
     * Smith2           条件属性值
     *
     * 注意：last_name引号是可选的，如果添加引号，只能是双引号，且不能使用转移字符\“\”包围
     * Smith2值类型如果是字符串类型，可以用""或''包围。
     */
}
