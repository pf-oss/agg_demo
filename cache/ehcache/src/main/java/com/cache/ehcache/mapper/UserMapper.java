package com.cache.ehcache.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cache.ehcache.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/15 16:49
 */
@Repository
@CacheConfig(cacheNames = "users")
public interface UserMapper extends BaseMapper<User> {

//    @Cacheable(cacheNames="users", key="#p0")
    @Cacheable(cacheNames="users")
    User selectById(Integer id);

    @CachePut(key = "#user.id")
    default User insert0(User user) {
        // 插入记录
        this.insert(user);
        // 返回用户
        return user;
    }

    @CacheEvict(key = "#id")
    int deleteById(Integer id);

}

