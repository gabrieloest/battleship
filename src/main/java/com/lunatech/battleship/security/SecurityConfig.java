package com.lunatech.battleship.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
            .inMemoryAuthentication()
            .passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
            .withUser("johnDoe").password("password").roles("USER").and().withUser("myTaxi").password("password")
            .roles("USER", "ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .httpBasic().and().authorizeRequests().antMatchers("/user**").hasRole("USER").antMatchers("/**")
            .hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable();
    }

}
