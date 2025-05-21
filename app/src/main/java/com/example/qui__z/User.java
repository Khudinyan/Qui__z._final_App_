package com.example.qui__z;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int score;
    private String category;
    private long timeSpent;
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_SCORE = "user_score";
    private static final String KEY_CATEGORY = "user_category";
    private static final String KEY_TIME = "user_time";

    public User(String name, int score, String category) {
        this.name = name;
        this.score = score;
        this.category = category;
        this.timeSpent = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public String getCategory() {
        return category;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
    }

    // Сохранение данных пользователя
    public void saveUserData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NAME, name);
        editor.putInt(KEY_SCORE, score);
        editor.putString(KEY_CATEGORY, category);
        editor.putLong(KEY_TIME, timeSpent);
        editor.apply();
    }

    // Загрузка данных пользователя
    public static User loadUserData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String name = prefs.getString(KEY_NAME, "");
        String category = prefs.getString(KEY_CATEGORY, "");
        User user = new User(name, prefs.getInt(KEY_SCORE, 0), category);
        user.timeSpent = prefs.getLong(KEY_TIME, 0);
        return user;
    }

    // Проверка, есть ли сохраненные данные
    public static boolean hasSavedData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.contains(KEY_NAME) && !prefs.getString(KEY_NAME, "").isEmpty();
    }

    public void saveScore(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("QuizScores", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        
        // Получаем существующие результаты
        String json = prefs.getString("scores", null);
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> scores = gson.fromJson(json, type);
        
        if (scores == null) {
            scores = new ArrayList<>();
        }
        
        // Добавляем новый результат
        scores.add(this);
        
        // Сохраняем обновленный список
        String updatedJson = gson.toJson(scores);
        prefs.edit().putString("scores", updatedJson).apply();
    }

    public static List<User> getScores(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("QuizScores", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("scores", null);
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> scores = gson.fromJson(json, type);
        return scores != null ? scores : new ArrayList<>();
    }
} 