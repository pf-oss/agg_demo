package com.datasource.hikaricpmultiple.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/23 17:15
 */
@Component
@Slf4j
public class InitializationController implements CommandLineRunner {


    @Resource(name = "ordersDataSource")
    private DataSource ordersDataSource;

    @Resource(name = "usersDataSource")
    private DataSource usersDataSource;


    @Override
    public void run(String... args) throws Exception {

        // orders 数据源
        try (Connection conn = ordersDataSource.getConnection()) {
            // 这里，可以做点什么
            log.info("[run][ordersDataSource 获得连接：{}]", conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // users 数据源
        try (Connection conn = usersDataSource.getConnection()) {
            // 这里，可以做点什么
            log.info("[run][usersDataSource 获得连接：{}]", conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
