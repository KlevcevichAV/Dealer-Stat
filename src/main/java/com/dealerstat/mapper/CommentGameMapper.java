package com.dealerstat.mapper;

import com.dealerstat.entity.CommentGame;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentGameMapper implements RowMapper<CommentGame> {

    @Override
    public CommentGame mapRow(ResultSet resultSet, int i) throws SQLException {
        CommentGame commentGame = new CommentGame();
        commentGame.setCommentId(resultSet.getInt("comment_id"));
        commentGame.setGameId(resultSet.getInt("game_id"));
        return commentGame;
    }
}
