package com.el;

import com.el.model.Book;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootTest
class ElSpringDataDemoApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate esRestTemplate;

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    //按id查询
    @Test
    void testQueryBookById() {
        Book book = esRestTemplate.get("1", Book.class);
        Assertions.assertNotNull(book);
        System.out.println(book.toString());
    }




    //按id查询
    @Test
    void testRestHigh() throws Exception{
//        QueryBuilder queryBuilder = new TermQueryBuilder("id", "1");
        IdsQueryBuilder idsQueryBuilder = new IdsQueryBuilder();
        idsQueryBuilder.addIds("2");
     //   QueryBuilder queryBuilder = new IdsQueryBuilder("1");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(idsQueryBuilder);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("searchResponse == " + searchResponse.toString());
    }

    //按书名查询
    @Test
    void testQueryBookByTitle() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("title", "Java"))
                .build();
        SearchHits<Book> searchHits = esRestTemplate.search(searchQuery, Book.class);
        //SearchHits就是查询的结果集
        searchHits.get().forEach(hit -> {
            System.out.println(hit.getContent());
        });
    }

    //聚合操作-计算所有书籍的平均价格
    @Test
    void testAggregationBookAvgPrice() {
        //聚合名为avg_price，对price字段进行聚合，计算平均值
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.avg("avg_price").field("price"))
                .build();
        SearchHits<Book> searchHits = esRestTemplate.search(searchQuery, Book.class);
        searchHits.get().forEach(hit -> {
            System.out.println(hit.getContent());
        });
        //获取聚合结果
        if (searchHits.hasAggregations()) {
            ParsedAvg parsedAvg = searchHits.getAggregations().get("avg_price");
            Assertions.assertNotNull(parsedAvg, "无聚合结果");
            System.out.println(parsedAvg.getValue());
        }
    }

}
