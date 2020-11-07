package com.dealerstat.config;

import com.dealerstat.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoConfig {

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
        return new UserDaoImpl(getJDBCTemplate());
    }

    @Bean
    public CommentsDao getCommentDao(){
        return new CommentsDaoImpl(getJDBCTemplate());
    }

    @Bean
    public AdminDao getAdminDao(){
        return new AdminDaoImpl(getJDBCTemplate());
    }
}
