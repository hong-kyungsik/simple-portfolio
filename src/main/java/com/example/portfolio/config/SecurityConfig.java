package com.example.portfolio.config;

import com.example.portfolio.security.JwtAuthenticationFilter;
import com.example.portfolio.security.PortfolioVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.ConsensusBased;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private PortfolioVoter portfolioVoter;

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        List<AccessDecisionVoter<?>> decisionVoters = List.of(
            portfolioVoter,
            webExpressionVoter
        );
        return new AffirmativeBased(decisionVoters);
    }

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
            .antMatchers("/", "/api/**/auth/**", "/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .accessDecisionManager(accessDecisionManager());

        http.headers()
            .addHeaderWriter(new XFrameOptionsHeaderWriter(
                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
            .frameOptions().sameOrigin();

        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);

    }
}
