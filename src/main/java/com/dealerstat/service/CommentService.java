package com.dealerstat.service;

import com.dealerstat.entity.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getCommentsDealer(int userId);

    public Comment getComment(int dealerId,int id);

    public void addComment(Comment comment);
}
