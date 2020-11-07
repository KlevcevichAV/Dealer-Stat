package com.dealerstat.repository;

import com.dealerstat.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {
    Game findById(int id);
}
