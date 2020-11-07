package com.dealerstat.config;

import com.dealerstat.dao.*;
import com.dealerstat.entity.User;
import com.dealerstat.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public UserService getUserService(){
        return new UserServiceImpl();
    }

    @Bean
    public AdminService getAdminService(){
        return new AdminServiceImpl();
    }

    @Bean
    public User getUser(){
        User user = new User();
        user.setRole("ANON");
        return user;
    }

    @Bean
    public CommentService commentService(){
        return new CommentServiceImpl();
    }
}
