package com.security.oauth2.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.security.oauth2.demo.dao.UserDao;
import com.security.oauth2.demo.model.LoginUser;
import com.security.oauth2.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/6 14:50
 */
@Service
@Primary
public class LocalUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName, username);
        User user = userDao.selectOne(lambdaQueryWrapper);
        return createLoginUser(user);
    }


    public UserDetails createLoginUser(User user){
        return new LoginUser(user, Collections.EMPTY_SET);
    }
}
