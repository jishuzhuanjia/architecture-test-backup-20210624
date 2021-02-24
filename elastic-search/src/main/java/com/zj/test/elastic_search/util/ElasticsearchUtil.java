package com.zj.test.elastic_search.util;

import com.alibaba.fastjson.JSONObject;
import com.zj.test.util.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021年2月21日 13:20:56
 * @description: elasticsearch工具类
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Component
@Slf4j
public class ElasticsearchUtil {

    @Autowired
    private TransportClient transportClient;

    private static TransportClient client;

    @PostConstruct
    private void init() {
        client = this.transportClient;
    }

    /**
     * 如果索引不存在，则创建索引
     *
     * @finished-Time                           2021年2月22日 09:52:59
     * @param  index                            索引名
     * @return                                  索引已经存在或创建失败, 返回false, 否则返回true。
     *
     * @exception  InvalidIndexNameException    运行时异常,索引名无效，如索引名字母必须全部小写,否则会抛出此异常
     *
     */
    public static boolean createIndex(String index) {
        if (isIndexExist(index)) {
            log.info("createIndex: Index [{}] already exist! create failed.", index);
            return false;
        }

        log.info("createIndex: Index [{}] is not exist! prepare to create...", index);
        CreateIndexResponse indexresponse = client.admin().indices().prepareCreate(index).execute().actionGet();
        log.info("createIndex: Create index [{}] " + (indexresponse.isAcknowledged() ? "successfully." : "failed."), index);
        return indexresponse.isAcknowledged();
    }

    /**
     * 删除索引
     *
     * @finished-time   2021年2月22日 10:41:15
     * @param  index    要删除的索引名
     * @return          如果成功删除返回true, 索引不存在或删除失败则返回false。
     */
    public static boolean deleteIndex(String index) {
        if (!isIndexExist(index)) {
            log.info("deleteIndex: Index [{}] is not exist! delete failed.", index);
            return false;
        }
        DeleteIndexResponse deleteIndexResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
        if (deleteIndexResponse.isAcknowledged()) {
            log.info("deleteIndex: Delete index [{}]" + " successfully.", index);
        } else {
            log.info("deleteIndex: Delete index [{}] failed.", index);
        }
        return deleteIndexResponse.isAcknowledged();
    }

    /**
     * 判断索引是否存在
     * 说明：由于判断索引是否存在所需时间较删除/创建索引微不足道(1:10左右)，因此在删除/创建索引之前进行索引存在判断可减少用时。
     *
     * @finish-time  2021年2月22日 16:38:11
     * @param index  索引
     * @return
     */
    public static boolean isIndexExist(String index) {
        // indices: index的复数
        IndicesExistsResponse inExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        return inExistsResponse.isExists();
    }

    /**
     * 判断index下指定type是否存在
     *
     * @finished-time  2021年2月22日 17:18:13
     */
    public boolean isTypeExist(String index, String type) {
        return isIndexExist(index)
                ? client.admin().indices().prepareTypesExists(index).setTypes(type).execute().actionGet().isExists()
                : false;
    }

    /**
     * 插入/更新：根据指定的id插入/更新数据，这取决于指定id的数据是否已经存在，如果存在，则是更新操作。
     *
     * @tip                 为了与update操作区分开，请使用update方法进行更新操作。
     * @tip                 不要进行批量操作，性能不好
     * @tip                 index type参数不能省略，如果索引没有完成映射，会自动完成索引和type的映射，如果已经完成映射，则index 和 type必须与已有对应，否则报错
     * @finished-time       2021年2月22日 17:18:22
     * @param  jsonObject   要插入的数据
     * @param  index        索引，类似数据库
     * @param  type         类型，类似表
     * @param  id           数据id
     * @return              返回插入/更新的数据的id
     */
    public static String insertDataUseSpecifiedId(JSONObject jsonObject, String index, String type, String id) {
        // get() = execute().actionGet()
        /*
        prepareIndex:
        1.id: 如果省略,则es自动分配id并插入数据。
        如果不省略，则是插入/更新操作。这取决于指定的id是否已经存在数据。
         */
        IndexResponse response = client.prepareIndex(index, type, id).setSource(jsonObject).get();
        //log.info("addData response status:{},id:{}", response.status().getStatus(), response.getId());
        return response.getId();
    }

    /**
     * 插入数据
     *
     * @finished-time       2021年2月23日 09:46:26
     * @tip                 不要进行批量操作，性能不好
     * @param  jsonObject   要增加的数据
     * @param  index        索引，类似数据库
     * @param  type         类型，类似表
     * @return
     */
    public static String insertDataUseRandomId(JSONObject jsonObject, String index, String type) {
        IndexResponse response = client.prepareIndex(index, type).setSource(jsonObject).get();
        return response.getId();
    }

