package com.example.qui__z.model;

public class UserScore implements Comparable<UserScore> {
    private String userName;
    private int score;
    private String category;
    private long timestamp;

    public UserScore(String userName, int score, String category) {
        this.userName = userName;
        this.score = score;
        this.category = category;
        this.timestamp = System.currentTimeMillis();
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public String getCategory() {
        return category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(UserScore other) {
        return Integer.compare(other.score, this.score); // Сортировка по убыванию
    }

    public int getName() {
        return 0;
    }
}