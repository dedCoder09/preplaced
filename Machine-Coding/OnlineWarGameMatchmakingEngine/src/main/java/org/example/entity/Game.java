package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static int idCounter = 1;
    String id;
    GAME_MODE finalGameMode;
    GAME_LOCATION finalGameLocation;
    List<GAME_MODE> probableGameModes;
    List<GAME_LOCATION> probableGameLocations;
    List<String> currentPlayerIds;
    boolean gameStarted;

    public Game(List<GAME_MODE> gameModes, List<GAME_LOCATION> gameLocations) {
        this.id = "Game"+idCounter++;
        this.probableGameModes = gameModes;
        this.probableGameLocations = gameLocations;
        this.currentPlayerIds = new ArrayList<>();
        this.gameStarted = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GAME_MODE getFinalGameMode() {
        return finalGameMode;
    }

    public void setFinalGameMode(GAME_MODE finalGameMode) {
        this.finalGameMode = finalGameMode;
    }

    public GAME_LOCATION getFinalGameLocation() {
        return finalGameLocation;
    }

    public void setFinalGameLocation(GAME_LOCATION finalGameLocation) {
        this.finalGameLocation = finalGameLocation;
    }

    public List<GAME_MODE> getProbableGameModes() {
        return probableGameModes;
    }

    public void setProbableGameModes(List<GAME_MODE> probableGameModes) {
        this.probableGameModes = probableGameModes;
    }

    public List<GAME_LOCATION> getProbableGameLocations() {
        return probableGameLocations;
    }

    public void setProbableGameLocations(List<GAME_LOCATION> probableGameLocations) {
        this.probableGameLocations = probableGameLocations;
    }

    public List<String> getCurrentPlayerIds() {
        return currentPlayerIds;
    }

    public void setCurrentPlayerIds(List<String> currentPlayerIds) {
        this.currentPlayerIds = currentPlayerIds;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void addPlayers(String playerId){
        this.currentPlayerIds.add(playerId);
    }

    public void startGame(){
        setGameStarted(true);
        setFinalGameMode(getProbableGameModes().get(0));
        setFinalGameLocation(getProbableGameLocations().get(0));
    }

    public boolean canStartGame(){
        for(GAME_MODE gameMode : this.probableGameModes){
            if(gameMode.getPlayerCount() == this.getCurrentPlayerIds().size()) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", gameMode=" + finalGameMode +
                ", gameLocations=" + finalGameLocation +
                ", currentPlayerIds=" + currentPlayerIds +
                '}';
    }
}
