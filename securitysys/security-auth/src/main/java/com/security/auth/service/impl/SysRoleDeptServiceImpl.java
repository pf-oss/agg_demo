package com.security.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.auth.dao.SysRoleDeptDao;
import com.security.auth.model.domain.SysRoleDept;
import com.security.auth.service.SysRoleDeptService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:43
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDept> implements SysRoleDeptService {
    @Resource
    private SysRoleDeptDao sysRoleDeptDao;

}