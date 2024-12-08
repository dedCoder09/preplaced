package org.example.entity;

import org.example.helper.Pair;

import java.util.*;

public class User {

    String name;
    List<String> playListSongNames;
    List<String> friendNames;
    PriorityQueue<Pair<Double,String>> recommendedSongIds;

    public User(String name) {
        this.name = name;
        this.playListSongNames = new ArrayList<>();
        this.friendNames = new ArrayList<>();
        this.recommendedSongIds = new PriorityQueue<>((o1, o2) -> Double.compare(o2.getKey(), o1.getKey()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPlayListSongNames() {
        return playListSongNames;
    }

    public void setPlayListSongNames(List<String> playListSongNames) {
        this.playListSongNames = playListSongNames;
    }

    public List<String> getFriendNames() {
        return friendNames;
    }

    public void setFriendNames(List<String> friendNames) {
        this.friendNames = friendNames;
    }

    public PriorityQueue<Pair<Double, String>> getRecommendedSongIds() {
        return recommendedSongIds;
    }

    public void setRecommendedSongIds(PriorityQueue<Pair<Double, String>> recommendedSongIds) {
        this.recommendedSongIds = recommendedSongIds;
    }
}
