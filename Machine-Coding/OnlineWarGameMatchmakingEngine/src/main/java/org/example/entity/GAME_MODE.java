package org.example.entity;

public enum GAME_MODE {
    TWOVTWO(4),
    FASTDRAW(2),
    RAID(6);

    int playerCount;

    GAME_MODE(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}
