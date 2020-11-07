package com.dealerstat.service;

import com.dealerstat.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ProfileServiceImpl implements ProfileService{
    @Autowired
    public CommentService commentService;

    @Qualifier("getUserService")
    @Autowired
    public UserService userService;

    public Profile getProfile(int id){
        return new Profile(userService.getUser(id), commentService.getCommentsDealer(id));
    }
}
