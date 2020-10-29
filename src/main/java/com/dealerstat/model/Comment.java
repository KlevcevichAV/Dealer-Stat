package com.dealerstat.model;

import java.util.HashSet;
import java.util.Set;

public class Comment {
    private int id;
    private String message;
    private int user_id;
    private String createdAt;
    private boolean approved;
    private double appraisal;
    Set<Game> tags;

    public Comment(int id, String message, int user_id, String createdAt, double appraisal) {
        this.id = id;
        this.message = message;
        this.user_id = user_id;
        this.createdAt = createdAt;
        approved = false;
        this.appraisal = appraisal;
        tags = new HashSet<>();
    }

    public Comment(int id, String message, int user_id, String createdAt, double appraisal, Set<Game> tags) {
        this.id = id;
        this.message = message;
        this.user_id = user_id;
        this.createdAt = createdAt;
        this.approved = false;
        this.appraisal = appraisal;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public double getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(double appraisal) {
        this.appraisal = appraisal;
    }

    public Set<Game> getTags() {
        return tags;
    }

    public void setTags(Set<Game> tags) {
        this.tags = tags;
    }
}
