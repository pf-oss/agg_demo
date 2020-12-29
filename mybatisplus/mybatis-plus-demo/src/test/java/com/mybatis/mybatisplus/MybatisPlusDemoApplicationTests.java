package com.mybatis.mybatisplus;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatis.mybatisplus.mapper.UserDao;
import com.mybatis.mybatisplus.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        User user = userDao.selectById(1);
        log.info("user=========" + JSON.toJSONString(user));
    }


    @Test
    void testPage() {
        // 参数一：当前页
        // 参数二：页面大小
        Page<User> page = new Page<>(1,5);
        userDao.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
        log.info("user=========" + JSON.toJSONString(page));
    }

    @Test
    void insert() {
        User user = new User();
        user.setAge(18);
        user.setEmail("123@qq.com");
        user.setName("张三");
        userDao.insert(user);
        log.info("user=========" + JSON.toJSONString(user));
    }

    @Test
    void update() {
        User user = new User();
        user.setId(1343460870999482370L);
        userDao.updateById(user);
        log.info("user=========" + JSON.toJSONString(user));
    }

    @Test
    void delete() {
        userDao.deleteById(1343460870999482370L);
    }
}
