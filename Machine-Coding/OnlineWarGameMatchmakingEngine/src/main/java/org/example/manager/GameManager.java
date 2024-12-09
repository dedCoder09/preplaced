package org.example.manager;

import org.example.entity.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameManager {

    static Map<String, Game> gameMap = new ConcurrentHashMap<>();

    public static void addGame(Game game){
        gameMap.put(game.getId(),game);
    }

    public static Game getGame(String gameId){
        return gameMap.get(gameId);
    }

    public static Map<String,Game> getAllGames(){
        return gameMap;
    }
}
