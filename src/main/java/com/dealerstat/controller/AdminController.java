package com.dealerstat.controller;

import com.dealerstat.entity.profile.User;
import com.dealerstat.entity.profile.comment.Comment;
import com.dealerstat.entity.profile.comment.CommentWithTags;
import com.dealerstat.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    public final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/unapproved-dealer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getDealersUnapproved() {
        List<User> dealersUnapproved = adminService.getUsersForApproved();
        return dealersUnapproved;
    }

    @GetMapping("/admin/unapproved-dealer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUnapprovedUser(@PathVariable int id) {
        User unapprovedDealer = adminService.getUserForApproved(id);
        return unapprovedDealer;
    }

    @PutMapping("/admin/dealer-approved/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User setDealerApproved(@PathVariable int id) {
        return adminService.approveDealer(id);
    }

    @PutMapping("/admin/dealer-unapproved/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User setDealerUnapproved(@PathVariable int id) {
        return adminService.declineDealer(id);
    }

    @GetMapping("/admin/unapproved-comment")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Comment> getCommentsUnapproved() {
        List<Comment> unapprovedComment = adminService.findCommentsForApproved();
        return unapprovedComment;
    }

    @GetMapping("/admin/unapproved-comment/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommentWithTags getUnapprovedComment(@PathVariable int id) {
        CommentWithTags comment = adminService.findCommentById(id);
        return comment;
    }

    @PutMapping("/admin/comment-approved/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Comment setCommentApproved(@PathVariable int id) {
        return adminService.approveComment(id);
    }

    @PutMapping("/admin/comment-unapproved/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Comment setCommentUnapproved(@PathVariable int id) {
        return adminService.declineComment(id);
    }
}
