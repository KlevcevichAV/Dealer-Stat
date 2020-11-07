package com.dealerstat.dao;

import com.dealerstat.entity.Comment;

import java.util.List;

public interface CommentsDao {

    List<Comment> getCommentsDealer(int userId);

    Comment getComment(int dealerId, int id);

    void addComment(Comment comment);
}
