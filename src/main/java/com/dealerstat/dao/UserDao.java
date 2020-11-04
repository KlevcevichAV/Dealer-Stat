package com.dealerstat.dao;

import com.dealerstat.entity.User;
import com.dealerstat.mapper.UserMapper;
import com.dealerstat.service.ThereIsNoSuchUserException;
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
        return jdbcTemplate.query(sql, new UserMapper(), "DEALER");
    }

    public User getUser(int id) {
        String sql = "SELECT * FROM users WHERE id=? AND role=?";
        try{
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id, "DEALER");
        }catch (RuntimeException e){
            throw new ThereIsNoSuchUserException();
        }
    }

    // PASSWORD!!!!
    public void registration(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password, role, created_at) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user
                .getEmail(), user.getPassword(), "DEALER", user.getCreatedAt());
    }

    public void edit(User user) {
        String sql = "UPDATE users SET first_name=?, last_name=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
    }

    public User logIn(User user) {
        String sql = "SELECT DISTINCT * FROM users WHERE email=? AND password=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), user.getEmail(), user.getPassword());
    }
}
