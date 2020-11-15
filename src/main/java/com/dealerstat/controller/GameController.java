package com.dealerstat.controller;

import com.dealerstat.entity.Game;
import com.dealerstat.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<Game> getGames() {
        return gameService.findGames();

    }

    @PostMapping("/games/add")
    public Game addGame(@RequestBody Game game) {
        return gameService.addGame(game);
    }

    @GetMapping("/games/{id}")
    public Game getGame(@PathVariable int id, Model model) {
        return gameService.findGame(id);
    }

    @GetMapping("/games/{id}/edit")
    public Game updateGame(@RequestBody Game game, @PathVariable int id) {
        game.setId(id);
        return gameService.updateGame(game);
    }
}
