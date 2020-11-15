package com.dealerstat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class AuthProviderSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public AuthProviderSecurityConfig(DbAuthProvider authProvider, AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**", "/games/{id}/edit").hasAnyRole("ADMIN")
                .antMatchers("/registration", "/dealer/{id}/createComment").anonymous()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
    }

}
