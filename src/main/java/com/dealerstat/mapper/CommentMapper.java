package com.dealerstat.mapper;

import com.dealerstat.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<CommentMapper> {
    @Override
    public CommentMapper mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getInt("id"));
        comment.setMessage(resultSet.getString("message"));
        comment.setUserId(resultSet.getInt("user_id"));
        comment.setCreatedAt(resultSet.getString("created_at"));
        comment.setApproved("true".equals(resultSet.getString("approved")));
        return null;
    }
}
