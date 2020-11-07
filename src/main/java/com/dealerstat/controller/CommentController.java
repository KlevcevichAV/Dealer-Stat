package com.dealerstat.controller;

import com.dealerstat.entity.Comment;
import com.dealerstat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    public CommentService commentService;

    @GetMapping("/dealer-stat/dealer/{id}/comments")
    public List<Comment> getComments(@PathVariable int id) {
        return commentService.getCommentsDealer(id);
    }

    @GetMapping("/dealer-stat/dealer/{dealer_id}/comments/{id}")
    public Comment getComment(@PathVariable int id, @PathVariable int dealer_id) {
        return commentService.getComment(dealer_id, id);
    }

    @PostMapping(value = "/dealer-stat/dealer/{id}/createComment", consumes = "application/json", produces = "application/json")
    public String addComment(@RequestBody Comment comment, @PathVariable int id) {
        comment.setUserId(id);
        commentService.addComment(comment);
        return "redirect:/dealer-stat/dealer";
    }

}
