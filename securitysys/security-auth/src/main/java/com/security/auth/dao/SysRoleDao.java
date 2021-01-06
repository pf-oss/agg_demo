package com.security.auth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.auth.model.domain.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:21
 */
@Repository
public interface SysRoleDao extends BaseMapper<SysRole> {


    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRole> selectRolePermissionByUserId(Long userId);

}