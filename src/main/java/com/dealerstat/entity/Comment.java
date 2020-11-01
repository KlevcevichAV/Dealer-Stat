package com.dealerstat.entity;

import java.util.HashSet;
import java.util.Set;

public class Comment {
    private int id;
    private String message;
    private int userId;
    private String createdAt;
    private boolean approved;
    private int appraisal;
    Set<Game> tags;

    public Comment() {
        tags = new HashSet<>();
    }

    public Comment(int id, String message, int userId, String createdAt, int appraisal) {
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.createdAt = createdAt;
        approved = false;
        this.appraisal = appraisal;
        tags = new HashSet<>();
    }

    public Comment(int id, String message, int userId, String createdAt, int appraisal, Set<Game> tags) {
        this.id = id;
        this.message = message;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(int appraisal) {
        this.appraisal = appraisal;
    }

    public Set<Game> getTags() {
        return tags;
    }

    public void setTags(Set<Game> tags) {
        this.tags = tags;
    }
}
