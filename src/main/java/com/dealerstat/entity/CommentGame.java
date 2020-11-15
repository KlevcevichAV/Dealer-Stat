package com.dealerstat.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment_game")
public class CommentGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "comment_id")
    private int commentId;
    @Column(name = "game_id")
    private int gameId;

    public CommentGame() {
    }

    public CommentGame(int commentId, int gameId) {
        this.commentId = commentId;
        this.gameId = gameId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
