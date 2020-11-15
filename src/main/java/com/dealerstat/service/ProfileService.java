package com.dealerstat.service;

import com.dealerstat.entity.profile.Profile;
import com.dealerstat.entity.profile.User;
import com.dealerstat.entity.profile.comment.CommentWithTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ProfileService {
    public final CommentService commentService;
    public final UserService userService;

    @Autowired
    public ProfileService(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    public Profile findProfileById(int id) {
        User user = userService.findDealer(id);
        if (Objects.isNull(user)) throw new RuntimeException("Doesn't find dealer");
        List<CommentWithTags> comment = commentService.findDealerComments(id);
        return new Profile(user, comment);
    }

    public List<Profile> findProfiles() {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            profiles.add(new Profile(user, commentService.findDealerComments(user.getId())));
        }
        return profiles;
    }

    public List<Profile> findTopProfiles() {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            profiles.add(new Profile(user, commentService.findDealerComments(user.getId())));
        }
        profiles.sort(this::compareTo);
        return profiles;
    }

    public List<Profile> findTopProfilesTags(Set<String> tags) {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            Profile profile = new Profile(user, commentService.findDealerComments(user.getId()));
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

    public List<Profile> findMaxDealers(int max) {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            Profile profile = new Profile(user, commentService.findDealerComments(user.getId()));
            if (profile.getAverageRatingForTop() <= max) {
                profiles.add(profile);
            }
        }
        profiles.sort(this::compareTo);
        return profiles;
    }

    public List<Profile> getMinDealers(int min) {
        List<Profile> profiles = new ArrayList<>();
        List<User> users = userService.getUsers();
        for (User user : users) {
            Profile profile = new Profile(user, commentService.findDealerComments(user.getId()));
            if (profile.getAverageRating() >= min) {
                profiles.add(profile);
            }
        }
        profiles.sort(this::compareTo);
        return profiles;
    }

    public List<Profile> getMinAndMaxDealers(int min, int max) {
        List<Profile> maxDealers = findMaxDealers(max);
        List<Profile> minDealers = getMinDealers(min);
        maxDealers.retainAll(minDealers);
        return maxDealers;
    }

    private int compareTo(Profile profileOne, Profile profileTwo) {
        return Double.compare(profileTwo.getAverageRating(), profileOne.getAverageRating());
    }
}
