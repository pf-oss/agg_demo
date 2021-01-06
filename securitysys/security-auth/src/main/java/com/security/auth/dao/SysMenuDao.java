package com.security.auth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.auth.model.domain.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:21
 */
@Repository
public interface SysMenuDao extends BaseMapper<SysMenu> {

    /**
     * 根据用户ID查询权限
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

}