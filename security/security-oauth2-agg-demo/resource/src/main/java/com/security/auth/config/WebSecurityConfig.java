package com.security.auth.config;


import com.security.auth.service.impl.LocalUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/7 15:30
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LocalUserServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                //用户未登录时，访问任何资源都转跳到该路径，即登录页面
                .loginPage("/portal/login")
                //登录表单form中action的地址，也就是处理认证请求的路径
                .loginProcessingUrl("/login")
                //登录认证成功后默认转跳的路径，第二个参数为true则任何情况都跳到指定url。否则会先跳到referer，referer为空才跳到指定url
                .defaultSuccessUrl("/portal/index",true)
                ///登录表单form中用户名输入框input的name名，不修改的话默认是username
                .usernameParameter("username")
                //form中密码输入框input的name名，不修改的话默认是password
                .passwordParameter("password")
                .and()
                .authorizeRequests()
                //不需要通过登录验证就可以被访问的资源路径
                .antMatchers("/portal/hideForm","/portal/login","/portal/index").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


}