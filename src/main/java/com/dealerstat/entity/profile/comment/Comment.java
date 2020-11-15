package com.dealerstat.entity.profile.comment;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "created_at")
    private String createdAt;
    private boolean approved;
    private int appraisal;

    public Comment() {
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

}
