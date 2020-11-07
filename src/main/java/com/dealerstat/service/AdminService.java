package com.dealerstat.service;

import com.dealerstat.entity.Comment;
import com.dealerstat.entity.User;

import java.util.List;

public interface AdminService {
    List<User> getUsersForApproved();

    User getUser(int id);

    void setDealerApproved(int id);

    void setDealerUnapproved(int id);

    List<Comment> getCommentsForApproved();

    Comment getComment(int id);

    void setCommentApproved(int id);

    void setCommentUnapproved(int id);
}
