package com.dealerstat.dao;

import com.dealerstat.entity.Comment;
import com.dealerstat.entity.CommentGame;
import com.dealerstat.entity.User;
import com.dealerstat.mapper.CommentGameMapper;
import com.dealerstat.mapper.CommentMapper;
import com.dealerstat.mapper.GameMapper;
import com.dealerstat.mapper.UserMapper;
import com.dealerstat.service.ThereIsNoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AdminDao {

    public final JdbcTemplate jdbcTemplate;

    private Comment getTagsForComment(Comment comment) {
        String sql = "SELECT * FROM comment_game WHERE comment_id=?";
        List<CommentGame> list = jdbcTemplate.query(sql, new CommentGameMapper(), comment.getId());
        sql = "SELECT * from games WHERE id=?";
        for (CommentGame cg : list) {
            comment.getTags().add(jdbcTemplate.queryForObject(sql, new GameMapper(), cg.getGameId()));
        }
        return comment;
    }

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

    public List<Comment> getCommentsForApproved() {
        String sql = "SELECT * FROM comments WHERE approved=?";
        List<Comment> result = jdbcTemplate.query(sql, new CommentMapper(), 0);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, getTagsForComment(result.get(i)));
        }
        return result;
    }

    public Comment getComment(int id) {
        String sql = "SELECT * FROM comments WHERE id=?";
        try {
            Comment comment = jdbcTemplate.queryForObject(sql, new CommentMapper(), id);
            return getTagsForComment(comment);
        }catch (RuntimeException e){
            throw new ThereIsNoSuchUserException();
        }
    }

    public void setCommentApproved(int id) {
        String sql = "UPDATE comments SET approved=? WHERE id=?";
        try {
            jdbcTemplate.update(sql, 1, id);
        } catch (RuntimeException e) {
            throw new ThereIsNoSuchUserException();
        }
    }

    public void setCommentUnapproved(int id) {
        String sql = "UPDATE comments SET approved=? WHERE id=?";
        try {
            jdbcTemplate.update(sql, 0, id);
        } catch (RuntimeException e) {
            throw new ThereIsNoSuchUserException();
        }
    }
}
