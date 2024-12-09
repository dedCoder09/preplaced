package org.example.strategy;

import org.example.entity.*;
import org.example.manager.GameManager;
import org.example.manager.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class GameMatchmakingEngineStrategy {

    static ReentrantLock lock = new ReentrantLock();

    public synchronized static void processRequest(Request request){
        try{
            lock.lock();
            Map<String, Game> gameMap = GameManager.getAllGames();
            System.out.println("gameMap size -> " + gameMap.size());
            for(String gameId : gameMap.keySet()){
                Game game = gameMap.get(gameId);
                if(!game.isGameStarted() && isMatch(game,request)){
                    addPlayersToGame(game, request.getPlayerIds());
                    return;
                }
            }
            startNewGame(request);
        }finally {
            lock.unlock();
        }
    }

    private static boolean isMatch(Game game, Request request){
        System.out.println("inside isMatch with game -> " + game);
        System.out.println("inside isMatch with request -> " + request);
        boolean isMatchingGameModes = false;
        List<String> requestGameModesString = request.getGameModes().stream().map(GAME_MODE::name).collect(Collectors.toList());
        List<String> gameModesString = game.getProbableGameModes().stream().map(GAME_LOCATION::name).collect(Collectors.toList());
        for(GAME_MODE gameMode : game.getProbableGameModes()){

        }
        int playersRequiredForGameToStart = game.getGameMode().getPlayerCount() - game.getCurrentPlayerIds().size();
        System.out.println("playersRequiredForGameToStart -> " + playersRequiredForGameToStart);
        System.out.println("request.getPlayerIds().size() -> " + request.getPlayerIds().size());
        if(playersRequiredForGameToStart < request.getPlayerIds().size()){
            return false;
        }
        //System.out.println("check1");
        if(!request.getGameModes().contains(game.getGameMode())){
            return false;
        }
        //System.out.println("check2");
        boolean isMatchingLocation = false;
        List<String> requestLocationsString = request.getGameLocations().stream().map(GAME_LOCATION::name).collect(Collectors.toList());
        List<String> gameLocationsString = game.getProbableGameLocations().stream().map(GAME_LOCATION::name).collect(Collectors.toList());
        for(String gameLocation : gameLocationsString){
            if(requestLocationsString.contains(gameLocation)){
                isMatchingLocation = true;
            }
        }
        if(!isMatchingLocation){
            return false;
        }
        //System.out.println("check3");
        if("SameRank".equalsIgnoreCase(request.getRankType())){
            RANK requestedPlayerRank = PlayerManager.getPlayer(request.getPlayerIds().get(0)).getRank();
            for(String gamePlayerId : game.getCurrentPlayerIds()){
                if(!PlayerManager.getPlayer(gamePlayerId).getRank().equals(requestedPlayerRank)){
                    return false;
                }
            }
        }
        //System.out.println("check4");
        return true;
    }

    private static void startNewGame(Request request){
        Game game = new Game(request.getGameModes(), request.getGameLocations());
        GameManager.addGame(game);
        addPlayersToGame(game, request.getPlayerIds());
        for(String playerId : request.getPlayerIds()){
            Player player = PlayerManager.getPlayer(playerId);
            player.setCurrentGameId(game.getId());
        }
    }

    private static void addPlayersToGame(Game game, List<String> playerList){
        for(String playerId : playerList){
            game.addPlayers(playerId);
        }
        if(game.canStartGame()){
            game.startGame();
        }
    }

}
