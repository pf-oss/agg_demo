package com.el.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/20 15:35
 */

@Data
@AllArgsConstructor
@Document(indexName = "book")
public class Book {

    @Id
    private Integer id;

    private String title;

    private BigDecimal price;

    @Field(type = FieldType.Keyword)
    private List<String> tag;

}
