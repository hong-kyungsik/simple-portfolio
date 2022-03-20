package com.example.portfolio.config;

import com.example.portfolio.security.JwtAuthenticationFilter;
import com.example.portfolio.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    protected void configure(HttpSecurity http) throws Exception{

        http.csrf()
            .disable();

        http.httpBasic()
                .disable();

        http.formLogin()
                .disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .antMatchers("/", "/**/auth/**").permitAll()
            .anyRequest().authenticated();

        http.headers()
            .addHeaderWriter(new XFrameOptionsHeaderWriter(
                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
            .frameOptions().sameOrigin();

        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);

    }
}
