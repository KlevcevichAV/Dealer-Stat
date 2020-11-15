package com.dealerstat.service;

import com.dealerstat.entity.profile.Profile;
import com.dealerstat.entity.profile.User;
import com.dealerstat.entity.profile.comment.CommentWithTags;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProfileServiceImpl {
    public final CommentServiceImpl commentService;

    public final UserServiceImpl userService;

    public ProfileServiceImpl(CommentServiceImpl commentService, UserServiceImpl userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    public Profile getProfile(int id) {
        User user = userService.getUser(id);
        if (user == null) return null;
        List<CommentWithTags> comment = commentService.getCommentsDealer(id);
        return new Profile(user, comment);
    }

    public List<Profile> getProfiles() {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            profiles.add(new Profile(user, commentService.getCommentsDealer(user.getId())));
        }
        return profiles;
    }

    public List<Profile> getTopProfiles() {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            profiles.add(new Profile(user, commentService.getCommentsDealer(user.getId())));
        }
        profiles.sort(this::compareTo);
        return profiles;
    }

    public List<Profile> getTopProfilesTags(Set<String> tags) {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            Profile profile = new Profile(user, commentService.getCommentsDealer(user.getId()));
            if (profile.calculatingAverageRatingForTags(tags)) {
                profiles.add(profile);
            }
        }
        profiles.sort(this::compareTo);
        return profiles;
    }

    public void editProfile(Profile profile) {
        userService.edit(profile.getUser());
    }

    public List<Profile> getMaxDealers(int max) {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            Profile profile = new Profile(user, commentService.getCommentsDealer(user.getId()));
            if (profile.getAverageRatingForTop() <= max) {
                profiles.add(profile);
            }
        }
        return profiles;
    }

    public List<Profile> getMinDealers(int min) {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            Profile profile = new Profile(user, commentService.getCommentsDealer(user.getId()));
            if (profile.getAverageRating() >= min) {
                profiles.add(profile);
            }
        }
        return profiles;
    }

    public List<Profile> getMinAndMaxDealers(int min, int max) {
        List<Profile> maxDealers = getMaxDealers(max);
        List<Profile> minDealers = getMinDealers(min);
//        maxDealers.retainAll(minDealers);
//        return maxDealers;
        return retain(maxDealers, minDealers);
    }

    public void registration(User user) {
        userService.registration(user);
    }

    private List<Profile> retain(List<Profile> listOne, List<Profile> listTwo) {
        List<Profile> result = new ArrayList<>();
        for (Profile profileOne : listOne) {
            for (Profile profileTwo : listTwo) {
                if (profileOne.getUser().getId() == profileTwo.getUser().getId()) {
                    result.add(profileOne);
                    continue;
                }
            }
        }
        return result;
    }

    private int compareTo(Profile profileOne, Profile profileTwo) {
        return Double.compare(profileOne.getAverageRatingForTop(), profileTwo.getAverageRatingForTop());
    }
}
