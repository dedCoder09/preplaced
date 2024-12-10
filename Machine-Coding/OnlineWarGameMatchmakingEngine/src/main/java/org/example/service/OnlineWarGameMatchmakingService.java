package org.example.service;

import org.example.entity.*;
import org.example.manager.GameManager;
import org.example.manager.PlayerManager;
import org.example.manager.RequestManager;
import org.example.strategy.GameMatchmakingEngineStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class OnlineWarGameMatchmakingService {

    public void addPlayer(String id, String rank){
        Player player = new Player(id, RANK.valueOf(rank.toUpperCase()));
        PlayerManager.addPlayer(player);
    }

    public void addRequest(List<String> playerIds, List<String> gameModesString, List<String> gameLocationsString, String rankType){
        List<GAME_MODE> gameModes = gameModesString.stream().map(String::toUpperCase).map(GAME_MODE::valueOf).collect(Collectors.toList());
        List<GAME_LOCATION> gameLocations = gameLocationsString.stream().map(String::toUpperCase).map(GAME_LOCATION::valueOf).collect(Collectors.toList());
        Request request = new Request(playerIds,gameModes,gameLocations,rankType);
        RequestManager.addRequest(request);
    }

    public void processRequest(Request request){
        //System.out.println(PlayerManager.getAllPlayer().size());
        GameMatchmakingEngineStrategy.processRequest(request);
    }

    public void showAllGames(){
        Map<String,Game> gameMap = GameManager.getAllGames();
        //System.out.println("ritam " + gameMap.size());
        for(String gameId : gameMap.keySet()){
            Game game = gameMap.get(gameId);
            if(game.isGameStarted()){
                System.out.println("Playing " + game.getFinalGameMode() + " game with players: " + game.getCurrentPlayerIds() + " in " + game.getFinalGameLocation());
            }else{
                System.out.println("Waiting game with players: " + game.getCurrentPlayerIds());
            }
        }
    }
}
