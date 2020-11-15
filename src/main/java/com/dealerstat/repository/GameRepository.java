package com.dealerstat.repository;

import com.dealerstat.entity.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Integer> {
    Game findById(int id);

    List<Game> findAll();

    Game findByName(String name);
}
