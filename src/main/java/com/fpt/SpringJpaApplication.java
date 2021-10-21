package com.fpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class SpringJpaApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable csrf, moi request phai xac thuc, phuong thuc la basic
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //cau hinh ko su dung security cho cac static
        web.ignoring().antMatchers("/css/**", "/js/**", "/image/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //luu tam admin va mk 123
        auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
    }
}
