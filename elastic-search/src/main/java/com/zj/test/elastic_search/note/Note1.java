package com.zj.test.elastic_search.note;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/27 11:11
 * @description: 教程内容笔记: 取其更精华
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月27日 23:50:29
 */
public class Note1 {
    /**
     *1.Apache Lucene
     *
     * Elasticsearch 是一个开源的搜索引擎，建立在一个全文搜索引擎库 Apache Lucene™ 基础之上。
     * Lucene 可以说是当下最先进、高性能、全功能的搜索引擎库—​无论是开源还是私有。
     *
     *2. Lucene存在的缺点: 非常复杂
     * 但是 Lucene 仅仅只是一个库。为了充分发挥其功能，你需要使用 Java 并将 Lucene 直接集成到应用程序中。
     * 更糟糕的是，您可能需要获得信息检索学位才能了解其工作原理。Lucene 非常 复杂。
     *
     *3.Elasticearch的目的：使得Lucene使用更加简单
     * Elasticsearch 也是使用 Java 编写的，它的内部使用 Lucene 做索引与搜索，但是它的目的是使全文检索变得简单，
     * 通过隐藏 Lucene 的复杂性，取而代之的提供一套简单一致的 RESTful API。
     *
     *然而，Elasticsearch 不仅仅是 Lucene，并且也不仅仅只是一个全文搜索引擎。 它可以被下面这样准确的形容：
     * 一个分布式的实时文档存储，每个字段 可以被索引与搜索
     * 一个分布式实时分析搜索引擎
     * 能胜任上百个服务节点的扩展，并支持 PB 级别的结构化或者非结构化数据
     *
     *4.安装并运行Elasticsearch
     * 4.1.你需要先安装一个较新的版本的 Java，最好的选择是，你可以从 www.java.com 获得官方提供的最新版本的 Java。
     * 4.2.之后，你可以从 elastic 的官网 elastic.co/downloads/elasticsearch 获取最新版本的 Elasticsearch。
     *
     * 启动
     * windows下运行/bin/elasticsearch.bat
     *
     * 启动检测
     * curl http://localhost:9200/?pretty
     * 得到类似响应:
     * {
     *   "name" : "OHPG9R1V35BH490",
     *   "cluster_name" : "elasticsearch",
     *   "cluster_uuid" : "ItKaCcl-QDeN5rlYdBKcDQ",
     *   "version" : {
     *     "number" : "7.9.3",
     *     "build_flavor" : "default",
     *     "build_type" : "zip",
     *     "build_hash" : "c4138e51121ef06a6404866cddc601906fe5c868",
     *     "build_date" : "2020-10-16T10:36:16.141335Z",
     *     "build_snapshot" : false,
     *     "lucene_version" : "8.6.2",
     *     "minimum_wire_compatibility_version" : "6.8.0",
     *     "minimum_index_compatibility_version" : "6.0.0-beta1"
     *   },
     *   "tagline" : "You Know, for Search"
     * }
     * 说明启动成功。
     *
     * 5.Java API
     * 如果你正在使用 Java，在代码中你可以使用 Elasticsearch 内置的两个客户端：
     *
     * 节点客户端（Node client）
     * 节点客户端作为一个非数据节点加入到本地集群中。换句话说，它本身不保存任何数据，
     * 但是它知道数据在集群中的哪个节点中，并且可以把请求转发到正确的节点。
     *
     * 传输客户端（Transport client）
     * 轻量级的传输客户端可以将请求发送到远程集群。它本身不加入集群，但是它可以将请求转发到集群中的一个节点上。
     *
     * 两个 Java 客户端都是通过 9300 端口并使用 Elasticsearch 的原生 传输 协议和集群交互。
     * 集群中的节点通过端口 9300 彼此通信。如果这个端口没有打开，节点将无法形成一个集群。
     *
     * Tip
     * Java 客户端作为节点必须和 Elasticsearch 有相同的 主要 版本；否则，它们之间将无法互相理解。
     *
     * 6.与elasticsearch交互
     * ?pretty: 将格式化地输出 JSON 返回值，使其更容易阅读
     *
     * 计算集群中文档的数量:
     * C:\Users\Administrator>curl -get http://localhost:9200/_count?pretty
     * {
     *   "count" : 0,
     *   "_shards" : {
     *     "total" : 0,
     *     "successful" : 0,
     *     "skipped" : 0,
     *     "failed" : 0
     *   }
     * }
     *
     * curl命令参数
     * -i       显示消息行
     *
     * 7.elasticsearch面向文档
     *也许有一天你想把这些对象存储在数据库中。使用关系型数据库的行和列存储，
     * 这相当于是把一个表现力丰富的对象塞到一个非常大的电子表格中：
     * 为了适应表结构，你必须设法将这个对象扁平化—​通常一个字段对应一列—​而且每次查询时又需要将其重新构造为对象。
     *
     * Elasticsearch 是 面向文档 的，意味着它存储整个对象或 文档。Elasticsearch 不仅存储文档，而且 索引 每个文档的内容，
     * 使之可以被检索。在 Elasticsearch 中，我们对文档进行索引、检索、排序和过滤—​而不是对行列数据。这是一种完全不同的思考数据的方式，
     * 也是 Elasticsearch 能支持复杂全文检索的原因。
     *
     * 8.Elasticsearch 使用 JavaScript Object Notation（或者 JSON）作为文档的序列化格式。
     * JSON 序列化为大多数编程语言所支持，并且已经成为 NoSQL 领域的标准格式。 它简单、简洁、易于阅读。
     *
     * 9.概念
     * 存储数据到 Elasticsearch 的行为叫做 索引 ，但在索引一个文档之前，需要确定将文档存储在哪里。
     * // 索引名词相当于INSERT
     *
     * 一个 Elasticsearch 集群可以 包含多个 索引 ，              //索引就相当于库
     * 相应的每个索引可以包含多个 类型 。                         //类型相当于表
     * 这些不同的类型存储着多个 文档 ，每个文档又有 多个 属性 。      //文档相当于记录
     *                                                     //文档由属性组成
     *
     * 倒排索引
     * 关系型数据库通过增加一个 索引 比如一个 B树（B-tree）索引 到指定的列上，以便提升数据检索速度。
     * Elasticsearch 和 Lucene 使用了一个叫做 倒排索引 的结构来达到相同的目的。
     * 默认的，一个文档中的每一个属性都是 被索引 的（有一个倒排索引）和可搜索的。一个没有倒排索引的属性是不能被搜索到的。
     * 我们将在 倒排索引 讨论倒排索引的更多细节。
     *
     * 10.增
     * 索引实例: 索引员工文档          //注意这里的员工指的是类型的一种，相当于表
     * curl -X PUT "localhost:9200/megacorp/employee/1?pretty" -H "Content-Type: application/json" -d"{\"first_name\" :  \"John \",\"last_name\" :   \"Smith\",\"age\" :        25,\"about\" :       \"I love to go rock climbing \", \"interests \": [  \"sports \",  \"music \" ]}"
     * 注意:  curl命令可能存在某些使用陷进，如-H参数值必须用""来包围，更多细节需要你自己去探究。
     *
     * {
     *   "_index" : "megacorp",
     *   "_type" : "employee",
     *   "_id" : "1",
     *   "_version" : 13,                               //相同文档被索引的次数，第一次索引后该值为1，因为我这里对该条文档索引了13次
     *   "result" : "updated",
     *   "_shards" : {
     *     "total" : 2,
     *     "successful" : 1,
     *     "failed" : 0
     *   },
     *   "_seq_no" : 12,
     *   "_primary_term" : 3
     * }
     *
     * 注意，
     * 1.路径 /megacorp/employee/1 包含了三部分的信息：
     * megacorp     索引名称
     * employee     类型名称
     * 1            特定雇员的ID(就相当于数据库中的主键，代表一条文档)
     *
     * es接口可以看出是restful风格
     *
     * 2.PUT方法可用来更新，如果请求body没有传递json数据，会导致该条文档没有任何属性。
     * 因此增加和更新的时候需要注意
     *
     * 11.查
     * 检索文档
     * C:\Users\Administrator>curl -X GET "localhost:9200/megacorp/employee/1?pretty"
     * {
     *   "_index" : "megacorp",
     *   "_type" : "employee",
     *   "_id" : "1",                               //restful接口中传递的id值
     *   "_version" : 14,                           //文档版本，与更新次数有关
     *   "_seq_no" : 13,
     *   "_primary_term" : 3,
     *   "found" : true,
     *   "_source" : {
     *     "first_name" : "John ",
     *     "last_name" : "Smith",
     *     "age" : 25,
     *     "about" : "I love to go rock climbing ",
     *     "interests " : [
     *       "sports ",
     *       "musicxxxx "
     *     ]
     *   }
     * }
     *
     * 注意: type不能省略。
     *
     * 12.更新
     * 再次进行索引操作即可
     *
     * 14.删除
     * DELETE方法调用es restful接口，同上。
     */
}
