package com.el.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/20 15:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "cars")
public class Car {

    @Id
    private String id;

    private Long price;

    @Field(type = FieldType.Keyword)
    private String color;

    @Field(type = FieldType.Keyword)
    private String make;

    private String sold;

}
