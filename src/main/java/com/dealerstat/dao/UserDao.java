package com.dealerstat.dao;

import com.dealerstat.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUser(int id);

    String registration(User user) throws Exception;

    void edit(User user);

    User logIn(User user);

    String registrationGuest(User user);
}
