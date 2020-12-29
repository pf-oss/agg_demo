package com.mybatis.plux.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatis.plux.dao.UserDao;
import com.mybatis.plux.model.User;
import com.mybatis.plux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/29 16:26
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
   public IPage<User> selectAll(){
       Page page = new Page(1, 5);
       IPage<User> userIPage = userDao.selectAll(page);
       return userIPage;
   }


}
