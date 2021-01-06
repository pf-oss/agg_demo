package com.security.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.auth.dao.SysUserPostDao;
import com.security.auth.model.domain.SysUserPost;
import com.security.auth.service.SysUserPostService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:43
 */
@Service("sysUserPostService")
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostDao, SysUserPost> implements SysUserPostService {
    @Resource
    private SysUserPostDao sysUserPostDao;

}