package com.feign.consumerdemo1.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DemoDTO implements Serializable {

    private String username;
    private String password;
}
