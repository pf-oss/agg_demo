package com.springboot.validation.validationdemo.service;

import com.springboot.validation.validationdemo.dto.UserAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/24 15:55
 */
@Service
@Validated
@Slf4j
public class UserService {

    public void get(@Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
    }

    public void add(@Valid UserAddDTO addDTO) {
        log.info("[add][addDTO: {}]", addDTO);
    }

    public void add01(@Valid UserAddDTO addDTO) {
        this.add(addDTO);
    }

    public void add02(UserAddDTO addDTO) {
        self().add(addDTO);
    }

    private UserService self() {
        return (UserService) AopContext.currentProxy();
    }

}
