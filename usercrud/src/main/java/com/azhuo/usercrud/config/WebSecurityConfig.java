package com.azhuo.usercrud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration // 声明是本类是一个配置类
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        1、authorizeRequests()：所有security全注解配置实现的开端，表示开始说明需要的权限
            需要的权限分两部分：第一部分是拦截的路径，第二部分访问该路径需要的权限。
        2、antMatchers("")：表示拦截的路径
        3、permitAll()：任何权限都可以访问，直接放行所有
           hasAnyRole()：需要拥有的权限
        4、anyRequest()：任何请求
        5、authenticated()：认证后才能访问
        6、.and().csrf().disable()：固定写法，表示使csrf拦截失效
         */
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
