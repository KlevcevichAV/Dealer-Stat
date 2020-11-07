package com.dealerstat.repository;

import com.dealerstat.entity.CommentGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentGameRepository extends CrudRepository<CommentGame, Integer> {
    List<CommentGame> findByCommentId(int commentId);
}
