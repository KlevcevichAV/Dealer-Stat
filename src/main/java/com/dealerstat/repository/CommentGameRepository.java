package com.dealerstat.repository;

import com.dealerstat.entity.CommentGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentGameRepository extends CrudRepository<CommentGame, Integer> {
    List<CommentGame> findByCommentId(int commentId);
}
