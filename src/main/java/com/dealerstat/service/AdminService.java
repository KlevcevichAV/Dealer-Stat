package com.dealerstat.service;

import com.dealerstat.dao.AdminDao;
import com.dealerstat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminService {

    @Autowired
    public AdminDao adminDao;

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
}
