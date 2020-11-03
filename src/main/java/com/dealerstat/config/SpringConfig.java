package com.dealerstat.config;

import com.dealerstat.dao.UserDao;
import com.dealerstat.entity.User;
import com.dealerstat.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public JdbcTemplate getJDBCTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dealer_stat?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        return dataSource;
    }

    @Bean
    public UserDao getUserDao(){
        return new UserDao(getJDBCTemplate());
    }

    @Bean
    public UserService userService1(){
        return new UserService();
    }

    @Bean
    public User getUser(){
        User user = new User();
        user.setRole("ANON");
        return user;
    }

}
