package com.ochoa.arnau.swissknife.Ranking;

/**
 * Created by arnau on 05/02/2017.
 */
public class Player {
    private String username;
    private int image;
    private int score;

    public Player(int image, String username, int score) {
        this.score = score;
        this.image = image;
        this.username = username;
    }

    public int getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}
