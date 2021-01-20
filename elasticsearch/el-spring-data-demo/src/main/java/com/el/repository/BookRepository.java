package com.el.repository;


import com.el.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 方法一 基于JPA Repository的方式，继承ElasticsearchRepository接口
 * @author: pf
 * @create: 2021/1/20 15:41
 */

@Repository
public interface BookRepository  extends ElasticsearchRepository<Book, Integer> {

    /**
     * @Description: 按价格区间查询
     * @param min:
     * @param max:
     * @return: java.util.List<com.el.model.Book>
     */
    List<Book> findByPriceBetween(BigDecimal min, BigDecimal max);

    /**
     * @Description: 按书名查询，因为使用了中文分词器ik，所以这里并不是精确查询
     * @param title:
     * @return: java.util.List<com.el.model.Book>
     */
    List<Book> findByTitle(String title);

    /**
     * @Description: 按标签匹配查询
     * @param tags:
     * @return: java.util.List<com.el.model.Book>
     */
    List<Book> findByTagIn(List<String> tags);

}
