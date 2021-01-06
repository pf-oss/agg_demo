package com.security.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.auth.dao.SysRoleMenuDao;
import com.security.auth.model.domain.SysRoleMenu;
import com.security.auth.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:43
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {
    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

}