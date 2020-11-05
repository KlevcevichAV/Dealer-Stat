package com.dealerstat.service;

import com.dealerstat.dao.CommentsDao;
import com.dealerstat.dao.UserDao;
import com.dealerstat.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileService {
    @Autowired
    public CommentService commentService;

    @Autowired
    public UserService userService;

    public Profile getProfile(int id){
        return new Profile(userService.getUser(id), commentService.getCommentsDealer(id));
    }
}
