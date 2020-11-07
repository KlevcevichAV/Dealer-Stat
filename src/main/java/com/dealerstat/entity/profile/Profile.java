package com.dealerstat.entity;

import com.dealerstat.service.ThereIsNoSuchUserException;

import java.util.List;

public class Profile {
    User user;
    List<Comment> userComments;

    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
    }

    public Profile(User user, List<Comment> userComments) {
        this.user = user;
        this.userComments = userComments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }
}
