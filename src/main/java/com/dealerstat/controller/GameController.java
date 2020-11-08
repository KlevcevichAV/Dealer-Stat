package com.dealerstat.controller;

import com.dealerstat.entity.Game;
import com.dealerstat.service.GameServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GameController {

    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        model.addAttribute("games", gameService.getGames());
        return "lists/games";
    }

    @PostMapping("/games/add")
    public String addGame(@RequestBody Game game) {
        gameService.addGame(game);
        return "redirect:/games";
    }

    @GetMapping("/games/{id}")
    public String getGame(@PathVariable int id, Model model) {
        model.addAttribute("game", gameService.getGame(id));
        return "entity/game";
    }

    @GetMapping("/games/{id}/edit")
    public String updateGame(@RequestBody Game game, @PathVariable int id) {
        game.setId(id);
        gameService.updateGame(game);
        return "redirect:/games";
    }
}
