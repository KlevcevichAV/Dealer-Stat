package com.dealerstat.entity.profile;

import com.dealerstat.entity.profile.comment.CommentWithTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Profile {

    public final static double NO_RATING = 999.9;
    private User user;
    private List<CommentWithTags> userComments;
    private double averageRating;

    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
    }

    public Profile(User user, List<CommentWithTags> userComments) {
        this.user = user;
        this.userComments = userComments;
        setAverageRatingCalculation();
    }

    private void setAverageRatingCalculation() {
        double result = 0;
        for (CommentWithTags userComment : userComments) {
            result += userComment.getComment().getAppraisal();
        }
        averageRating = (userComments.size() != 0) ? result / userComments.size() : -1;
    }

    public boolean calculatingAverageRatingForTags(Set<String> tags) {
        double result = 0;
        int countComments = 0;
        List<CommentWithTags> temp = new ArrayList<>();
        for (CommentWithTags userComment : userComments) {
            if (userComment.checkingIfTagExists(tags)) {
                result += userComment.getComment().getAppraisal();
                countComments++;
                temp.add(userComment);
            }
        }
        if (countComments == 0) {
            return false;
        }
        averageRating = result / countComments;
        userComments = temp;
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CommentWithTags> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<CommentWithTags> userComments) {
        this.userComments = userComments;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public double getAverageRatingForTop() {
        return (averageRating == -1) ? NO_RATING : averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
