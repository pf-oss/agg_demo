package com.cache.rediscache.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cache.rediscache.model.UserDO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


@Repository
//@CacheConfig(cacheNames = "user")
public interface UserMapper extends BaseMapper<UserDO> {

//        @Cacheable(cacheNames="user", key="#p0")
//    @Cacheable(cacheNames="user", key="'user'.concat(#a0)")
//    @Cacheable(cacheNames="user", key="'d'")
//    @Cacheable(cacheNames="user", key="#id")
//@Cacheable(cacheNames="user")
    @Cacheable(cacheNames="user", key="'name:'+ #p0")
//    @Cacheable(value = "UserInfoList", cacheNames="UserInfoList", key="#p0")
    UserDO selectById(Integer id);


    @CachePut(key = "#user.id")
    default UserDO insert0(UserDO user) {
        // 插入记录
        this.insert(user);
        // 返回用户
        return user;
    }

    @CacheEvict
    int deleteById(Integer id);
}
