package com.redis.lettuce.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UserCacheObject {

    public Integer id;

    public String name;

    public Integer gender;
}
