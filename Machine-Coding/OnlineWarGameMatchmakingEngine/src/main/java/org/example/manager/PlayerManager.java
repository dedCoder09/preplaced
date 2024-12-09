package org.example.manager;

import org.example.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    static Map<String, Player> playerMap = new HashMap<>();

    public static void addPlayer(Player player){
        playerMap.put(player.getPlayerId(), player);
    }

    public static Player getPlayer(String playerId){
        return playerMap.get(playerId);
    }

    public static Map<String, Player> getAllPlayer(){
        return playerMap;
    }
}
