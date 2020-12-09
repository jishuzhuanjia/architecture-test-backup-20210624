## ES基础
#### 1.匹配规则
term: 对于keyword类型，必须精确匹配。对于text类型，只能用来匹配某分片，不能匹配全字符串。  
query_string: 对于keyword类型，必须精确匹配才会返回。对于text类型，可以匹配任何字符串: 可以是子字符串。  
prefix: 匹配值以指定值开头的文档，如果不填入值，会匹配所有，可用来返回一些没有值的字段。  
missing: 没有对应字段。


#### 2.默认es端口：9200
#### 3.es默认为文档添加'_id'字段，就相当于主键。默认情况下，我们创建的字段值是可重复的。

#### 4.始终确保http://localhost:9200后只有一个'/',否则可能会导致查询结果错误。

#ES http操作
#### 1.计算文档的数量
#### 1.1.计算ES集群中所有文档的数量
GET http://localhost:9200/_count?pretty      
结果形如：  
{
    "count": 8,
    "_shards": {
        "total": 10,            // 等于所有索引的number_of_shards值之和
        "successful": 10,
        "skipped": 0,
        "failed": 0
    }
}

#### 1.2.计算指定索引中文档的数量
GET http://localhost:9200/${index}/_count?pretty

#### 2.分析器测试 
GET http://localhost:9200/[${index}]/_analyze                   	// 注：es-head插件中GET中没有指定json头，因此需要使用POST方法  
{  
	"analyzer": "standard",  
	"text": "i love you"  
}  
结果：  
{  
	"tokens": [  
		{  
			"token": "i",  
			"start_offset": 0,      // 词条开始位置的序号(包含此位置的字符)，从0开始  
			"end_offset": 1,        // 词条结束位置的序号(不包含此位置的字符)  
			"type": "<ALPHANUM>",  
			"position": 0           // 本词条在所有词条中的位置  
		},  
		{  
			"token": "love",  
			"start_offset": 2,  
			"end_offset": 6,  
			"type": "<ALPHANUM>",  
			"position": 1  
		},  
		{  
			"token": "you",  
			"start_offset": 7,  
			"end_offset": 10,  
			"type": "<ALPHANUM>",  
			"position": 2  
		}  
	]  
}  
注意: index是可选的，不能添加type。否则会变成添加/更新数据。  

