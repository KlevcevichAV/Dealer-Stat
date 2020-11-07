package com.dealerstat.repository;

import com.dealerstat.entity.profile.comment.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Comment findByIdAndUserIdAndApproved(int id, int userId, boolean approved);
    List<Comment> findByUserIdAndApproved(int userId, boolean approved);
}
