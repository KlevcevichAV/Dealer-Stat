package com.dealerstat.service;

import com.dealerstat.entity.Game;
import com.dealerstat.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl {

    @Autowired
    private GameRepository gameRepository;


    public List<Game> getGames() {
        return gameRepository.findAll();
    }


    public Game getGame(int id) {
        return gameRepository.findById(id);
    }

    public Game getGame(String name) {
        return gameRepository.findByName(name);
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
