package com.security.oauth2.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/7 10:54
 */
@Slf4j
@Service
public class DbClientDetailsService extends JdbcClientDetailsService {

    public DbClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

}
