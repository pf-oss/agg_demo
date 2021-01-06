package com.security.auth.model.domain;

import lombok.Data;

import java.io.Serializable;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:16
 */
@Data
public class SysRoleDept implements Serializable {
    private static final long serialVersionUID = 951555552917227175L;

    /**
     * 角色ID
     */
     private Long roleId;
    
    /**
     * 部门ID
     */
     private Long deptId;

}