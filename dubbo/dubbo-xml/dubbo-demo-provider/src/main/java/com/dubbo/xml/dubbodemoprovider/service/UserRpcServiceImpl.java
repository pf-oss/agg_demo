package com.dubbo.xml.dubbodemoprovider.service;


import com.dubbo.xml.dubbodemoxml.api.UserRpcService;
import com.dubbo.xml.dubbodemoxml.dto.UserDTO;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/11/25 14:44
 */
@Service
public class UserRpcServiceImpl implements UserRpcService {

    @Override
    public UserDTO get(Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(id % 2 + 1);
        userDTO.setId(id);
        userDTO.setName("没有昵称：" + id);
        return userDTO;
    }
}
