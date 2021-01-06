package com.security.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.auth.dao.SysPostDao;
import com.security.auth.model.domain.SysPost;
import com.security.auth.service.SysPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:43
 */
@Service("sysPostService")
public class SysPostServiceImpl extends ServiceImpl<SysPostDao, SysPost> implements SysPostService {
    @Resource
    private SysPostDao sysPostDao;

}