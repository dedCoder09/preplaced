package org.example;

import org.example.entity.Player;
import org.example.entity.Request;
import org.example.manager.RequestManager;
import org.example.service.OnlineWarGameMatchmakingService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OnlineWarGameMatchmakingService onlineWarGameMatchmakingService = new OnlineWarGameMatchmakingService();

        onlineWarGameMatchmakingService.addPlayer("Player1","Bronze");
        onlineWarGameMatchmakingService.addPlayer("Player2","Bronze");
        onlineWarGameMatchmakingService.addPlayer("Player3","Bronze");
        onlineWarGameMatchmakingService.addPlayer("Player4","Bronze");
        onlineWarGameMatchmakingService.addPlayer("Player5","Silver");
        onlineWarGameMatchmakingService.addPlayer("Player6","Gold");
        onlineWarGameMatchmakingService.addPlayer("Player7","Gold");
        onlineWarGameMatchmakingService.addPlayer("Player8","Platinum");
        onlineWarGameMatchmakingService.addPlayer("Player9","Diamond");

        onlineWarGameMatchmakingService.addRequest(new ArrayList<String>(List.of("Player8","Player9")),
                new ArrayList<String>(List.of("FastDraw")), new ArrayList<String>(List.of("CastleTown","AirBase")), "AnyRank");
        onlineWarGameMatchmakingService.addRequest(new ArrayList<String>(List.of("Player1","Player2")),
                new ArrayList<String>(List.of("TwoVTwo")), new ArrayList<String>(List.of("CastleTown","AirBase")), "SameRank");
        onlineWarGameMatchmakingService.addRequest(new ArrayList<String>(List.of("Player3")),
                new ArrayList<String>(List.of("TwoVTwo","Raid")), new ArrayList<String>(List.of("SavageLand","AirBase")), "SameRank");
        onlineWarGameMatchmakingService.addRequest(new ArrayList<String>(List.of("Player4")),
                new ArrayList<String>(List.of("TwoVTwo","Raid")), new ArrayList<String>(List.of("CastleTown","AirBase")), "AnyRank");
        onlineWarGameMatchmakingService.addRequest(new ArrayList<String>(List.of("Player5","Player6","Player7")),
                new ArrayList<String>(List.of("TwoVTwo","Raid")), new ArrayList<String>(List.of("SavageLand")), "AnyRank");


        List<Request> requestList = RequestManager.getAllRequest();

        //case 1
        //onlineWarGameMatchmakingService.processRequest(requestList.get(0));

        //case 2
        Thread t1 = new Thread(() -> {
            onlineWarGameMatchmakingService.processRequest(requestList.get(1));
        });
        Thread t2 = new Thread(() -> {
            onlineWarGameMatchmakingService.processRequest(requestList.get(2));
        });
        Thread t3 = new Thread(() -> {
            onlineWarGameMatchmakingService.processRequest(requestList.get(3));
        });
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onlineWarGameMatchmakingService.showAllGames();
    }
}