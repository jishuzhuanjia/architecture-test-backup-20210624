package com.zj.test.elastic_search.note;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/30 23:01
 * @description: 请求体查询(前面介绍的是简易查询)
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class Note4 {

    /**
     *简易 查询 —query-string search— 对于用命令行进行即席查询（ad-hoc）是非常有用的。然而，为了充分利用查询的强大功能，
     * 你应该使用 请求体 search API， 之所以称之为请求体查询(Full-Body Search)，因为大部分参数是通过 Http 请求体而非查询字符串来传递的。
     *
     * 请求体查询 —下文简称 查询—不仅可以处理自身的查询请求，还允许你对结果进行片段强调（高亮）、对所有或部分结果进行聚合分析，
     * 同时还可以给出 你是不是想找 的建议，这些建议可以引导使用者快速找到他想要的结果。
     *
     *
     * 1.空查询: 在功能上等价于使用match_all
     * curl -X GET "localhost:9200/_search?pretty" -H 'Content-Type: application/json' -d'
     * {}
     * '
     * 空查询: 将查询返回索引中所有
     * 如果省略index,则会返回所有索引中的文档数据
     *
     * 通配符的一个例子：
     * testindex* /type4,type/search 这将返回名字以testindex开头的索引的前提下，返回他们所有的type4和type类型的值(如果存在的话)
     *
     * 【分页】
     * GET /_search
     * {
     *   "from": 30,            // 基于0
     *   "size": 10
     * }
     *
     *
     * 2.查询表达式(Query DSL)
     * 要使用这种查询表达式，只需将查询语句传递给 query 参数：
     *
     * 【match_all】
     * 空查询（empty search） —{}— 在功能上等价于使用 match_all 查询， 正如其名字一样，匹配所有文档：
     * GET /_search
     * {
     *     "query": {
     *         "match_all": {}
     *     }
     * }
     *
     * 如果是针对某个字段，那么它的结构如下：
     * {					//match_all中的内容
     *     QUERY_NAME: {				//相当于type
     *         FIELD_NAME: {
     *             ARGUMENT: VALUE,
     *             ARGUMENT: VALUE,...
     *         }
     *     }
     * }
     *
     * 举个例子，你可以使用 match 查询语句 来查询 tweet 字段中包含 elasticsearch 的 tweet：
     * GET /_search
     * {
     *     "query": {
     *         "match": {
     *             "tweet": "elasticsearch"
     *         }
     *     }
     * }
     *
     * 3.合并查询语句
     * 查询语句(Query clauses) 就像一些简单的组合块，这些组合块可以彼此之间合并组成更复杂的查询。这些语句可以是如下形式：
     *
     * 叶子语句（Leaf clauses） (就像 match 语句) 被用于将查询字符串和一个字段（或者多个字段）对比。
     * 复合(Compound) 语句 主要用于 合并其它查询语句。 比如，一个 bool 语句 允许在你需要的时候组合其它语句，无论是 must 匹配、 must_not 匹配还是 should 匹配，同时它可以包含不评分的过滤器（filters）：
     *
     * {
     *     "bool": {
     *         "must":     { "match": { "tweet": "elasticsearch" }},
     *         "must_not": { "match": { "name":  "mary" }},
     *         "should":   { "match": { "tweet": "full text" }},
     *         "filter":   { "range": { "age" : { "gt" : 30 }} }
     *     }
     * }
     *
     * 例如，以下查询是为了找出信件正文包含 business opportunity 的星标邮件，或者在收件箱正文包含 business opportunity 的非垃圾邮件：
     *
     * {
     *     "bool": {
     *         "must": { "match":   { "email": "business opportunity" }},
     *         "should": [
     *             { "match":       { "starred": true }},
     *             { "bool": {
     *                 "must":      { "match": { "folder": "inbox" }},
     *                 "must_not":  { "match": { "spam": true }}
     *             }}
     *         ],
     *         "minimum_should_match": 1
     *     }
     * }
     *
     * 4.查询与过滤
     * lasticsearch 使用的查询语言（DSL）拥有一套查询组件，这些组件可以以无限组合的方式进行搭配。这套组件可以在以下两种情况下使用：
     * 过滤情况（filtering context）和查询情况（query context）。
     * 一个评分查询计算每一个文档与此查询的 相关程度，同时将这个相关程度分配给表示相关性的字段 _score，并且按照相关性对匹配到的文档进行排序。
     * 这种相关性的概念是非常适合全文搜索的情况，因为全文搜索几乎没有完全 “正确” 的答案。
     *
     * 自 Elasticsearch 问世以来，查询与过滤（queries and filters）就独自成为 Elasticsearch 的组件。但从 Elasticsearch 2.0 开始，
     * 过滤（filters）已经从技术上被排除了，同时所有的查询（queries）拥有变成不评分查询的能力。
     * 然而，为了明确和简单，我们用 "filter" 这个词表示不评分、只过滤情况下的查询。
     * 你可以把 "filter" 、 "filtering query" 和 "non-scoring query" 这几个词视为相同的。
     * 相似的，如果单独地不加任何修饰词地使用 "query" 这个词，我们指的是 "scoring query" 。
     *
     * 性能差异                              //评分查询、过滤查询
     * 过滤查询（Filtering queries）只是简单的检查包含或者排除，这就使得计算起来非常快。
     * 考虑到至少有一个过滤查询（filtering query）的结果是 “稀少的”（很少匹配的文档），
     * 并且经常使用不评分查询（non-scoring queries），结果会被缓存到内存中以便快速读取，所以有各种各样的手段来优化查询结果。
     *
     * 相反，评分查询（scoring queries）不仅仅要找出匹配的文档，还要计算每个匹配文档的相关性，
     * 计算相关性使得它们比不评分查询费力的多。同时，查询结果并不缓存。
     * 多亏倒排索引（inverted index），一个简单的评分查询在匹配少量文档时可能与一个涵盖百万文档的filter表现的一样好，
     * 甚至会更好。但是在一般情况下，一个filter 会比一个评分的query性能更优异，并且每次都表现的很稳定。
     * 过滤（filtering）的目标是减少那些需要通过评分查询（scoring queries）进行检查的文档。
     *
     * 如何选择查询与过滤
     * 通常的规则是，使用查询（query）语句来进行 全文 搜索或者其它任何需要影响 相关性得分 的搜索。除此以外的情况都使用过滤（filters)。
     *
     * 5.最重要的查询
     * 【match_all】 查询简单的匹配所有文档。在没有指定查询方式时，它是默认的查询：
     * { "match_all": {}}
     * 它经常与 filter 结合使用—​例如，检索收件箱里的所有邮件。所有邮件被认为具有相同的相关性，所以都将获得分值为 1 的中性 _score。
     *
     * 【match 查询】
     * 无论你在任何字段上进行的是全文搜索还是精确查询，match 查询是你可用的标准查询。
     *
     * 如果你在一个全文字段上使用 match 查询，在执行查询前，它将用正确的分析器去分析查询字符串：
     * { "match": { "tweet": "About Search" }}
     *
     * 如果在一个精确值的字段上使用它，例如数字、日期、布尔或者一个 not_analyzed 字符串字段，那么它将会精确匹配给定的值：
     * { "match": { "age":    26           }}
     * { "match": { "date":   "2014-09-01" }}
     * { "match": { "public": true         }}
     * { "match": { "tag":    "full_text"  }}
     *
     *对于精确值的查询，你可能需要使用 filter 语句来取代 query，因为 filter 将会被缓存。接下来，我们将看到一些关于 filter 的例子。
     *
     * 不像我们在 轻量 搜索 章节介绍的字符串查询（query-string search）， match 查询不使用类似 +user_id:2 +tweet:search 的查询语法。
     * 它只是去查找给定的单词。这就意味着将查询字段暴露给你的用户是安全的；你需要控制那些允许被查询字段，不易于抛出语法异常。
     *
     * 【multi_match 查询】
     * multi_match 查询可以在多个字段上执行相同的 match 查询：
     *
     * {
     *     "multi_match": {
     *         "query":    "full text search",
     *         "fields":   [ "title", "body" ]
     *     }
     * }
     *
     * range 查询
     * range 查询找出那些落在指定区间内的数字或者时间：
     *
     * {
     *     "range": {
     *         "age": {
     *             "gte":  20,
     *             "lt":   30
     *         }
     *     }
     * }
     * 拷贝为 cURL
     * 在 Sense 中查看
     *
     * 被允许的操作符如下：
     * gt       大于
     * gte      大于等于
     * lt       小于
     * lte      小于等于
     *
     * 【term 查询】
     * term 查询被用于精确值匹配，这些精确值可能是数字、时间、布尔或者那些 not_analyzed 的字符串：
     *
     * { "term": { "age":    26           }}
     * { "term": { "date":   "2014-09-01" }}
     * { "term": { "public": true         }}
     * { "term": { "tag":    "full_text"  }}
     * 拷贝为 cURL
     * 在 Sense 中查看
     *
     * term 查询对于输入的文本不 分析 ，所以它将给定的值进行精确查询。
     *
     * 【terms 查询】
     * terms 查询和 term 查询一样，但它允许你指定多值进行匹配。如果这个字段包含了指定值中的任何一个值，那么这个文档满足条件：
     *
     * { "terms": { "tag": [ "search", "full_text", "nosql" ] }}
     * 拷贝为 cURL
     * 在 Sense 中查看
     *
     * 和 term 查询一样，terms 查询对于输入的文本不分析。它查询那些精确匹配的值（包括在大小写、重音、空格等方面的差异）。
     *
     * 【exists 查询和 missing 查询】
     * exists 查询和 missing 查询被用于查找那些指定字段中有值 (exists) 或无值 (missing) 的文档。
     * 这与SQL中的 IS_NULL (missing) 和 NOT IS_NULL (exists) 在本质上具有共性：
     *
     * {
     *     "exists":   {
     *         "field":    "title"
     *     }
     * }
     * 拷贝为 cURL
     * 在 Sense 中查看
     *
     * 这些查询经常用于某个字段有值的情况和某个字段缺值的情况。
     *
     * 5.组合多查询
     * 现实的查询需求从来都没有那么简单；它们需要在多个字段上查询多种多样的文本，并且根据一系列的标准来过滤。为了构建类似的高级查询，你需要一种能够将多查询组合成单一查询的查询方法。
     *
     * 你可以用 bool 查询来实现你的需求。这种查询将多查询组合在一起，成为用户自己想要的布尔查询。它接收以下参数：
     *
     * must
     * 文档 必须 匹配这些条件才能被包含进来。
     * must_not
     * 文档 必须不 匹配这些条件才能被包含进来。
     * should
     * 如果满足这些语句中的任意语句，将增加 _score ，否则，无任何影响。它们主要用于修正每个文档的相关性得分。
     * filter
     * 必须 匹配，但它以不评分、过滤模式来进行。这些语句对评分没有贡献，只是根据过滤标准来排除或包含文档。
     *
     * 【增加带过滤器（filtering）的查询】
     * 如果我们不想因为文档的时间而影响得分，可以用 filter 语句来重写前面的例子：
     *
     * {
     *     "bool": {
     *         "must":     { "match": { "title": "how to make millions" }},
     *         "must_not": { "match": { "tag":   "spam" }},
     *         "should": [
     *             { "match": { "tag": "starred" }}
     *         ],
     *         "filter": {
     *           "range": { "date": { "gte": "2014-01-01" }}
     *         }
     *     }
     * }
     *
     * 通过将 range 查询移到 filter 语句中，我们将它转成不评分的查询，将不再影响文档的相关性排名。由于它现在是一个不评分的查询，可以使用各种对 filter 查询有效的优化手段来提升性能。
     *
     * 所有查询都可以借鉴这种方式。将查询移到 bool 查询的 filter 语句中，这样它就自动的转成一个不评分的 filter 了。
     *
     * 如果你需要通过多个不同的标准来过滤你的文档，bool 查询本身也可以被用做不评分的查询。简单地将它放置到 filter 语句中并在内部构建布尔逻辑：
     *
     * {
     *     "bool": {
     *         "must":     { "match": { "title": "how to make millions" }},
     *         "must_not": { "match": { "tag":   "spam" }},
     *         "should": [
     *             { "match": { "tag": "starred" }}
     *         ],
     *         "filter": {
     *           "bool": {
     *               "must": [
     *                   { "range": { "date": { "gte": "2014-01-01" }}},
     *                   { "range": { "price": { "lte": 29.99 }}}
     *               ],
     *               "must_not": [
     *                   { "term": { "category": "ebooks" }}
     *               ]
     *           }
     *         }
     *     }
     * }
     * 拷贝为 cURL
     * 在 Sense 中查看
     *
     *
     * 将 bool 查询包裹在 filter 语句中，我们可以在过滤标准中增加布尔逻辑
     *
     * 通过混合布尔查询，我们可以在我们的查询请求中灵活地编写 scoring 和 filtering 查询逻辑。
     *
     * 【constant_score 查询】
     * 尽管没有 bool 查询使用这么频繁，constant_score 查询也是你工具箱里有用的查询工具。它将一个不变的常量评分应用于所有匹配的文档。它被经常用于你只需要执行一个 filter 而没有其它查询（例如，评分查询）的情况下。
     *
     * 可以使用它来取代只有 filter 语句的 bool 查询。在性能上是完全相同的，但对于提高查询简洁性和清晰度有很大帮助。
     * {
     *     "constant_score":   {
     *         "filter": {
     *             "term": { "category": "ebooks" }
     *         }
     *     }
     * }
     * term 查询被放置在 constant_score 中，转成不评分的 filter。这种方式可以用来取代只有 filter 语句的 bool 查询。
     *
     * 6。验证查询
     * 查询可以变得非常的复杂，尤其和不同的分析器与不同的字段映射结合时，理解起来就有点困难了。不过 validate-query API 可以用来验证查询是否合法。
     *
     * GET /gb/tweet/_validate/query
     * {
     *    "query": {
     *       "tweet" : {
     *          "match" : "really powerful"
     *       }
     *    }
     * }
     * 以上 validate 请求的应答告诉我们这个查询是不合法的：
     * {
     *   "valid" :         false,
     *   "_shards" : {
     *     "total" :       1,
     *     "successful" :  1,
     *     "failed" :      0
     *   }
     * }
     *
     * 【理解错误信息】
     * 为了找出 查询不合法的原因，可以将 explain 参数 加到查询字符串中：
     *
     * GET /gb/tweet/_validate/query?explain
     * {
     *    "query": {
     *       "tweet" : {
     *          "match" : "really powerful"
     *       }
     *    }
     * }
     * 拷贝为 cURL
     * 在 Sense 中查看
     *
     *
     * explain 参数可以提供更多关于查询不合法的信息。
     * 很明显，我们将查询类型(match)与字段名称 (tweet)搞混了：
     *
     * {
     *   "valid" :     false,
     *   "_shards" :   { ... },
     *   "explanations" : [ {
     *     "index" :   "gb",
     *     "valid" :   false,
     *     "error" :   "org.elasticsearch.index.query.QueryParsingException:
     *                  [gb] No query registered for [tweet]"
     *   } ]
     * }
     *
     * 理解查询语句
     * 对于合法查询，使用 explain 参数将返回可读的描述，这对准确理解 Elasticsearch 是如何解析你的 query 是非常有用的：
     *
     * GET /_validate/query?explain
     * {
     *    "query": {
     *       "match" : {
     *          "tweet" : "really powerful"
     *       }
     *    }
     * }
     * 拷贝为 cURL
     * 在 Sense 中查看
     *
     * 我们查询的每一个 index 都会返回对应的 explanation ，因为每一个 index 都有自己的映射和分析器：
     *
     * {
     *   "valid" :         true,
     *   "_shards" :       { ... },
     *   "explanations" : [ {
     *     "index" :       "us",                            //索引
     *     "valid" :       true,
     *     "explanation" : "tweet:really tweet:powerful"
     *   }, {
     *     "index" :       "gb",
     *     "valid" :       true,
     *     "explanation" : "tweet:realli tweet:power"
     *   } ]
     * }
     * 从 explanation 中可以看出，匹配 really powerful 的 match 查询被重写为两个针对 tweet 字段的 single-term 查询，
     * 一个single-term查询对应查询字符串分出来的一个term。
     * 当然，对于索引 us ，这两个 term 分别是 really 和 powerful ，而对于索引 gb ，term 则分别是 realli 和 power 。
     * 之所以出现这个情况，是由于我们将索引 gb 中 tweet 字段的分析器修改为 english 分析器。
     */
}
