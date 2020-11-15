package com.dealerstat.repository;

import com.dealerstat.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    Game findById(int id);

    List<Game> findAll();

    Game findByName(String name);
}
