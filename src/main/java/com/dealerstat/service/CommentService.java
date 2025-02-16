package com.dealerstat.service;

import com.dealerstat.entity.CommentGame;
import com.dealerstat.entity.Game;
import com.dealerstat.entity.profile.comment.Comment;
import com.dealerstat.entity.profile.comment.CommentWithTags;
import com.dealerstat.repository.CommentGameRepository;
import com.dealerstat.repository.CommentRepository;
import com.dealerstat.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentGameRepository commentGameRepository;
    private final GameRepository gameRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentGameRepository commentGameRepository, GameRepository gameRepository) {
        this.commentRepository = commentRepository;
        this.commentGameRepository = commentGameRepository;
        this.gameRepository = gameRepository;
    }

    public List<CommentWithTags> findDealerComments(int userId) {
        List<Comment> commentList = commentRepository.findByUserIdAndApproved(userId, true);
        List<CommentWithTags> result = new ArrayList<>();
        for (Comment comment : commentList) {
            result.add(findComment(userId, comment.getId()));
        }
        return result;
    }

    public CommentWithTags findComment(int dealerId, int id) {
        Comment comment = commentRepository.findByIdAndUserIdAndApproved(id, dealerId, true);
        if (Objects.isNull(comment)) throw new RuntimeException("No comment");
        Set<Game> tags = new HashSet<>();
        for (CommentGame cg : commentGameRepository.findByCommentId(id)) {
            tags.add(gameRepository.findById(cg.getGameId()));
        }
        return new CommentWithTags(comment, tags);
    }

    public void addComment(Comment comment) {
        Calendar calendar = Calendar.getInstance();
        comment.setCreatedAt(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        commentRepository.save(comment);
    }
}
