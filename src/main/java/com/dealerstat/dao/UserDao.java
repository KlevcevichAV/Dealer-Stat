package com.dealerstat.dao;

import com.dealerstat.entity.User;
import com.dealerstat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        String sql = "SELECT * FROM users WHERE role=?";
        final String role = "DEALER";
        return jdbcTemplate.query(sql, new UserMapper(), role);
    }

    public User getUser(int id) {
        String sql = "SELECT * FROM users WHERE id=? AND role=?";
        final String role = "DEALER";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id, role);
    }
}
