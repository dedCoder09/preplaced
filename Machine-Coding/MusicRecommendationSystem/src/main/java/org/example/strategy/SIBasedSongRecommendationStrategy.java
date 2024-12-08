package org.example.strategy;

import org.example.entity.Song;
import org.example.entity.User;
import org.example.helper.Pair;
import org.example.manager.SongManager;
import org.example.manager.UserManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SIBasedSongRecommendationStrategy implements SongRecommendationStrategy{
    @Override
    public PriorityQueue<Pair<Double, String>> getRecommendedSongs(User user) {

        PriorityQueue<Pair<Double,String>> songsRecommended = new PriorityQueue<>((o1, o2) -> Double.compare(o2.getKey(), o1.getKey()));

        Map<String, Song> songMap = SongManager.getAllSongs();
        Map<String, User> userMap = UserManager.getAllUsers();

        Map<String,Double> songUPSMap = getUPS(user,songMap);
        Map<String,Double> songUFPSMap = getUFPS(user,songMap,userMap);
        List<String> userSongPlayList = user.getPlayListSongNames();

        for(String songName : songMap.keySet()){
            if(!userSongPlayList.contains(songName)) {
                Double uas = (songUPSMap.containsKey(songName) ? songUPSMap.get(songName) : 0) + (songUFPSMap.containsKey(songName) ? songUFPSMap.get(songName) : 0);
                DecimalFormat df = new DecimalFormat("#.##");
                uas = Double.parseDouble(df.format(uas));
                if (uas > 0) songsRecommended.add(new Pair(uas, songName));
            }
        }

        return songsRecommended;
    }

    private double getSimilarityIndex(Song song1, Song song2){

        int matchedFiledCount = song1.getCountMatchingFields(song2);
        double totalFieldCount = (song1.getTotalFieldCount() + song2.getTotalFieldCount())/2;

        return matchedFiledCount/totalFieldCount;
    }

    private Map<String,Double> getUPS(User user, Map<String, Song> songMap){

        Map<String,Double> songUPSMap = new HashMap<>();

        for(String songName : songMap.keySet()){
            Song song = songMap.get(songName);

            Double songUps = 0.0, sumSI = 0.0;
            List<String> userPlaylistSongNames = user.getPlayListSongNames();
            for(String playlistSongName : userPlaylistSongNames){
                Song userPlaylistSong = songMap.get(playlistSongName);

                Double si = getSimilarityIndex(song, userPlaylistSong);
                sumSI += si;
            }

            songUps = sumSI / userPlaylistSongNames.size();
            songUPSMap.put(songName, songUps);
        }

        return songUPSMap;
    }

    private Map<String,Double> getUFPS(User user, Map<String, Song> songMap, Map<String, User> userMap){

        Map<String,Double> songUFPSMap = new HashMap<>();

        Map<String,Integer> songFreq = new HashMap<>();
        List<String> userFriendNames = user.getFriendNames();
        for(String userFriendName : userFriendNames){
            User friend = userMap.get(userFriendName);
            List<String> friendPlaylistSongNames = friend.getPlayListSongNames();
            for(String songName : friendPlaylistSongNames){
                songFreq.put(songName,songFreq.getOrDefault(songName,0)+1);
            }
        }

        for(String songName : songFreq.keySet()){
            songUFPSMap.put(songName,((double)songFreq.get(songName) / userFriendNames.size()));
        }

        return songUFPSMap;
    }
}
