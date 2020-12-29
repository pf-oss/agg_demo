package com.mybatis.plux.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mybatis.plux.model.User;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/29 16:29
 */
public interface UserService extends IService<User> {

    IPage<User> selectAll();

}

