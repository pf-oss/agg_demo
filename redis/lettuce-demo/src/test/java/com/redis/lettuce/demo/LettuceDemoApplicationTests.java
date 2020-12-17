package com.redis.lettuce.demo;

import com.redis.lettuce.demo.model.UserCacheObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class LettuceDemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStringSetKey() {
        stringRedisTemplate.opsForValue().set("yunai:o", "shuai");
    }

    @Test
    public void testStringSetKey02() {
        redisTemplate.opsForValue().set("yunai", "shuai");
    }



    @Test
    public void testStringSetKeyUserCache() {
        UserCacheObject object = new UserCacheObject();
        object.setId(1);
        object.setName("芋道源码");
        object.setGender(1); // 男
        String key = String.format("user:%d", object.getId());
        redisTemplate.opsForValue().set(key, object);
    }

    @Test
    public void testStringGetKeyUserCache() {
        String key = String.format("user:%d", 1);
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }
}
