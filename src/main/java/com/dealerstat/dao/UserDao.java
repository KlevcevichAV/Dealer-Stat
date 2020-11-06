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

    private boolean checkEmail(String email){
        String sql = "SELECT * FROM users WHERE email=?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new UserMapper(), email);
            return false;
        }catch (RuntimeException e){
            return true;
        }
    }

    public List<User> getUsers() {
        String sql = "SELECT * FROM users WHERE role=? AND approved=?";
        return jdbcTemplate.query(sql, new UserMapper(), "DEALER", 1);
    }

    public User getUser(int id) {
        String sql = "SELECT * FROM users WHERE id=? AND role=? AND approved=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id, "DEALER", 1);
        } catch (RuntimeException e) {
            throw new ThereIsNoSuchUserException();
        }
    }

    // PASSWORD!!!!
    public void registration(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password, role, created_at, approved) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user
                .getEmail(), user.getPassword(), "DEALER", user.getCreatedAt(), 0);
    }

    public void edit(User user) {
        String sql = "UPDATE users SET first_name=?, last_name=?, email=? WHERE id=?";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
    }

    public User logIn(User user) {
        String sql = "SELECT DISTINCT * FROM users WHERE email=? AND password=? AND approved=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), user.getEmail(), user.getPassword(), 1);
        } catch (RuntimeException e) {
            return null;
        }

    }

    //     PASSWORD!!!!
    //  EMAIL

    public void registrationGuest(User user) {
        if(checkEmail(user.getEmail())){
            String sql = "INSERT INTO users (first_name, last_name, email, role, created_at, approved) VALUES (?,?,?,?,?,?)";
             jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user
                    .getEmail(), "DEALER", user.getCreatedAt(), 0);
        }
    }
}
