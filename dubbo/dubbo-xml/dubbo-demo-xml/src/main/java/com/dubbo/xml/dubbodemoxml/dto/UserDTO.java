package com.dubbo.xml.dubbodemoxml.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/25 14:28
 */
@Data
public class UserDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;


}
