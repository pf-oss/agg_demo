package com.validation.customize.controller;


import com.validation.customize.dto.UserUpdateGenderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/24 17:41
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class UserController {

    @PostMapping("/update_gender")
    public void updateGender(@Validated UserUpdateGenderDTO updateGenderDTO) {
        log.info("[updateGender][updateGenderDTO: {}]", updateGenderDTO);
    }


}
