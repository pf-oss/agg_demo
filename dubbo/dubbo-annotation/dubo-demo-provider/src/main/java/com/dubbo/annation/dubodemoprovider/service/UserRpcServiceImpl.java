package com.dubbo.annation.dubodemoprovider.service;

// UserRpcServiceImpl.java

import com.dubbo.xml.dubbodemoxml.api.UserRpcService;
import com.dubbo.xml.dubbodemoxml.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "${dubbo.provider.UserRpcService.version}")
public class UserRpcServiceImpl implements UserRpcService {

    @Override
    public UserDTO get(Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(id % 2 + 1);
        userDTO.setName("没有昵称：" + id);
        userDTO.setId(id);
        return userDTO;
    }
}

