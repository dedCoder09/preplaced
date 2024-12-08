package org.example.manager;

import org.example.entity.Song;

import java.util.HashMap;
import java.util.Map;

public class SongManager {

    public static Map<String, Song> songMap = new HashMap<>();

    public static void addSong(Song song){
        songMap.put(song.getName(),song);
    }

    public static Song getSong(String name){
        Song song = songMap.get(name);
        return song;
    }

    public static Map<String, Song> getAllSongs(){
        return songMap;
    }
}
