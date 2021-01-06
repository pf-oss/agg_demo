package com.security.auth.model.domain;

import lombok.Data;

import java.io.Serializable;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:16
 */
@Data
public class SysUserPost implements Serializable {
    private static final long serialVersionUID = -59847163973322731L;

    /**
     * 用户ID
     */
     private Long userId;
    
    /**
     * 岗位ID
     */
     private Long postId;

}