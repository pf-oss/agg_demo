package com.el;


import com.alibaba.fastjson.JSON;
import com.el.model.Car;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootTest
public class ElasticsearchRestTemplateQuery {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    String index = "cars";

    /**
     *  elasticsearchRestTemplate 实现方式
     */
    @Test
    void getById(){
        Car car = elasticsearchRestTemplate.get("eFYiKXcBpxozTdPakfjr", Car.class);
        System.out.println("通过Id查询的数据" + JSON.toJSONString(car));
    }

    /**
     *  restHighLevelClient 实现方式
     */
    @Test
    void testQueryBookByIdRest() throws Exception{
        GetRequest getRequest = new GetRequest(index, "eFYiKXcBpxozTdPakfjr");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.toString());
    }

    /**
     *  elasticsearchRestTemplate 实现方式
     */
    @Test
    void getList(){
        QueryBuilder matchQueryBuilder = new MatchQueryBuilder("color", "red");
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(matchQueryBuilder);
        SearchHits<Car> search = elasticsearchRestTemplate.search(nativeSearchQuery, Car.class);
        System.out.println("查询数据:" + JSON.toJSONString(search));
    }


    /**
     *  restHighLevelClient 实现方式
     */
    @Test
    void getListRest() throws Exception{
        QueryBuilder matchQueryBuilder = new MatchQueryBuilder("color", "red");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilder);
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询数据:" + JSON.toJSONString(search));
    }

    /**
     *  elasticsearchRestTemplate 实现方式
     */
    @Test
    void listByMultiCondition(){
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(
                new BoolQueryBuilder()
                .must(new MatchQueryBuilder("color", "red"))
                .must(new RangeQueryBuilder("price").from(0).to(20000))
        ).build();
        SearchHits<Car> search = elasticsearchRestTemplate.search(nativeSearchQuery, Car.class);
        System.out.println("多条件查询: " + JSON.toJSONString(search));
    }

    /**
     *  restHighLevelClient 实现方式
     */
    @Test
    void listByMultiConditionRest()throws Exception{
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery()
        .must(new MatchQueryBuilder("color", "red"))
        .must(new RangeQueryBuilder("price").from(0).to(20000)));
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("多条件查询:" + JSON.toJSONString(search));

    }

    /**
     *  elasticsearchRestTemplate 实现方式
     */
    @Test
    void listByPage(){
      QueryBuilder queryBuilder = new MatchQueryBuilder("color", "red");
      NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
              .withQuery(queryBuilder)
              .withPageable(PageRequest.of(0, 3))
              .build();
        SearchHits<Car> search = elasticsearchRestTemplate.search(nativeSearchQuery, Car.class);
        System.out.println("分页查询数据: " + JSON.toJSONString(search));
    }


    /**
     *  restHighLevelClient 实现方式
     */
    @Test
    void listByPageRest()throws Exception{
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.boolQuery()
                .must(new MatchQueryBuilder("color", "red"))).from(0).size(2);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("分页查询查询:" + JSON.toJSONString(search));

    }

    // ===================================================================== 聚合操作 =====================================================

    /**
     *  elasticsearchRestTemplate 实现方式
     */
    @Test
    void count(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().build();
        long count = elasticsearchRestTemplate.count(searchQuery, Car.class);
        System.out.println("文档数量：" + count);
    }

    /**
     *  restHighLevelClient 实现方式
     */
    @Test
    void countRest() throws Exception{
        CountRequest countRequest = new CountRequest(index);
        CountResponse count = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
        System.out.println("文档的数量: " + JSON.toJSONString(count));
    }

    /**
     *  elasticsearchRestTemplate 实现方式
     */
    @Test
    void avgPrice(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.avg("avg_price").field("price"))
                .build();
        SearchHits<Car> search = elasticsearchRestTemplate.search(searchQuery, Car.class, IndexCoordinates.of(index));
        System.out.println("价格的平均值: " +  JSON.toJSONString(search.getAggregations().getAsMap().get("avg_price")));
    }

    /**
     *  restHighLevelClient 实现方式
     */
    @Test
    void avgPriceRest() throws Exception{

        SearchRequest searchRequest = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(AggregationBuilders.avg("avg_price").field("price")).size(0);

        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询的平均值为:" + JSON.toJSONString(search));
    }

    /**
     *  elasticsearchRestTemplate 实现方式
     */
    @Test
    void maxPrice(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.max("max_price").field("price"))
                .build();
        SearchHits<Car> search = elasticsearchRestTemplate.search(searchQuery, Car.class, IndexCoordinates.of(index));
        System.out.println("最大的价格: " +  JSON.toJSONString(search.getAggregations().getAsMap().get("max_price")));
    }

    /**
     *  restHighLevelClient 实现方式
     */
    @Test
    void maxPriceRest() throws Exception{
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(AggregationBuilders.max("max_price").field("price")).size(0);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("最大的价格: " + JSON.toJSONString(search));
    }


}
