package com.cache.rediscache;

import com.alibaba.fastjson.JSON;
import com.cache.rediscache.mapper.UserMapper;
import com.cache.rediscache.model.UserDO;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class RedisCacheApplicationTests {

    private static final String CACHE_NAME_USER = "user";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCacheManager() {
        System.out.println(cacheManager);
    }

    @Test
    public void testSelectById() {
        // 这里，胖友事先插入一条 id = 1 的记录。
        Integer id = 2;

        // 查询 id = 1 的记录
        UserDO user = userMapper.selectById(id);
        System.out.println("user：" + user);
        // 判断缓存中，是不是存在
        UserDO userDO = cacheManager.getCache(CACHE_NAME_USER).get(id, UserDO.class);
        System.out.println("user ==== " + JSON.toJSONString(userDO));
//        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));

        // 查询 id = 1 的记录
        user = userMapper.selectById(id);
        System.out.println("user：" + user);
    }

    // UserMapperTest.java

    @Test
    public void testInsert() {
        // <1> 插入记录
        UserDO user = new UserDO();
        user.setUsername(UUID.randomUUID().toString()); // 随机账号，因为唯一索引
        user.setPassword("testinsert....");
        user.setCreateTime(new Date());
        user.setDeleted(0);
        userMapper.insert0(user);

        // <2> 判断缓存中，是不是存在
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));
        System.out.println("缓存为：" + JSON.toJSONString(cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class)));
    }


    @Test
    public void testDeleteById() {
        // <1> 插入记录，为了让缓存里有记录
        UserDO user = new UserDO();
        user.setUsername(UUID.randomUUID().toString()); // 随机账号，因为唯一索引
        user.setPassword("nicai");
        user.setCreateTime(new Date());
        user.setDeleted(0);
        userMapper.insert0(user);
        // <2> 判断缓存中，是不是存在
//        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));
        System.out.println("插入缓存为：" + JSON.toJSONString(cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class)));

        // <3.1> 删除记录，为了让缓存被删除
        userMapper.deleteById(user.getId());
        // <3.2> 判断缓存中，是不是存在
//        Assert.assertNull("缓存不为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));
        System.out.println("删除缓存为：" + JSON.toJSONString(cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class)));

    }



}
