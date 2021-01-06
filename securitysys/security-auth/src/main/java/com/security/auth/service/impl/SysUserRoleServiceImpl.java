package com.security.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.auth.dao.SysUserRoleDao;
import com.security.auth.model.domain.SysUserRole;
import com.security.auth.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:44
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;
}