#### 3.请求体里创建索引
PUT http://168.61.2.100:19200/${index}
{
    "settings":{										// settings可省略 
        "index.number_of_shards":1,						// 分片数，可省略,如：查询 10 个分片中用的 10 个. 8 命中. 耗时 0.000 秒 ,其中的分片就是此属性
        "index.number_of_replicas":1,					// 备份数，可省略
        "index.refresh_interval":"5s"					// 索引刷新间隔，可省略
    },
    "mappings":{
        "typename":{
            "properties":{
                "id":{
                    "type":"text"
                },
                "path":{
                    "type":"text"
                },
                "value":{
                    "type":"text"
                },
                "owner":{
                    "type":"text"
                }
            }
        }
    }
}
注意: 
1.index指定创建的索引名，不能省略。
2.mappings可为{},如果mappings中定义字段，typename字段不能省略。
3.url中不要添加type: 不要使用POST http://168.61.2.100:19200/${index}/${type}创建索引，以免索引定义错误。如：
{
	"mappings": {
		"t": {
			"properties": {
				"properties": {
					"properties": {
						"username": {
							"properties": {
								"type": {
									"type": "text",
									"fields": {
										"keyword": {
											"ignore_above": 256,
											"type": "keyword"
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

#### 4.通过url + 请求体 插入数据
http://168.61.2.100:19200/${index}/${type}/${_id}    post  
{  
    "id":"1001",  
    "path":"/app/poc/ducument/1001.doc",  
    "value":"   balabala  ",  
    "owner":"张三"  
}  
注意：
1.type不能省略，一旦省略，该命令就会变成创建索引的命令。  
2.如果省略请求体，则插入的文档只有_id字段，没有其他字段，es会为_id分配值。  
3.请求体中也可以添加嵌套属性，如：  
http://localhost:9200/index-test-1/type/23  
{  
  "info": {  
    "name": "zhoujian",  
    "age": 25  
  }  
}  

#### 5.删除数据
#### 5.1.通过url删除数据
DELETE http://168.61.2.100:19200/${index}/${type}/${_id}   

#### 5.2.查询条件删除
http://168.61.2.100:19200/${index}/${type}/_delete_by_query  
{  
"query": {  
"match": {  
			"_id": "delete_by_query"		// _id字段也可作为删除条件  
		}  
	}  
}  


#### 6.通过 url + 请求体 修改数据：_update字段在7.9.3不能添加，否则报错。
POST  http://168.61.2.100:19200/${index}/${type}/${_id}/_update  
{  
    "doc":{						// 如果请求路径添加了_update，doc 不能省略，否则报错，"reason": "Validation Failed: 1: script or doc is missing;"  	
        "id":"1001",  
        "owner":"lii三"  
    }  
}  

#### 7.通过url简单检索文档
GET  http://168.61.2.100:19200/${index}/${type}/${_id}  	
注意：  
1.index、type、_id都不能省略。  


#### 8.轻量查询格式  
curl -X GET "localhost:9200[/indexname][/typename]/_search?...pretty"  

#### 9.带有精确查询的轻量查询，会查询所有字段  
curl -X GET "localhost:9200[/indexname][/typename]/_search?q=value"

#### 10.带有精确查询的轻量查询----高亮搜索，查询指定的一个或多个字段  
curl -X GET "localhost:9200/indexname/typename/_search?q=last_name:Smith2&pretty"  
更加复杂的查询条件：  
name:("mary" "john"): name属性为mary或john的文档，多个值可以用','分隔，用空格分隔也可以  
age:>20  
注意：  
1.name:("mary" "john")多个值需要用引号包围，且必须为""，值之间可以用, ' ' 甚至没有分隔符都可以。 单个值没有限制。  

#### 11.请求体中里添加索引查询条件，其中index和type是可选的，但是如果省略了index，type一定要省略，否则报错 */
    POST  http://168.61.2.100:19200/${index}/${type}/_search
        {
        "query": {
            "match": {
                "owner": "三"
            }
        }
    }

#### 12.请求体中为索引类型加字段：type可选，在7.9.3版本添加type字段报错，因此7.9.3版本中不添加type参数  
    PUT http://168.61.9.110:9200/${index}/_mapping/${type}     		
    {
        "[typename]": {
            "properties": {
                "emp_type": {
                    "index": "not_analyzed",
                    "type": "string"
                }
            }
        }
    }
    注意：
    1.路径中index和type必须已经存在，否则报错。  
    2.请求体中typename字段是可选的，如果添加typename，则必须正确，否则报错。  

#### 13.复制索引，包括索引的字段和设置
    POST _reindex
    {
        "source": {
            "index": "indexName"	/* source.index必须存在，否则会报错no such index */
        },
        "dest": {
            "index": "indexName2"	/* dest.index可以不存在，会自动创建 */
        }
    }
    注意：
    1.有值的字段才会被添加到新索引中。
    2.如果source索引中没有数据，不会复制。

#### 14.删除索引中字段的变通实现，因为索引中的字段不能直接删除
    假设前提：当前索引indexname中误添加字段age1,我们想要还原
    1.创建一个和当前indexname定义一样的索引,我们可以叫做indexname2。
    2.将indexname内容复制到indexname2。
    3.将indexname2中age1字段有值的文档全部删除，注意这一步是关键，如果不删除，复制索引的时候，因为字段有值，会自动创建字段age1。
    4.删除索引indexname。
    5.创建索引indexname，定义和indexname删除age1字段后一样。
    6.复制indexname2到indexname，还原成功。
    注：实际的操作中，只需要将不想要的字段的数据删除，然后使用_reindex复制索引即可。


#### 15.查看索引当前映射状态
GET http://localhost:9200[/index/type]/_mapping  
testindex: 是可选的，如果省略，则返回所有索引的映射状况  
typename: 可选的，但是我添加此参数时报错, 可能因为我的es版本较高,我的版本是7.9.3, 6.4.2版本正常。  
注意：如果省略index参数，则也要省略type。  

## 以下随记

POST /customerinfos3/_close  

PUT /customerinfos3/_settings  

{  
	"analysis": {  
		"analyzer": {  
			"default": {  
				"tokenizer": "ik_max_word"  
			},  
			"pinyin_analyzer": {  
				"tokenizer": "my_pinyin"  
			}  
		},  
		"tokenizer": {  
			"my_pinyin": {  
				"type": "pinyin",  
				"keep_first_letter": false,  
				"keep_separate_first_letter": false,  
				"keep_full_pinyin": true,  
				"limit_first_letter_length": 20,  
				"lowercase": true,  
				"keep_none_chinese": true  
			}  
		}  
	}  
}  

POST /customerinfos3/_open  


POST customerinfos3/_mapping/customerinfo3  
{  
    "properties":{  
        "name_pinyin":{  
            "type":"text",  
            "analyzer": "pinyin_analyzer",  
            "fielddata":true,  
             "fields": {  
                 "keyword": {  
                    "type": "keyword",  
                    "ignore_above": 256  
                }  
            }  
        }  
    }  
}  

#### 查找字段missing的数据 
POST http://localhost:9200/index-test-1/type/_search  
{  
  "query": {  
    "bool": {					/* filter在es5.0被废弃，使用bool代替 */  
      "must_not": {  
        "exists": {				/* missing也被官方移除了 */  
          "field": "username"  
        }  
      }  
    }  
  }  
}  

#### 例：删除username字段missing的文档
http://localhost:9200/index-test-1/type/_delete_by_query  
{  
  "query": {  
    "bool": {  
      "must_not": {  
        "exists": {  
          "field": "username"  
        }  
      }  
    }  
  }  
}  
注意：  
1.如果文档字段值是""，则该文档的该字段是非missing的。  

####
2020年12月9日 15:13:59