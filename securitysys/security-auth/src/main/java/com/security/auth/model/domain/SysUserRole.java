package com.security.auth.model.domain;

import lombok.Data;
import java.io.Serializable;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:16
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 405017937978126659L;

    /**
     * 用户ID
     */
     private Long userId;
    
    /**
     * 角色ID
     */
     private Long roleId;

}