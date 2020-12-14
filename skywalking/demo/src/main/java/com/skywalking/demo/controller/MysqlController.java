package com.skywalking.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mysql")
public class MysqlController {

    @Autowired
    private JdbcTemplate template;

    @GetMapping("/test")
    public String mysql() {
        this.selectById(1);
        return "mysql";
    }

    public Object selectById(Integer id) {
        return template.queryForObject("SELECT id, username, password FROM t_user WHERE id = ?",
                // 结果转换成对应的对象。Object 理论来说是 UserDO.class ，这里偷懒了。
                new BeanPropertyRowMapper<>(Object.class),
                id);
    }

}
