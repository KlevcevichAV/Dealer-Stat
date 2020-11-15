package com.dealerstat.controller;

import com.dealerstat.entity.CommentGame;
import com.dealerstat.entity.Game;
import com.dealerstat.entity.profile.comment.CommentWithTags;
import com.dealerstat.repository.CommentGameRepository;
import com.dealerstat.repository.GameRepository;
import com.dealerstat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class CommentController {
    private final CommentService commentService;
    private final GameRepository gameRepository;
    private final CommentGameRepository commentGameRepository;

    @Autowired
    public CommentController(CommentService commentService, GameRepository gameRepository, CommentGameRepository commentGameRepository) {
        this.commentService = commentService;
        this.gameRepository = gameRepository;
        this.commentGameRepository = commentGameRepository;
    }

    @GetMapping("/dealer/{id}/comments")
    public List<CommentWithTags> getComments(@PathVariable int id, Model model) {
        List<CommentWithTags> comments = commentService.findDealerComments(id);
        model.addAttribute("comments", comments);
        return comments;

    }

    @GetMapping("/dealer/{dealer_id}/comments/{id}")
    public CommentWithTags getComment(@PathVariable int id, @PathVariable int dealer_id) {
        try {
            return commentService.findComment(dealer_id, id);
        } catch (RuntimeException e) {
            return null;
        }
    }

    @PostMapping("/dealer/{id}/createComment")
    public String addComment(@RequestBody CommentWithTags comment, @PathVariable int id) {
        comment.getComment().setUserId(id);
        commentService.addComment(comment.getComment());
        for (Game game : comment.getTags()) {
            Game temp = gameRepository.findByName(game.getName());
            if (Objects.isNull(temp)) gameRepository.save(game);
            commentGameRepository.save(new CommentGame(comment.getComment().getId(), temp.getId()));
        }
        return "create";
    }

}
