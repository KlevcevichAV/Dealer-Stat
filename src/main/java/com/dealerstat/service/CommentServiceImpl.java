package com.dealerstat.service;

import com.dealerstat.dao.CommentsDao;
import com.dealerstat.dao.CommentsDaoImpl;
import com.dealerstat.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService{
    @Autowired
    public CommentsDao commentsDao;

    public List<Comment> getCommentsDealer(int userId){
        return commentsDao.getCommentsDealer(userId);
    }

    public Comment getComment(int dealerId,int id){
        return commentsDao.getComment(dealerId, id);
    }

    public void addComment(Comment comment){
        commentsDao.addComment(comment);
    }
}
