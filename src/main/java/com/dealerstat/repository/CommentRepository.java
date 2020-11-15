package com.dealerstat.repository;

import com.dealerstat.entity.profile.comment.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Comment findByIdAndUserIdAndApproved(int id, int userId, boolean approved);

    List<Comment> findByUserIdAndApproved(int userId, boolean approved);

    List<Comment> findByApproved(boolean approved);

    Comment findByApprovedAndUserId(boolean approved, int userId);

    Comment findById(int id);
}
