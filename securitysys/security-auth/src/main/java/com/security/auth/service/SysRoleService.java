package com.security.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.auth.model.domain.SysRole;

import java.util.Set;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:34
 */
public interface SysRoleService  extends IService<SysRole> {


    /**
     * @Description:
     * @param userId:
     * @return: java.util.Set<java.lang.String>
     */
    Set<String> selectRolePermissionByUserId(Long userId);
}