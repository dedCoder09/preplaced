package org.example.service;

import org.example.entity.Song;
import org.example.entity.User;
import org.example.helper.Pair;
import org.example.manager.SongManager;
import org.example.manager.UserManager;
import org.example.strategy.SIBasedSongRecommendationStrategy;
import org.example.strategy.SongRecommendationStrategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class SongRecommendationService {

    public void addSong(String input){

        String[] pairs = input.replaceAll("[{}\"]", "").split(",");
        // Map to store key-value pairs
        Map<String, String> map = new HashMap<>();

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            map.put(keyValue[0].trim(), keyValue[1].trim());
        }

        // Store values in separate variables
        String name = map.get("name");
        String singer = map.get("singer");
        String genre = map.get("genre");
        Integer tempo = Integer.valueOf(map.get("tempo"));

        Song song = new Song.SongBuilder(name).setSinger(singer).setGenre(genre).setTempo(tempo).build();
        SongManager.addSong(song);
    }

    public void addUser(String input){

        String[] pairs = input.replaceAll("[{}\"]", "").split(" ");

        // Initialize variables
        String name = null;
        String[] playlist = null;
        String[] friendlist = null;

        for (String pair : pairs) {
            if (pair.contains("name:")) {
                // Extract name
                name = pair.split(":")[1].trim();
            } else if (pair.contains("playlist:")) {
                // Extract playlist and split into array
                String playlistString = pair.substring(pair.indexOf('[') + 1, pair.indexOf(']'));
                playlist = playlistString.split(",");
            } else if (pair.contains("friends:")) {
                // Extract friends and split into array
                String friendsString = pair.substring(pair.indexOf('[') + 1, pair.indexOf(']'));
                friendlist = friendsString.split(",");
            }
        }
        User user = new User(name);
        for(String songName : playlist){
            //System.out.println("songName : " + songName);
            user.getPlayListSongNames().add(SongManager.getSong(songName).getName());
        }
        for(String friendName : friendlist){
            user.getFriendNames().add(friendName);
        }
        UserManager.addUser(user);
    }

    public void updateSongRecommendation(){
        Map<String, User> userMap = UserManager.getAllUsers();
        SongRecommendationStrategy songRecommendationStrategy = new SIBasedSongRecommendationStrategy();

        for(String userName : userMap.keySet()){
            User user = userMap.get(userName);
            PriorityQueue<Pair<Double, String>> songsRecommended = songRecommendationStrategy.getRecommendedSongs(user);

            user.setRecommendedSongIds(songsRecommended);
        }
    }

    public void showSongRecommendation(String userName){
        System.out.println("Song recommendation for userName : " + userName);
        User user = UserManager.getUser(userName);
        PriorityQueue<Pair<Double,String>> recommendedSongIds = user.getRecommendedSongIds();

        Iterator iterator = recommendedSongIds.iterator();
        while(iterator.hasNext()){
            Pair<Double,String> songWithUASValue = (Pair<Double, String>) iterator.next();
            Song song = SongManager.getSong(songWithUASValue.getValue());
            System.out.println(song.getName() + " (UAS = " + songWithUASValue.getKey() + ")");
        }
        System.out.println("\n");
    }

    public void getAllSongs(){
        Map<String,Song> allSongs = SongManager.getAllSongs();
        for(String songName : allSongs.keySet()){
            System.out.println("songname -> " + allSongs.get(songName).getName());
        }
    }

    public void getAllUsers(){
        Map<String,User> allUsers = UserManager.getAllUsers();
        for(String userName : allUsers.keySet()){
            System.out.println("userName -> " + allUsers.get(userName).getName());
        }
    }
}
