package com.security.oauth2.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @Description:
 * @author: pf
 * @create: 2020/12/28 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    private String userName;

    private String password;

}
