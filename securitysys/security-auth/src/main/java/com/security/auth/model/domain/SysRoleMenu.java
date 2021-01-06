package com.security.auth.model.domain;

import lombok.Data;

import java.io.Serializable;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:16
 */
@Data
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 144016477187816000L;

    /**
     * 角色ID
     */
     private Long roleId;
    
    /**
     * 菜单ID
     */
     private Long menuId;

}