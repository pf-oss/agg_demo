package com.security.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.auth.dao.SysDeptDao;
import com.security.auth.model.domain.SysDept;
import com.security.auth.service.SysDeptService;
import org.springframework.stereotype.Service;



@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {

}