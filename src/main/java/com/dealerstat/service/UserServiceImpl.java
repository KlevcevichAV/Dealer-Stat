package com.dealerstat.service;

import com.dealerstat.dao.UserDao;
import com.dealerstat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserDao userDao;

    public String registration(User user) throws Exception {
        return userDao.registration(user);

    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUser(int id) {
        return userDao.getUser(id);
    }

    public boolean edit(User user, User currentUser) {
        if (user.getId() == currentUser.getId() && user.getId() != 0) {
            if (user.getEmail()  == null) {
                user.setEmail(currentUser.getEmail());
            }
            if (user.getFirstName() == null) {
                user.setFirstName(currentUser.getFirstName());
            }
            if (user.getLastName() == null) {
                user.setLastName(currentUser.getLastName());
            }
            userDao.edit(user);
            return true;
        } else return false;
    }

    public User logIn(User user) {
        return userDao.logIn(user);
    }

    public String registrationDealerGuest(User user){
        return userDao.registrationGuest(user);
    }
}
