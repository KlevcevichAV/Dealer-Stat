package com.dealerstat.entity.profile.comment;

import com.dealerstat.entity.Game;

import java.util.Set;

public class CommentWithTags {
    private Comment comment;
    private Set<Game> tags;

    public CommentWithTags() {
    }

    public CommentWithTags(Comment comment, Set<Game> tags) {
        this.comment = comment;
        this.tags = tags;
    }

    public boolean checkingIfTagExists(Set<String> tags) {
        for (Game game : this.tags) {
            for (String tag : tags) {
                if (game.getName().equals(tag)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Set<Game> getTags() {
        return tags;
    }

    public void setTags(Set<Game> tags) {
        this.tags = tags;
    }

}
