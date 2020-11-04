package com.dealerstat.dao;

import com.dealerstat.entity.Comment;
import com.dealerstat.entity.CommentGame;
import com.dealerstat.entity.Game;
import com.dealerstat.mapper.CommentGameMapper;
import com.dealerstat.mapper.CommentMapper;
import com.dealerstat.mapper.GameMapper;
import com.dealerstat.service.ThereIsNoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CommentsDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Comment getTagsForComment(Comment comment) {
        String sql = "SELECT * FROM comment_game WHERE comment_id=?";
        List<CommentGame> list = jdbcTemplate.query(sql, new CommentGameMapper(), comment.getId());
        sql = "SELECT * from games WHERE id=?";
        for (CommentGame cg : list) {
            comment.getTags().add(jdbcTemplate.queryForObject(sql, new GameMapper(), cg.getGameId()));
        }
        return comment;
    }

    public List<Comment> getCommentsDealer(int userId) {
        String sql = "SELECT * FROM comments WHERE user_id=? AND approved=?";
        List<Comment> result = jdbcTemplate.query(sql, new CommentMapper(), userId, 1);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, getTagsForComment(result.get(i)));
        }
        return result;
    }

    public Comment getComment(int id) {
        String sql = "SELECT * FROM comments WHERE id=? AND approved=?";
        try {
            Comment comment = jdbcTemplate.queryForObject(sql, new CommentMapper(), id, 1);
            return getTagsForComment(comment);
        }catch (RuntimeException e){
            throw new ThereIsNoSuchUserException();
        }
    }

    private Game addGame(Game game) {
        String sql = "SELECT * FROM games WHERE name=?";
        Game result = jdbcTemplate.queryForObject(sql, new GameMapper(), game.getName());
        if (result.getName() == null) {
            sql = "INSERT INTO games (name) VALUES (?)";
            jdbcTemplate.update(sql, game.getName());
            return addGame(game);
        }
        return result;
    }

    public void addComment(Comment comment) {
        String sql = "INSERT INTO comments (message, user_id, created_at, approved, appraisal) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, comment.getMessage(), comment.getUserId(), comment.getCreatedAt(), 0, comment.getAppraisal());
        sql = "SELECT * FROM comments WHERE message=? AND user_id=? AND created_at=? AND approved=? AND appraisal=? ORDER BY id DESC LIMIT 1";
        Comment newComment = jdbcTemplate.queryForObject(sql, new CommentMapper(), comment.getMessage(), comment.getUserId(), comment.getCreatedAt(), 0, comment.getAppraisal());
        for (Game game : comment.getTags()) {
            sql = "INSERT INTO comment_game (comment_id, game_id) VALUES (?,?)";
            game = addGame(game);
            jdbcTemplate.update(sql, newComment.getId(), game.getId());
        }
    }
}
