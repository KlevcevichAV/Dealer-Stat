package com.dealerstat.dao;

import com.dealerstat.entity.User;
import com.dealerstat.mapper.UserMapper;
import com.dealerstat.service.ThereIsNoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AdminDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsersForApproved() {
        String sql = "SELECT * FROM users WHERE role=? AND approved=?";
        return jdbcTemplate.query(sql, new UserMapper(), "DEALER", 0);
    }

    public User getUser(int id) {
        String sql = "SELECT * FROM users WHERE id=? AND role=? AND approved=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id, "DEALER", 0);
        } catch (RuntimeException e) {
            throw new ThereIsNoSuchUserException();
        }
    }

    public void setDealerApproved(int id) {
        String sql = "UPDATE users SET approved=? WHERE id=?";
        try {
            jdbcTemplate.update(sql, 1, id);
        } catch (RuntimeException e) {
            throw new ThereIsNoSuchUserException();
        }
    }

    public void setDealerUnapproved(int id) {
        String sql = "UPDATE users SET approved=? WHERE id=?";
        try {
            jdbcTemplate.update(sql, 0, id);
        } catch (RuntimeException e) {
            throw new ThereIsNoSuchUserException();
        }
    }
}