    /**
     * 通过id删除数据
     *
     * @finish-time     2021年2月23日 09:57:38
     * @param  index    索引，类似数据库
     * @param  type     类型，类似表
     * @param  id       数据ID
     */
    public static void deleteDataById(String index, String type, String id) {
        DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();
        //log.info("deleteDataById response status:{},id:{}", response.status().getStatus(), response.getId());
    }

    /**
     * 通过id更新数据
     *
     * @finish-time             2021年2月23日 10:01:21
     * @tip                     如果指定的id数据不存在，就什么都不做
     *
     * @param  jsonObject       要增加的数据
     * @param  index            索引，类似数据库
     * @param  type             类型，类似表
     * @param  id               数据id
     * @return
     */
    public static void updateDataById(JSONObject jsonObject, String index, String type, String id) {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).type(type).id(id).doc(jsonObject);
        client.update(updateRequest);
    }

    /**
     * 通过id获取数据
     *
     * @finish-time     2021年2月23日 10:16:26
     * @param  index    索引，类似数据库
     * @param  type     类型，类似表
     * @param  id       数据id
     * @param  fields   需要显示的字段，逗号分隔（缺省为全部字段）
     * @return
     */
    public static Map<String, Object> searchDataById(String index, String type, String id, String fields) {
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        if (StringUtils.isNotEmpty(fields)) {
            getRequestBuilder.setFetchSource(fields.split(","), null);
        }
        GetResponse getResponse = getRequestBuilder.execute().actionGet();
        return getResponse.getSource();
    }

    /**
     * 结构化查询，将查询数据封装到到分页中
     *
     * @attention                       高亮字段结果将会覆盖正常返回的字段的结果
     * @attention                       高亮字段需要作为查询的条件，否则查询结果不会返回highlight而报空指针异常
     * @attention                       type传参: 对于低/高版本es,会查询索引中的多个索引，如果都不存在，不会报错，查询结果为空。
     *                                  如果不传参，则会查询索引所有存在的type
     *                                  兼容高低版本的es
     * @finish-time                     2021年2月23日 14:18:54
     *
     *
     * @param  index                        索引名称
     * @param  type                         索引type,低版本中索引支持多个type,高版本中最多只有一个type,
     *                                      但是不管传递1或多个type都不会报错。
     * @param  currentPage                  开始行,基于0
     * @param  pageSize                     每页显示条数
     * @param  query                        查询条件
     * @param  fieldsStr                    需要显示的字段，逗号分隔（缺省为全部字段）
     *                                      TIP: 字段必须以,隔开,且不能有多余的空格。
     * @param  sortField                    排序字段
     * @param  highlightFieldsStr           高亮字段
     *                                      TIP: 字段必须以,隔开,且不能有多余的空格。
     * @return
     */
    public static EsPage searchDataAsPage(String index, String type,
                                          int currentPage, int pageSize, QueryBuilder query,
                                          String fieldsStr, String sortField, String highlightFieldsStr) {
        //String[] highlightFields = getHighlightArray(highlightFieldsStr);
        String[] highlightFields = (highlightFieldsStr == null) ? null : highlightFieldsStr.split(",");
        SearchResponse searchResponse = sendSearchRequest(index, type, currentPage, pageSize, query, fieldsStr, sortField, highlightFields);

        long totalHits = searchResponse.getHits().totalHits;
        long length = searchResponse.getHits().getHits().length;

        log.debug("共查询到[{}]条数据,返回[{}]条数据.", totalHits, length);

        if (searchResponse.status().getStatus() == 200) {
            // 解析返回结果
            List<Map<String, Object>> sourceList = processSearchResponse(searchResponse, highlightFields);
            return new EsPage(currentPage, pageSize, (int) totalHits, sourceList);
        }

        return null;
    }

    /**
     * 结构化查询，将查询数据封装到到列表中
     *
     * @attention                   高亮字段结果将会覆盖正常返回的字段的结果
     * @attention                   高亮字段需要作为查询的条件，否则查询结果不会返回highlight而报空指针异常
     * @attention                   type传参: 对于低/高版本es,会查询索引中的多个索引，如果都不存在，不会报错，查询结果为空。
     *                              如果不传参，则会查询索引所有存在的type
     *                              兼容高低版本的es
     *
     * @param  index                索引名称
     * @param  type                 索引type,低版本中索引支持多个type,高版本中最多只有一个type,但是不管传递1或多个type都不会报错。
     * @param  currentPage          开始行,基于0
     * @param  pageSize             每页显示条数
     * @param  query                查询条件
     * @param  fieldsStr            需要显示的字段，逗号分隔（缺省为全部字段）
     *                              TIP: 字段必须以,隔开,且不能有多余的空格。
     * @param  sortField            排序字段
     * @param  highlightFieldsStr   高亮字段
     *                              TIP: 字段必须以,隔开,且不能有多余的空格。
     * @return
     */
    public static List<Map<String, Object>> searchDataAsList(String index, String type,
                                                             int currentPage, int pageSize, QueryBuilder query,
                                                             String fieldsStr, String sortField, String highlightFieldsStr) {
        //String[] highlightArray = getHighlightArray(highlightFieldsStr);
        String[] highlightFields = (highlightFieldsStr == null) ? null : highlightFieldsStr.split(",");
        SearchResponse searchResponse = sendSearchRequest(index, type, currentPage, pageSize, query, fieldsStr, sortField, highlightFields);

        if (searchResponse.status().getStatus() == 200) {
            // 解析返回结果
            return processSearchResponse(searchResponse, highlightFields);
        }
        return null;
    }

    /**
     * 加工es响应结果
     *
     * @param searchResponse
     * @param highlightFields
     */
    private static List<Map<String, Object>> processSearchResponse(SearchResponse searchResponse, String[] highlightFields) {
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        boolean hasHighlight = (null != highlightFields && highlightFields.length > 0);

        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            // 1.将文档id封装到返回结果中
            searchHit.getSourceAsMap().put("id", searchHit.getId());

            // 2.加工高亮字段: 会覆盖正常的返回值。
            if (hasHighlight) {
                for (int i = 0; i < highlightFields.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Text[] text = searchHit.getHighlightFields().get(highlightFields[i]).getFragments();
                    if (text != null) {
                        for (Text str : text) {
                            // <em>123456</em>
                            // TestHelper.println("fragment",str);
                            stringBuilder.append(str.string());
                        }
                        searchHit.getSourceAsMap().put(highlightFields[i], stringBuilder.toString());
                    }
                }
            }
            sourceList.add(searchHit.getSourceAsMap());
        }
        return sourceList;
    }

    private static SearchResponse sendSearchRequest(String index, String type, int currentPage, int pageSize, QueryBuilder query, String fields, String sortField, String[] highlightFields) {
        // 1.设置索引
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        if (StringUtils.isNotEmpty(type)) {
            // 2.设置类型
            /*
            设置查询的type,可以不存在
            之所以参数为数组类型，可以兼容es版本,因为高版本es中index最多有一个type
             */
            searchRequestBuilder.setTypes(type.split(","));
        }
        searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);

        // 需要显示的字段，逗号分隔（缺省为全部字段）
        if (StringUtils.isNotEmpty(fields)) {
            searchRequestBuilder.setFetchSource(fields.split(","), null);
        }

        //排序字段
        if (StringUtils.isNotEmpty(sortField)) {
            searchRequestBuilder.addSort(sortField, SortOrder.DESC);
        }

        if (null != highlightFields && highlightFields.length > 0) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            // 默认前后缀: <em> </em>
            /*highlightBuilder.preTags("<span style='color:red' >");//设置前缀
            highlightBuilder.postTags("</span>");//设置后缀*/
            // 设置高亮字段
            for (int i = 0; i < highlightFields.length; i++) {
                // TestHelper.println("高亮字段", highlightFields[i]);
                highlightBuilder.field(highlightFields[i]);
            }
            searchRequestBuilder.highlighter(highlightBuilder);
        }

        //searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery());
        searchRequestBuilder.setQuery(query);

        // 分页(浅分页)
        searchRequestBuilder.setFrom((currentPage - 1) * pageSize).setSize(pageSize);

        // 设置是否按查询匹配度排序
        searchRequestBuilder.setExplain(true);

        //打印的内容 可以在 Elasticsearch head 和 Kibana  上执行查询
        log.debug("\n{}", searchRequestBuilder);

        //TimeHelper.start();
        // 执行搜索,返回搜索响应信息
        return searchRequestBuilder.execute().actionGet();
    }

    /**
     *
     * @param highlightFieldsStr 高亮字符串,不同字段以,隔开
     * @return
     */
    private static String[] getHighlightArray(String highlightFieldsStr) {
        String[] highlightFields = null;
        if (StringUtils.isNotBlank(highlightFieldsStr)) {
            // 字段不能包含空格，所以需要移除空格
            highlightFields = StringUtils.remove(highlightFieldsStr, " ").split(",");
        }
        return highlightFields;
    }

    public static void main(String[] args) {
        // 移除指定的字符,不会修改源字符串
        String s = " This is my world!";
        TestHelper.println(StringUtils.remove(s, ' '));
        TestHelper.println(s);
    }
}