package com.security.oauth2.demo.config;

import com.security.oauth2.demo.service.impl.DbClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;


/**
 * @Description: 认证服务器
 * @author: pf
 * @create: 2021/1/6 15:13
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServerConfiguration  extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private DbClientDetailsService dbClientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
//        clients.withClientDetails(clientDetailsService);
//        clients.withClientDetails(dbClientDetailsService);

//        clients.inMemory()
//                .withClient("passwordMode") // 客户端ID
//                .scopes("all")
//                .authorizedGrantTypes("password", "refresh_token") // 设置验证方式
//                .secret(new BCryptPasswordEncoder().encode("admin"))   //必须加密
//                .accessTokenValiditySeconds(10000) //token过期时间
//                .refreshTokenValiditySeconds(10000); //refresh过期时间;
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                //配置userService 这样每次认证的时候会去检验用户是否锁定，有效等
                .userDetailsService(userService);
    }

//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                // 开启/oauth/token_key验证端口无权限访问
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("permitAll()")
////        请求/oauth/token的，如果配置支持allowFormAuthenticationForClients的，且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter
//                .allowFormAuthenticationForClients();
//    }

}
