package com.security.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.auth.model.domain.SysUser;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:34
 */
public interface SysUserService extends IService<SysUser> {


    SysUser selectUserByUserName(String userName);


}