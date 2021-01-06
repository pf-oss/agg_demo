package com.security.auth;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.security.auth.dao.SysUserDao;
import com.security.auth.model.domain.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SecurityAuthApplicationTests {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
//        sysUserLambdaQueryWrapper.in(SysUser::getUserId, userIds);
        sysUserLambdaQueryWrapper.in(SysUser::getUserId, Arrays.asList(1, 2));
//        List<SysUser> sysUsers = sysUserDao.selectList(sysUserLambdaQueryWrapper);
        SysUser sysUsers = sysUserDao.selectById(1);
        System.out.println("===============" + JSON.toJSONString(sysUsers));
    }


    @Test
    void createPassword(){
        final String admin = passwordEncoder.encode("admin");
        System.out.println("admin ====" + admin);
        System.out.println("result === " + passwordEncoder.matches("admin", admin));


    }

}
