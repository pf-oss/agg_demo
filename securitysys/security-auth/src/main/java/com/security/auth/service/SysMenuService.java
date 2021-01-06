package com.security.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.auth.model.domain.SysMenu;
import com.security.auth.model.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:34
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<SysMenu> menus);

}