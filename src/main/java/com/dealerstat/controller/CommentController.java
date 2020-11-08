package com.dealerstat.controller;

import com.dealerstat.entity.Game;
import com.dealerstat.entity.profile.comment.CommentWithTags;
import com.dealerstat.repository.GameRepository;
import com.dealerstat.service.CommentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    private final CommentServiceImpl commentService;

    private final GameRepository gameRepository;

    public CommentController(CommentServiceImpl commentService, GameRepository gameRepository) {
        this.commentService = commentService;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/dealer/{id}/comments")
    public String getComments(@PathVariable int id, Model model) {
        List<CommentWithTags> comments = commentService.getCommentsDealer(id);
        model.addAttribute("comments", comments);
        return "lists/comments";

    }

    @GetMapping("/dealer/{dealer_id}/comments/{id}")
    public String getComment(@PathVariable int id, @PathVariable int dealer_id, Model model) {
        CommentWithTags comment = commentService.getComment(dealer_id, id);
        model.addAttribute("comment", comment);
        return "comment";
    }

    @GetMapping("/dealer/{id}/createComment")
    public String showAddCommentsPage(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "create/addComment";
    }


    @PostMapping("/dealer/{id}/createComment")
    public String addComment(@RequestBody CommentWithTags comment, @PathVariable int id) {
        comment.getComment().setUserId(id);
        commentService.addComment(comment.getComment());
        for (Game game : comment.getTags()) {
            gameRepository.save(game);
        }
        return "redirect:/dealer/{id}";
    }

}
