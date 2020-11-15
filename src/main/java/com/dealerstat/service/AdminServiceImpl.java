package com.dealerstat.service;

import com.dealerstat.entity.CommentGame;
import com.dealerstat.entity.Game;
import com.dealerstat.entity.profile.User;
import com.dealerstat.entity.profile.comment.Comment;
import com.dealerstat.entity.profile.comment.CommentWithTags;
import com.dealerstat.repository.CommentGameRepository;
import com.dealerstat.repository.CommentRepository;
import com.dealerstat.repository.GameRepository;
import com.dealerstat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class AdminServiceImpl{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CommentGameRepository commentGameRepository;

    public List<User> getUsersForApproved() {
        return userRepository.findAllByApprovedAndRole(false, "DEALER");
    }

    public User getUserForApproved(int id) {
        return userRepository.findByIdAndRoleAndApproved(id, "DEALER", false);
    }

    public User setDealerApproved(int id){
        User user = userRepository.findById(id);
        user.setApproved(true);
        userRepository.save(user);
        return user;
    }

    public User setDealerUnapproved(int id){
        User user = userRepository.findById(id);
        user.setApproved(false);
        userRepository.save(user);
        return user;
    }

    public List<Comment> getCommentsForApproved() {
        return commentRepository.findByApproved(false);
    }

    public CommentWithTags getComment(int id) {
        Comment comment = commentRepository.findById(id);
        Set<Game> tags = new HashSet<>();
        for(CommentGame cg: commentGameRepository.findByCommentId(id)){
            tags.add(gameRepository.findById(cg.getGameId()));
        }
        return new CommentWithTags(comment, tags);
    }

    public Comment setCommentApproved(int id){
        Comment comment = commentRepository.findById(id);
        comment.setApproved(true);
        commentRepository.save(comment);
        return comment;
    }

    public Comment setCommentUnapproved(int id){
        Comment comment = commentRepository.findById(id);
        comment.setApproved(false);
        commentRepository.save(comment);
        return comment;
    }
}
