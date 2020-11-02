package com.dealerstat.service;

import com.dealerstat.dao.UserDao;
import com.dealerstat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserDao userDao;

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUser(int id) {
        return userDao.getUser(id);
    }
}
