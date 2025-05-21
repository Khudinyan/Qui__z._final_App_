package com.example.qui__z.model;

public class UserScore {
    private String name;
    private int score;
    private String category;

    public UserScore(String name, int score, String category) {
        this.name = name;
        this.score = score;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getCategory() {
        return category;
    }
} 