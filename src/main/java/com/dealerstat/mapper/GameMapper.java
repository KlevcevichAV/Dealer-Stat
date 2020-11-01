package com.dealerstat.mapper;

import com.dealerstat.entity.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {
    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        Game game = new Game();
        game.setId(resultSet.getInt("id"));
        game.setName(resultSet.getString("name"));
        return game;
    }
}
