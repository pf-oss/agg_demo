package com.mybatis.plux.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/28 15:41
 */
@MapperScan("com.mybatis.plux.dao")
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {

    /**
     *  注册乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 逻辑删除组件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * SQL执行效率插件 @Profile({"dev","test"}) 设置 dev test 环境开启，保证我们的效率
     */
    @Bean
//    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //ms 设置sql执行的最大时间，如果超过了则不执行
        performanceInterceptor.setMaxTime(100);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     *  分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return  new PaginationInterceptor();
    }
}
