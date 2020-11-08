package com.dealerstat.controller;

import com.dealerstat.entity.profile.User;
import com.dealerstat.entity.profile.comment.Comment;
import com.dealerstat.entity.profile.comment.CommentWithTags;
import com.dealerstat.service.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class AdminController {
    public final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/unapproved-dealer")
    public String getDealersUnapproved(Model model) {
        List<User> dealersUnapproved = adminService.getUsersForApproved();
        model.addAttribute("dealersUnapproved", dealersUnapproved);
        return "dealersUnapproved";
    }

    @GetMapping("/admin/unapproved-dealer/{id}")
    public String getUnapprovedUser(@PathVariable int id, Model model) {
        User unapprovedDealer = adminService.getUserForApproved(id);
        model.addAttribute("unapprovedDealer", unapprovedDealer);
        return "unapprovedDealer";
    }

    @PutMapping("/admin/dealer-approved/{id}")
    public String setDealerApproved(@PathVariable int id) {
        adminService.setDealerApproved(id);
        return "redirect:/admin/unapproved-dealer";
    }

    @PutMapping("/admin/dealer-unapproved/{id}")
    public void setDealerUnapproved(@PathVariable int id) {
        adminService.setDealerUnapproved(id);
    }

    @GetMapping("/admin/unapproved-comment")
    public String getCommentsUnapproved(Model model) {
        List<Comment> unapprovedComment = adminService.getCommentsForApproved();
        model.addAttribute("unapprovedComments", unapprovedComment);
        return "unapprovedComments";
    }

    @GetMapping("/admin/unapproved-comment/{id}")
    public String getUnapprovedComment(@PathVariable int id, Model model) {
        CommentWithTags comment = adminService.getComment(id);
        model.addAttribute("unapprovedComment", comment);
        return "unapprovedComment";
    }

    @PutMapping("/admin/comment-approved/{id}")
    public void setCommentApproved(@PathVariable int id) {
        adminService.setCommentApproved(id);
    }

    @PutMapping("/admin/comment-unapproved/{id}")
    public void setCommentUnapproved(@PathVariable int id) {
        adminService.setCommentUnapproved(id);
    }
}
