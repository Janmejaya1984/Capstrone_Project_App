package com.java.springbootdemo.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder builder)throws Exception{
      builder.inMemoryAuthentication().withUser("John")
              .password("{noop}john@123").roles("Admin")
              .and().withUser("foo").password("{noop}foo@123").roles("User");
    }
    public void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeHttpRequests().antMatchers("/api/v1/books/**")
                .hasRole("Admin")
                .antMatchers("/api/v1/messages").hasRole("User")
                .anyRequest().authenticated().and().httpBasic();
        httpSecurity.csrf().disable();
    }

}
