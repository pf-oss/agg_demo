package com.security.auth.model.dto;

import lombok.Data;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 17:44
 */
@Data
public class LoginBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";

}
