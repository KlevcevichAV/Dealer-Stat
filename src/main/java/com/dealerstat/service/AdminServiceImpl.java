package com.dealerstat.service;

import com.dealerstat.dao.AdminDao;
import com.dealerstat.dao.AdminDaoImpl;
import com.dealerstat.entity.Comment;
import com.dealerstat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Autowired
    public AdminDao adminDao ;

    public List<User> getUsersForApproved() {
        return adminDao.getUsersForApproved();
    }

    public User getUser(int id) {
        return adminDao.getUser(id);
    }

    public void setDealerApproved(int id){
        adminDao.setDealerApproved(id);
    }

    public void setDealerUnapproved(int id){
        adminDao.setDealerUnapproved(id);
    }

    public List<Comment> getCommentsForApproved() {
        return adminDao.getCommentsForApproved();
    }

    public Comment getComment(int id) {
        return adminDao.getComment(id);
    }

    public void setCommentApproved(int id){
        adminDao.setCommentApproved(id);
    }

    public void setCommentUnapproved(int id){
        adminDao.setCommentUnapproved(id);
    }
}
