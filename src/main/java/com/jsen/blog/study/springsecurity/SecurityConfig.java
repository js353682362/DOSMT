package com.jsen.blog.study.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @（#）:SecurityConfig.java
 * @description:
 * @author: jiaosen 2018/6/15
 * @version: Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注入我们自己的AuthenticationProvider
     */
    @Autowired
    private AuthenticationProvider provider;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("123456").roles("USER");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);

        // auth.inMemoryAuthentication().withUser("admin").password("123456").roles("USER").and()
        // .withUser("test").password("123456").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHander).permitAll()
                // 这就表示 /index这个页面不需要权限认证，所有人都可以访问
                .and().authorizeRequests()
                // .antMatchers("/asciiCamera").permitAll()
                // 这就表示/whoim的这个资源需要有ROLE_ADMIN的这个角色才能访问。不然就会提示拒绝访问
                // .antMatchers("/whoim").hasRole("ADMIN")
                // .antMatchers(HttpMethod.POST, "/user/*").hasRole("ADMIN")
                // .antMatchers(HttpMethod.GET, "/user/*").hasRole("USER")

                // 必须经过认证以后才能访问
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .anyRequest().authenticated().and().csrf().disable();

        // // loginPage("/index")表示登录时跳转的页面，因为登录页面我们不需要登录认证，所以我们需要添加 permitAll() 方法。
        // // 表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
        // http.formLogin().loginPage("/login").loginProcessingUrl("/login/form")
        // .failureUrl("/login-error").permitAll()
        // .and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();
    }
}
