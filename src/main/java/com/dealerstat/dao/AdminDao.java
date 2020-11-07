package com.dealerstat.dao;

import com.dealerstat.entity.Comment;
import com.dealerstat.entity.User;
import com.dealerstat.mapper.CommentMapper;
import com.dealerstat.mapper.UserMapper;
import com.dealerstat.service.ThereIsNoSuchUserException;

import java.util.List;

public interface AdminDao {
    public List<User> getUsersForApproved();

    public User getUser(int id);

    public void setDealerApproved(int id);

    public void setDealerUnapproved(int id);

    public List<Comment> getCommentsForApproved();

    public Comment getComment(int id);

    public void setCommentApproved(int id);

    public void setCommentUnapproved(int id);
}
