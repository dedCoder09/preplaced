package org.example;

import org.example.entity.COMMAND;
import org.example.service.SongRecommendationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SongRecommendationService songRecommendationService = new SongRecommendationService();

        String filePath = "/Users/ritambera/Desktop/Machine-Coding/preplaced/Machine-Coding/MusicRecommendationSystem/src/main/resources/input.txt";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            COMMAND command = COMMAND.valueOf(scanner.nextLine());
            switch (command) {
                case ADD_SONG: while(true){
                    String input = scanner.nextLine();
                    if(!input.equals("END")){
                        songRecommendationService.addSong(input);
                    }else break;
                }
                songRecommendationService.updateSongRecommendation();
                break;
                case ADD_PERSON: while(true){
                    String input = scanner.nextLine();
                    if(!input.equals("END")){
                        songRecommendationService.addUser(input);
                    }else break;
                }
                songRecommendationService.updateSongRecommendation();
                break;
                case GET_ALL_SONGS: songRecommendationService.getAllSongs();
                    break;
                case GET_ALL_USERS: songRecommendationService.getAllUsers();
                    break;
                case GET_SONG_RECOMMENDATION: while(true){
                    String input = scanner.nextLine();
                    if(!input.equals("END")){
                        songRecommendationService.showSongRecommendation(input);
                    }else break;
                }
                break;
                case EXIT: return;
            }
            //System.out.println("\n");
        }
    }
}