package org.example.entity;

import java.lang.reflect.Field;

public class Song {
    private String name;
    private String singer;
    private String genre;
    private Integer tempo;

    private Song(SongBuilder songBuilder){
        this.name = songBuilder.name;
        this.singer = songBuilder.singer;;
        this.genre = songBuilder.genre;
        this.tempo = songBuilder.tempo;
    }

    public String getName() {
        return name;
    }

    public String getSinger() {
        return singer;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getTempo() {
        return tempo;
    }

    public static class SongBuilder{
        private String name;
        private String singer;
        private String genre;
        private Integer tempo;

        public SongBuilder(String name){
            this.name = name;
        }

        public SongBuilder setSinger(String singer) {
            this.singer = singer;
            return this;
        }

        public SongBuilder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public SongBuilder setTempo(Integer tempo) {
            this.tempo = tempo;
            return this;
        }

        public Song build(){
            return new Song(this);
        }
    }

    public int getTotalFieldCount(){
        int totalFieldCount = 0;

        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                // Ensure private fields are accessible
                if("name".equals(field.getName())){
                    continue;
                }
                field.setAccessible(true);

                Object thisValue = field.get(this);

                // Compare the field values
                if (thisValue != null) {
                    totalFieldCount++;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return totalFieldCount;
    }

    public int getCountMatchingFields(Song otherSong) {
        if (otherSong == null) {
            return 0;
        }

        int matchCount = 0;

        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                // Ensure private fields are accessible
                if("name".equals(field.getName())){
                    continue;
                }
                field.setAccessible(true);

                Object thisValue = field.get(this);
                Object otherValue = field.get(otherSong);

                // Compare the field values
                if (thisValue != null && thisValue.equals(otherValue)) {
                    matchCount++;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return matchCount;
    }
}
