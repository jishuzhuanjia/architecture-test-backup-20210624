package com.zj.test.elastic_search.note;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/30 21:45
 * @description: 映射
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class Note3 {

    /**
     * 1.映射
     *
     * 为了能够将时间域视为时间，数字域视为数字，字符串域视为全文或精确值字符串， Elasticsearch 需要知道每个域中数据的类型。这个信息包含在映射中。
     *
     * 如 数据输入和输出 中解释的，索引中每个文档都有 类型 。每种类型都有它自己的 映射 ，或者 模式定义 。映射定义了类型中的域，每个域的数据类型，
     * 以及Elasticsearch如何处理这些域。映射也用于配置与类型有关的元数据。     // 类型存在的意义之一
     *
     * 1.1.域类型(核心简单域类型)
     * Elasticsearch支持如下简单域类型：
     * ---------------
     * 字符串类型: string
     * 整型类型: byte,short,integer,long
     * 浮点数类型: float,double
     * 布尔型: boolean
     * 日期: date
     * ---------------
     * 核心复杂域类型
     * 除了我们提到的简单标量数据类型， JSON 还有 null 值，数组，和对象，这些 Elasticsearch 都是支持的。
     *
     * 1.2.ES动态映射
     * 当你索引一个包含新域的文档—​之前未曾出现-- Elasticsearch 会使用 动态映射 ，通过JSON中基本数据类型，尝试猜测域类型，使用如下规则：
     *---------------
     * JSON type                       域 type
     *
     * 布尔型:true 或者 false            boolean
     *
     * 整数: 123                        long
     *
     * 浮点数: 123.45                    double
     *
     * 字符串，有效日期: 2014-09-15         date
     *
     * 字符串: foo bar                    string
     * ---------------
     *
     * 这意味着如果你通过引号( "123" )索引一个数字，它会被映射为 string 类型，而不是 long 。
     * 但是，如果这个域已经映射为 long ，那么 Elasticsearch 会尝试将这个字符串转化为 long ，如果无法转化，则抛出一个异常。
     *
     * 1.3.查看映射: /_mapping
     * 我们可以查看 Elasticsearch 在一个或多个索引中的一个或多个类型的映射
     *
     * 返回结果部分数据结构：
     * "testindex": {
     *      "mappings": {
     *          "properties": {
     *              "age": {
     *                  "type": "long"
     *              },
     *              "password": {
     *                  "type": "text",
     *                  "fields": {
     *                      "keyword": {
     *                      "type": "keyword",
     *                      "ignore_above": 256
     *              }
     *          }
     *      }
     *  }
     * }
     * }
     *
     * http://localhost:9200/_mapping             查看所有索引的映射
     *
     * http://localhost:9200/testindex/_mapping/type1   我测试报错，我的es版本比较新7.9.3
     *
     * 1.4.自定义域映射
     * 尽管在很多情况下基本域数据类型已经够用，但你经常需要为单独域自定义映射，特别是字符串域。自定义映射允许你执行下面的操作：
     *
     * 全文字符串域和精确值字符串域的区别
     * 使用特定语言分析器
     * 优化域以适应部分匹配
     * 指定自定义数据格式
     * 还有更多
     *
     * 域最重要的属性是 type 。对于不是 string 的域，你一般只需要设置 type ：
     * {
     *     "number_of_clicks": {
     *         "type": "integer"
     *     }
     * }
     *
     * //
     * 默认， string 类型域会被认为包含全文。就是说，它们的值在索引前，会通过一个分析器，针对于这个域的查询在搜索前也会经过一个分析器。
     * string 域映射的两个最重要属性是 index 和 analyzer 。
     * ----------
     * index: 属性控制怎样索引字符串。它可以是下面三个值：
     *
     * analyzed(默认值)
     * 首先分析字符串，然后索引它。换句话说，以全文索引这个域。
     * not_analyzed
     *   索引这个域，所以它能够被搜索，但索引的是精确值。不会对它进行分析。
     * no
     * 不索引这个域。这个域不会被搜索到。
     *
     * string 域 index 属性默认是 analyzed 。如果我们想映射这个字段为一个精确值，我们需要设置它为 not_analyzed ：
     *
     * {
     *     "tag": {
     *         "type":     "string",
     *         "index":    "not_analyzed"
     *     }
     * }
     *
     * //
     *
     其他简单类型（例如 long ， double ， date 等）也接受 index 参数，但有意义的值只有 no 和 not_analyzed ， 因为它们永远不会被分析。
     * ----------
     *
     * analyzer
     * 对于 analyzed 字符串域，用 analyzer 属性指定在搜索和索引时使用的分析器。默认， Elasticsearch 使用 standard 分析器，
     * 但你可以指定一个内置的分析器替代它，例如 whitespace 、 simple 和 english：
     * {
     *     "tweet": {
     *         "type":     "string",
     *         "analyzer": "english"
     *     }
     * }
     *
     * 1.5.更新映射
     * 当你首次创建一个索引的时候，可以指定类型的映射。你也可以使用 /_mapping 为新类型（或者为存在的类型更新映射）增加映射。
     *
     * //
     *
     * 尽管你可以 增加 一个存在的映射，你不能 修改 存在的域映射。如果一个域的映射已经存在，那么该域的数据可能已经被索引。
     * 如果你意图修改这个域的映射，索引的数据可能会出错，不能被正常的搜索。
     *
     * 我们可以更新一个映射来添加一个新域，但不能将一个存在的域从 analyzed 改为 not_analyzed 。
     *
     *
     * 1.6.复杂核心域类型
     * 【多值域】
     * 很有可能，我们希望 tag 域包含多个标签。我们可以以数组的形式索引标签：
     *
     * { "tag": [ "search", "nosql" ]}
     * 对于数组，没有特殊的映射需求。任何域都可以包含0、1或者多个值，就像全文域分析得到多个词条。
     *
     * 这暗示 数组中所有的值必须是相同数据类型的 。你不能将日期和字符串混在一起。
     * 如果你通过索引数组来创建新的域，Elasticsearch 会用数组中第一个值的数据类型作为这个域的 类型 。
     *
     * 当你从 Elasticsearch 得到一个文档，每个数组的顺序和你当初索引文档时一样。你得到的 _source 域，包含与你索引的一模一样的 JSON 文档。
     *
     * 但是，数组是以多值域 索引的—可以搜索，但是无序的。 在搜索的时候，你不能指定 “第一个” 或者 “最后一个”。 更确切的说，把数组想象成 装在袋子里的值 。
     *
     * 【空域】
     * 当然，数组可以为空。这相当于存在零值。 事实上，在 Lucene 中是不能存储 null 值的，所以我们认为存在 null 值的域为空域。
     *
     * 下面三种域被认为是空的，它们将不会被索引：
     * "null_value":               null,
     * "empty_array":              [],
     * "array_with_null_value":    [ null ]
     *
     * 【多层级对象】
     * 我们讨论的最后一个 JSON 原生数据类是 对象 -- 在其他语言中称为哈希，哈希 map，字典或者关联数组。
     *
     * 内部对象 经常用于嵌入一个实体或对象到其它对象中。例如，与其在 tweet 文档中包含 user_name 和 user_id 域，我们也可以这样写：
     *
     * {
     *     "tweet":            "Elasticsearch is very flexible",
     *     "user": {
     *         "id":           "@johnsmith",
     *         "gender":       "male",
     *         "age":          26,
     *         "name": {
     *             "full":     "John Smith",
     *             "first":    "John",
     *             "last":     "Smith"
     *         }
     *     }
     * }
     *
     * 【内部对象的映射】
     * Elasticsearch 会动态监测新的对象域并映射它们为 对象 ，在 properties 属性下列出内部域：
     *
     * {
     *   "gb": {
     *     "tweet": {
     *       "properties": {
     *         "tweet":            { "type": "string" },
     *         "user": {
     *           "type":             "object",
     *           "properties": {
     *             "id":           { "type": "string" },
     *             "gender":       { "type": "string" },
     *             "age":          { "type": "long"   },
     *             "name":   {
     *               "type":         "object",
     *               "properties": {
     *                 "full":     { "type": "string" },
     *                 "first":    { "type": "string" },
     *                 "last":     { "type": "string" }
     *               }
     *             }
     *           }
     *         }
     *       }
     *     }
     *   }
     * }
     *
     * 【内部对象是如何索引的】
     * Lucene 不理解内部对象。 Lucene 文档是由一组键值对列表组成的。为了能让 Elasticsearch 有效地索引内部类，它把我们的文档转化成这样：
     *
     * {
     *     "tweet":            [elasticsearch, flexible, very],
     *     "user.id":          [@johnsmith],
     *     "user.gender":      [male],
     *     "user.age":         [26],
     *     "user.name.full":   [john, smith],
     *     "user.name.first":  [john],
     *     "user.name.last":   [smith]
     * }
     *
     * 内部域 可以通过名称引用（例如， first ）。为了区分同名的两个域，我们可以使用全 路径 （例如， user.name.first ） 或 type 名加路径（ tweet.user.name.first ）。
     *
     * 【内部对象数组】
     * 最后，考虑包含内部对象的数组是如何被索引的。 假设我们有个 followers 数组：
     *
     * {
     *     "followers": [
     *         { "age": 35, "name": "Mary White"},
     *         { "age": 26, "name": "Alex Jones"},
     *         { "age": 19, "name": "Lisa Smith"}
     *     ]
     * }
     * 这个文档会像我们之前描述的那样被扁平化处理，结果如下所示：
     *
     * {
     *     "followers.age":    [19, 26, 35],
     *     "followers.name":   [alex, jones, lisa, smith, mary, white]
     * }
     * {age: 35} 和 {name: Mary White} 之间的相关性已经丢失了，因为每个多值域只是一包无序的值，而不是有序数组。这足以让我们问，“有一个26岁的追随者？”
     *
     * 但是我们不能得到一个准确的答案：“是否有一个26岁 名字叫 Alex Jones 的追随者？”
     *
     * 相关内部对象被称为 nested 对象，可以回答上面的查询，我们稍后会在嵌套对象中介绍它。
     */
}
