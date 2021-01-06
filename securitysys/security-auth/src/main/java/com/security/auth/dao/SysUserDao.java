package com.security.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.auth.model.domain.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:21
 */
@Repository
public interface SysUserDao extends BaseMapper<SysUser> {


    SysUser selectUserByUserName(String userName);

}