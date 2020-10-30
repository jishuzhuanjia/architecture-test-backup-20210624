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
     * 1.轻量检索实例: 一个简单的搜索，全量查询: _search
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
     * 注：type也可以省略。
     *
     * 2.轻量搜索：
     * 2.1.高亮搜索：带有条件的搜索
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
     *
     * 2.2.在索引中： 省略q参数的key
     * 如：GET /_search?q=mary
     * 表示返回包含mary的所有文档，注意，这里的mary是精确查找，而不包括那些包含mary的那些文档。
     *
     * 注意：属性名不能用来作为查询值，否则查不到。
     *
     * 一个PU报错
     * Rejecting mapping update to [AAA] as the final mapping would have more than 1 type: [AAA, BBB]：
     * 原因是elastic search在6.x版本调整了， 一个index只能存储一种type。
     *
     * 使用技巧：
     * 如果省略了索引，则在所有的索引中查找。
     *
     * _all字段：
     * 当索引一个文档的时候，Elasticsearch 取出所有字段的值拼接成一个大的字符串，作为 _all 字段进行索引。
     *
     * 2.3.更复杂的查询
     * +name:(mary john) +date:>2014-09-10 +(aggregations geo)
     * 解释:
     * name:(mary john): name属性为mary或john的文档
     *
     *
     * 注意：
     * name(mary john) 多个值可以用','分隔，用空格分隔也可以
     * (aggregations geo)       这是_all字段包含aggregations或geo
     *
     * 轻量查询的缺点：
     * 当查询字符串经过适当的编码后，可读性很差
     * 对于特殊的一些符号，如,空格等，操作不当会返回错误。
     *
     * 3.精确值(精确值查找) VS 全文(全文搜索)
     * 对于精确值来讲，Foo 和 foo 是不同的，2014 和 2014-09-15 也是不同的。
     *
     * 全文：
     * 查询全文数据要微妙的多。我们问的不只是“这个文档匹配查询吗”，而是“该文档匹配查询的程度有多大？”换句话说，该文档与给定查询的相关性如何？
     *
     * 我们很少对全文类型的域做精确匹配。相反，我们希望在文本类型的域中搜索。不仅如此，我们还希望搜索能够理解我们的 意图 ：
     *
     * 搜索 UK ，会返回包含 United Kindom 的文档。
     * 搜索 jump ，会匹配 jumped ， jumps ， jumping ，甚至是 leap 。
     * 搜索 johnny walker 会匹配 Johnnie Walker ， johnnie depp 应该匹配 Johnny Depp 。
     *
     * 即全文类型通常不作精确查找的，非结构化的文本数据。
     *
     * 【倒排索引】
     * 为了促进这类在全文域中的查询，Elasticsearch 首先 分析 文档，之后根据结果创建 倒排索引 。
     *
     *
     * 4.倒排索引
     * Elasticsearch 使用一种称为 倒排索引 的结构，它适用于快速的全文搜索。一个倒排索引由文档中所有不重复词的列表构成，
     * 对于其中每个词，有一个包含它的文档列表。
     *
     * 例如，假设我们有两个文档，每个文档的 content 域包含如下内容：
     *
     * The quick brown fox jumped over the lazy dog
     * Quick brown foxes leap over lazy dogs in summer
     * 为了创建倒排索引，我们首先将每个文档的 content 域拆分成单独的 词（我们称它为 词条 或 tokens ），               // 词条、tokens
     * 创建一个包含所有不重复词条的排序列表，然后列出每个词条出现在哪个文档。结果如下所示：
     * Term      Doc_1  Doc_2
     * -------------------------
     * Quick   |       |  X
     * The     |   X   |
     * brown   |   X   |  X
     * dog     |   X   |
     * dogs    |       |  X
     * fox     |   X   |
     * foxes   |       |  X
     * in      |       |  X
     * jumped  |   X   |
     * lazy    |   X   |  X
     * leap    |       |  X
     * over    |   X   |  X
     * quick   |   X   |
     * summer  |       |  X
     * the     |   X   |
     * ------------------------
     *
     * 现在，如果我们想搜索 quick brown ，我们只需要查找包含每个词条的文档：
     *
     * Term      Doc_1  Doc_2
     * -------------------------
     * brown   |   X   |  X
     * quick   |   X   |
     * ------------------------
     * Total   |   2   |  1
     *
     * 两个文档都匹配，但是第一个文档比第二个匹配度更高。如果我们使用仅计算匹配词条数量的简单 相似性算法 ，
     * 那么，我们可以说，对于我们查询的相关性来讲，第一个文档比第二个文档更佳。
     *
     * 描述全文查找的结果精确度的度量应该是匹配度，而不是精确相等，否则就成了精确查找了。
     *
     * 但是，我们目前的倒排索引有一些问题：
     *
     * Quick 和 quick 以独立的词条出现，然而用户可能认为它们是相同的词。
     * fox 和 foxes 非常相似, 就像 dog 和 dogs ；他们有相同的词根。
     * jumped 和 leap, 尽管没有相同的词根，但他们的意思很相近。他们是同义词。
     * 使用前面的索引搜索 +Quick +fox 不会得到任何匹配文档。（记住，+ 前缀表明这个词必须存在。）只有同时出现 Quick 和 fox 的文档才满足这个查询条件，但是第一个文档包含 quick fox ，第二个文档包含 Quick foxes 。
     *
     * 我们的用户可以合理的期望两个文档与查询匹配。我们可以做的更好。
     *
     * 如果我们将词条规范为标准模式，那么我们可以找到与用户搜索的词条不完全一致，但具有足够相关性的文档。例如：
     *
     * Quick 可以小写化为 quick 。
     * foxes 可以 词干提取 --变为词根的格式-- 为 fox 。类似的， dogs 可以为提取为 dog 。
     * jumped 和 leap 是同义词，可以索引为相同的单词 jump 。
     * 现在索引看上去像这样：
     *
     * Term      Doc_1  Doc_2
     * -------------------------
     * brown   |   X   |  X
     * dog     |   X   |  X
     * fox     |   X   |  X
     * in      |       |  X
     * jump    |   X   |  X
     * lazy    |   X   |  X
     * over    |   X   |  X
     * quick   |   X   |  X
     * summer  |       |  X
     * the     |   X   |  X
     * ------------------------
     *
     * 这还远远不够。我们搜索 +Quick +fox 仍然 会失败，因为在我们的索引中，已经没有 Quick 了。但是，
     * 如果我们对搜索的字符串使用与 content 域相同的标准化规则，会变成查询 +quick +fox ，这样两个文档都会匹配！
     *
     * 这非常重要。你只能搜索在索引中出现的词条，所以索引文本和查询字符串必须标准化为相同的格式。
     *
     * 分词和标准化的过程称为 分析 ， 我们会在下个章节讨论。             // 分析：分词和标准化的过程
     *
     * 5.分析和分析器                 //分析器
     * 分析 包含下面的过程：
     *
     * 首先，将一块文本分成适合于倒排索引的独立的 词条 ，
     * 之后，将这些词条统一化为标准格式以提高它们的“可搜索性”，或者 recall   // 标准化的目的: 提高可搜索性或者recall
     *
     * 分析器: 做上面这件事的东西，它封装了3个功能:
     * 1.字符过滤器
     * 首先，字符串按顺序通过每个 字符过滤器 。他们的任务是在分词前整理字符串。
     * 一个字符过滤器可以用来去掉HTML，或者将 & 转化成 and。
     *
     * 2.分词器
     * 其次，字符串被 分词器 分为单个的词条。一个简单的分词器遇到空格和标点的时候，可能会将文本拆分成词条。
     *
     * 3.Token 过滤器
     * 最后，词条按顺序通过每个 token 过滤器 。这个过程可能会改变词条（例如，小写化 Quick ），
     * 删除词条（例如， 像 a， and， the 等无用词），或者增加词条（例如，像 jump 和 leap 这种同义词）。
     *
     * Elasticsearch提供了开箱即用的字符过滤器、分词器和token 过滤器。这些可以组合起来形成自定义的分析器以用于不同的目的。我们会在 自定义分析器 章节详细讨论。
     *
     * 5.1.内置分析器
     * 对于"Set the shape to semi-transparent by calling set_trans(5)"，分别介绍不同分析器分析后的词条结果
     *
     * 5.1.1.标准分析器(默认)                         // 词条可以包括数值
     *                                              // 分隔符：绝大多数的标点符号，这些分隔符会被删除
     * 它是分析各种语言文本最常用的选择。它根据 Unicode 联盟 定义的 单词边界 划分文本。删除绝大部分标点。最后，将词条小写。它会产生：
     * set, the, shape, to, semi, transparent, by, calling, set_trans, 5
     *
     * 5.1.2.简单分析器                              // 词条不包括数值
     *                                              // 分隔符：非字母，非字母会被删除
     * 简单分析器在任何不是字母的地方分隔文本，将词条小写。它会产生:
     * set, the, shape, to, semi, transparent, by, calling, set, trans
     *
     * 5.1.3.空格分析器                              // 分隔符：空格
     *                                              // 因此此条可以包含数值和字母
     * 空格分析器在空格的地方划分文本。它会产生
     * Set, the, shape, to, semi-transparent, by, calling, set_trans(5)
     *
     * 5.1.4.语言分析器
     * 特定语言分析器可用于 很多语言。它们可以考虑指定语言的特点。例如， 英语 分析器附带了一组英语无用词（常用单词，例如 and 或者 the ，
     * 它们对相关性没有多少影响），它们会被删除。 由于理解英语语法的规则，这个分词器可以提取英语单词的 词干 。
     * 英语 分词器会产生下面的词条：
     *
     * set, shape, semi, transpar, call, set_tran, 5
     * 注意看 transparent、 calling 和 set_trans 已经变为词根格式。
     *
     * 5.2.什么时候使用分析器        // 全文域 和 精确值域
     * 当你查询一个 全文 域时， 会对查询字符串应用相同的分析器，以产生正确的搜索词条列表。
     * 当你查询一个 精确值 域时，不会分析查询字符串，而是搜索你指定的精确值。
     *
     * 因此，现在你就该理解前面的 _all 域了，它就是个 全文 域。
     *
     * 当我们在 _all 域查询 2014-09-15，它首先分析查询字符串，产生匹配 2014， 09， 或 15 中 任意 词条的查询。这也会匹配所有12条推文，因为它们都含有 2014 ：
     *
     * 5.3.测试分析器： 就是查看文本实际是如何被分析的。
     * 【测试】
     * GET /_analyze                    //es-head插件中GET中没有指定json头，因此需要使用POST方法
     * {
     *   "analyzer": "standard",
     *   "text": "Text to analyze"
     * }
     *
     * 注意必须是 ip:port/_analyze，不能添加索引名和type，否则会报错。
     *
     * 【返回结果示例】
     * {
     * "tokens": [                  // tokens(词条)
     * {
     * "token": "text",             // 词条值，可以看到被转换成了小写
     * "start_offset": 0,           // 在原来text中的开始index
     * "end_offset": 4,             //  end index： 注意不包含该位置的字符
     * "type": "<ALPHANUM>",        // 每个分析器的 type 值都不一样，可以忽略它们。它们在Elasticsearch中的唯一作用在于​keep_types token 过滤器​。
     * "position": 0                // 指明词条在原始文本中出现的位置。
     * }
     * ,
     * {
     * "token": "to",
     * "start_offset": 5,
     * "end_offset": 7,
     * "type": "<ALPHANUM>",        //
     * "position": 1
     * }
     * ,
     * {
     * "token": "analyze",
     * "start_offset": 8,
     * "end_offset": 15,
     * "type": "<ALPHANUM>",
     * "position": 2
     * }
     * ]
     * }
     *
     * 5.4.指定分析器
     * 当Elasticsearch在你的文档中检测到一个新的字符串域，它会自动设置其为一个全文 字符串 域，使用 标准 分析器对它进行分析。
     *
     * 你不希望总是这样。可能你想使用一个不同的分析器，适用于你的数据使用的语言。有时候你想要一个字符串域就是一个字符串域—​不使用分析，
     * 直接索引你传入的精确值，例如用户ID或者一个内部的状态域或标签。
     * 要做到这一点，我们必须手动指定这些域的映射。
     */
}