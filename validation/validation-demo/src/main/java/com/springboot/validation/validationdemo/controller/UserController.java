package com.springboot.validation.validationdemo.controller;


import com.springboot.validation.validationdemo.dto.UserAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/24 15:40
 */
@RestController
@RequestMapping("/users")
//@Validated
@Slf4j
public class UserController {

    @GetMapping("/get")
    public void get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
    }

    @PostMapping("/add")
    public void add(@Validated UserAddDTO addDTO) {
        log.info("[add][addDTO: {}]", addDTO);
    }
}

