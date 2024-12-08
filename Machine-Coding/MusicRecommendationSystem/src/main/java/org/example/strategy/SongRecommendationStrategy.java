package org.example.strategy;

import org.example.entity.User;
import org.example.helper.Pair;

import java.util.PriorityQueue;
import java.util.Queue;

public interface SongRecommendationStrategy {

    public PriorityQueue<Pair<Double, String>> getRecommendedSongs(User user);
}
