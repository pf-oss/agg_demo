package com.security.oauth2.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.oauth2.demo.model.User;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/28 15:15
 */
@Repository
public interface UserDao extends BaseMapper<User> {
    // 所有的CRUD操作都已经编写完成了
}
