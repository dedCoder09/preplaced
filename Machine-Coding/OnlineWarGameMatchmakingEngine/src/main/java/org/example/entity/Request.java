package org.example.entity;

import java.util.List;

public class Request {
    List<String> playerIds;
    List<GAME_MODE> gameModes;
    List<GAME_LOCATION> gameLocations;
    String rankType;

    public Request(List<String> playerIds, List<GAME_MODE> gameModes, List<GAME_LOCATION> gameLocations, String rankType) {
        this.playerIds = playerIds;
        this.gameModes = gameModes;
        this.gameLocations = gameLocations;
        this.rankType = rankType;
    }

    public List<String> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<String> playerIds) {
        this.playerIds = playerIds;
    }

    public List<GAME_MODE> getGameModes() {
        return gameModes;
    }

    public void setGameModes(List<GAME_MODE> gameModes) {
        this.gameModes = gameModes;
    }

    public List<GAME_LOCATION> getGameLocations() {
        return gameLocations;
    }

    public void setGameLocations(List<GAME_LOCATION> gameLocations) {
        this.gameLocations = gameLocations;
    }

    public String getRankType() {
        return rankType;
    }

    public void setRankType(String rankType) {
        this.rankType = rankType;
    }

    @Override
    public String toString() {
        return "Request{" +
                "playerIds=" + playerIds +
                ", gameModes=" + gameModes +
                ", gameLocations=" + gameLocations +
                ", rankType='" + rankType + '\'' +
                '}';
    }
}
