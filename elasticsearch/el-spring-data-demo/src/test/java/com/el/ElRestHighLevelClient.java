package com.el;


import com.alibaba.fastjson.JSON;
import com.el.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import java.io.IOException;
import java.util.*;

@SpringBootTest
public class ElRestHighLevelClient {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ElasticsearchRestTemplate esRestTemplate;

    String index = "cars";

    //按id查询
    @Test
    void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 10)
                .put("index.number_of_replicas", 1)
        );
        String mapping = "{\n" +
                "  \"properties\": {\n" +
                "    \"color\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"make\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"price\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"sold\": {\n" +
                "      \"type\": \"date\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        createIndexRequest.mapping(mapping, XContentType.JSON);
        CreateIndexResponse response = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println("创建索引:" + JSON.toJSONString(response));
    }

    @Test
    void delIndex() throws IOException{
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        AcknowledgedResponse deleteResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println("删除索引:" + JSON.toJSONString(deleteResponse));
    }

    @Test
    void delIndexEsRest(){
        boolean delete = esRestTemplate.indexOps(IndexCoordinates.of(index)).delete();
        System.out.println("删除索引:" + delete);
    }

    @Test
    void indexDetails()throws Exception{
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println("索引详情 aliases:" +  JSON.toJSONString(getIndexResponse.getAliases()));
        System.out.println("索引详情 settings:" + getIndexResponse.getSettings().get(index).toString());
        System.out.println("索引详情 mappings:" + JSON.toJSONString(getIndexResponse.getMappings()));
    }

    @Test
    void indexList() throws Exception{
        Response response = restHighLevelClient.getLowLevelClient().performRequest(
                new Request("GET", "/_cat/indices?h=health,status,index,docsCount,docsDeleted,storeSize&s=cds:desc&format=json&index=" + index)
        );
        System.out.println("查询信息" + JSON.toJSONString(response));
    }




    // ================================================================ 以下为数据操作 ==============================================================================


    @Test
    void insert() throws Exception{

        String source = "    {\n" +
                "        \"price\": 10000,\n" +
                "        \"color\": \"red\",\n" +
                "        \"make\": \"honda\",\n" +
                "        \"sold\": \"2014-10-28\"\n" +
                "    }";
        IndexRequest indexRequest = new IndexRequest(index);
//        indexRequest.source(XContentType.JSON,"field", "baz");
        indexRequest.source(source, XContentType.JSON);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(indexRequest);
        BulkResponse bulkItemResponses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("新增数据:" + JSON.toJSONString(bulkItemResponses.getItems()));
    }

    @Test
    void insetEsRest(){
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car("",10000L, "red", "honda", "2014-10-28");
        Car car2 = new Car("",20000L, "red", "honda", "2014-11-05");
        Car car3 = new Car("",30000L, "green", "ford", "2014-05-18");
        Car car4 = new Car("", 15000L, "blue", "toyota", "2014-07-02");
        Car car5 = new Car("", 12000L, "green", "toyota", "2014-08-19");
        Car car6 = new Car("", 20000L, "red", "honda", "2014-11-05");
        Car car7 = new Car("", 80000L, "red", "bmw", "2014-01-01");
        Car car8 = new Car("", 25000L, "blue", "ford", "2014-02-12");
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        cars.add(car6);
        cars.add(car7);
        cars.add(car8);
        Iterable<Car> save = esRestTemplate.save(cars);
        System.out.println("新增数据：" + JSON.toJSONString(save));
    }

    @Test
    void getInfo() throws Exception{
        SearchRequest searchRequest = new SearchRequest(index);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询数据===" + search.toString());
    }

    @Test
    void update() throws Exception{
        UpdateRequest updateRequest = new UpdateRequest(index, "blYPKHcBpxozTdPaLfi5");
        updateRequest.doc(XContentType.JSON, "sold", "2019-01-02");
        UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println("更新数据:" + JSON.toJSONString(update));
    }


    @Test
    void updateEsRest() throws Exception{
        Car car = new Car();
        car.setSold("2018-01-03");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(car);
        String json = objectMapper.writeValueAsString(car);
//        UpdateQuery updateQuery = UpdateQuery.builder("aVYPKHcBpxozTdPaLfi5").withDocument(Document.parse(json)).build();
        UpdateQuery updateQuery = UpdateQuery.builder("aVYPKHcBpxozTdPaLfi5").withDocument(Document.create().append("color", "red")).build();
        org.springframework.data.elasticsearch.core.query.UpdateResponse update = esRestTemplate.update(updateQuery, IndexCoordinates.of(index));
        System.out.println("esRest更新数据: " + JSON.toJSONString(update));
    }


    @Test
    void del() throws Exception{
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(index);
        deleteByQueryRequest.setQuery(new TermQueryBuilder("sold", "2018-01-03"));
        BulkByScrollResponse bulkByScrollResponse = restHighLevelClient.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        System.out.println("删除数据:" + JSON.toJSONString(bulkByScrollResponse));

    }

    @Test
    void delEsRest(){

//        // 第一种方式
//        String delete = esRestTemplate.delete("aVYPKHcBpxozTdPaLfi5", Car.class);
//
//
        // 第二种方式
        Car car = new Car();
        car.setId("b1YPKHcBpxozTdPaLfi5");
        car.setSold("2014-02-12");
        String delete = esRestTemplate.delete(car);
        System.out.println("删除数据" + delete);

    }














}
