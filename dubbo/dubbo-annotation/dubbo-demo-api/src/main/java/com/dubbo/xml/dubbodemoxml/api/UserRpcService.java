package com.dubbo.xml.dubbodemoxml.api;

import com.dubbo.xml.dubbodemoxml.dto.UserDTO;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/25 14:30
 */
public interface UserRpcService {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserDTO get(Integer id);

}
