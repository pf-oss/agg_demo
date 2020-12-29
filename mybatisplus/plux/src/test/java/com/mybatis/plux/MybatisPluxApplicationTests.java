package com.mybatis.plux;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatis.plux.dao.UserDao;
import com.mybatis.plux.model.User;
import com.mybatis.plux.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPluxApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        Page page = new Page(1, 5);
        IPage<User> userIPage = userDao.selectAll(page);
        System.out.println("========" + JSON.toJSONString(userIPage));
    }

    @Test
    void serviceTest() {
        IPage<User> userIPage = userService.selectAll();
        System.out.println("========" + JSON.toJSONString(userIPage));
    }

}
