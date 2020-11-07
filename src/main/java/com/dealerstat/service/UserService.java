package com.dealerstat.service;

import com.dealerstat.entity.User;

import java.util.List;

public interface UserService {

    String registration(User user) throws Exception;

    List<User> getUsers();

    User getUser(int id);

    boolean edit(User user, User currentUser);

    User logIn(User user);

    String registrationDealerGuest(User user);
}
