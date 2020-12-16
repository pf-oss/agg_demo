package com.cache.ehcache;

import com.alibaba.fastjson.JSON;
import com.cache.ehcache.mapper.UserMapper;
import com.cache.ehcache.model.User;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class EhcacheApplicationTests {

    private static final String CACHE_NAME_USER = "users";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CacheManager cacheManager;


    @Test
    public void testCacheManager() {
        System.out.println(cacheManager);
    }


    // UserMapperTest.java
    @Test
    public void testSelectById() {
        // 这里，胖友事先插入一条 id = 1 的记录。
        Integer id = 1;

        // 查询 id = 1 的记录
        User user = userMapper.selectById(id);
        System.out.println("user：" + user);
        // 判断缓存中，是不是存在
        System.out.println("缓存===" + JSON.toJSONString(cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), User.class)));
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), User.class));
        // 查询 id = 1 的记录
        user = userMapper.selectById(id);
        System.out.println("user：" + user);
    }

    // UserMapperTest.java

    @Test
    public void testInsert() {
        // <1> 插入记录
        User user = new User();
        user.setUsername(UUID.randomUUID().toString()); // 随机账号，因为唯一索引
        user.setPassword("nicai");
        user.setCreateTime(new Date());
        user.setDeleted(0);
        user.setId(2);
        userMapper.insert0(user);

        // <2> 判断缓存中，是不是存在
        String s = JSON.toJSONString(cacheManager.getCache(CACHE_NAME_USER).get(2));
        System.out.println("s==========" + s);
//        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), User.class));
    }



    
}
