package com.shuijing.peregrine.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liushuijing (shuijing@shanshu.ai)
 * @date 2021-08-09
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomerAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomerLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private CustomerAccessDeniedHandler accessDeniedHandler;

    //    @Autowired
    //    private CustomerAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //下面这两行配置表示在内存中配置了两个用户
        auth.inMemoryAuthentication().withUser("shuijing").roles("admin")
                        .password("$2a$10$zgh1tBntUwtv0TOh/PwR4OJ4cJ9oASLOScujrhv1/AAcPBpzHvC/2");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启登录配置
        http.authorizeRequests()
                        //表示访问 /hello 这个接口，需要具备 admin 这个角色
                        //                        .antMatchers("/hello").hasRole("admin")
                        //表示剩余的其他接口，登录之后就能访问
                        .anyRequest().authenticated().and().formLogin()
                        //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
                        //                        .loginPage("/login")
                        //登录处理接口
                        .loginProcessingUrl("/login")
                        //定义登录时，用户名的 key，默认为 username
                        .usernameParameter("uname")
                        //定义登录时，用户密码的 key，默认为 password
                        .passwordParameter("passwd").successHandler(authenticationSuccessHandler)
                        //和表单登录相关的接口统统都直接通过
                        .failureHandler(authenticationFailureHandler).permitAll().and().logout().logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler).permitAll().and()
                        .userDetailsService(userDetailsService).csrf().disable();

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
        //                        .authenticationEntryPoint(authenticationEntryPoint)
        ;
    }

    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        http.csrf().disable()
    //                        //                        ,"/swagger-ui.html"
    //                        .authorizeRequests()
    //                        .antMatchers("/login","/logout","/swagger-resources/**","/error","/webjars/**",
    //                        "/v2/api-docs", "/health")
    //                        .permitAll()
    //
    //                        .and()
    //                        .authorizeRequests()
    //
    //                        .anyRequest()
    //                        .authenticated()
    //
    //                        .and()
    //                        .formLogin()
    ////                        .successHandler(authenticationSuccessHandler)
    ////                        .failureHandler(authenticationFailureHandler)
    //                        .permitAll()
    //
    //                        .and()
    //                        .rememberMe()
    ////                        .tokenRepository(persistentTokenRepository())
    //                        .tokenValiditySeconds(3600)
    ////                        .userDetailsService(userDetailsService)
    //
    //                        .and()
    //                        .logout()
    ////                        .addLogoutHandler(preLogoutHandler)
    ////                        .logoutSuccessHandler(logoutSuccessHandler)
    //                        .permitAll()
    //
    //                        .and().addFilterAt(userAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
    //        ;

}
