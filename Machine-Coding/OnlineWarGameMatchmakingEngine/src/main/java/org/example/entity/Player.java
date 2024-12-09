package org.example.entity;

public class Player {
    String playerId;
    RANK rank;
    String currentGameId;

    public Player(String playerId, RANK rank) {
        this.playerId = playerId;
        this.rank = rank;
        this.currentGameId = null;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public RANK getRank() {
        return rank;
    }

    public void setRank(RANK rank) {
        this.rank = rank;
    }

    public String getCurrentGameId() {
        return currentGameId;
    }

    public void setCurrentGameId(String currentGameId) {
        this.currentGameId = currentGameId;
    }
}
