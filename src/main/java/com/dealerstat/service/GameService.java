package com.dealerstat.service;

import com.dealerstat.entity.Game;
import com.dealerstat.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findGames() {
        return gameRepository.findAll();
    }

    public Game findGame(int id) {
        return gameRepository.findById(id);
    }

    public Game addGame(Game game) {
        gameRepository.save(game);
        return game;
    }

    public Game updateGame(Game game) {
        gameRepository.save(game);
        return game;
    }
}
