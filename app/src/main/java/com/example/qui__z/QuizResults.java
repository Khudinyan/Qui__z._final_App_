package com.example.qui__z;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.example.qui__z.model.UserScore;

public class QuizResults extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView correctAnswersTextView;
    private TextView incorrectAnswersTextView;
    private MaterialButton restartButton;
    private MaterialButton homeButton;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        initializeViews();
        displayResults();
        setupButtons();
        setupBottomNavigation();
    }

    private void initializeViews() {
        scoreTextView = findViewById(R.id.scoreTextView);
        correctAnswersTextView = findViewById(R.id.correctAnswersTextView);
        incorrectAnswersTextView = findViewById(R.id.incorrectAnswersTextView);
        restartButton = findViewById(R.id.restartButton);
        homeButton = findViewById(R.id.homeButton);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void displayResults() {
        int correctAnswers = getIntent().getIntExtra("correct", 0);
        int incorrectAnswers = getIntent().getIntExtra("incorrect", 0);
        int totalQuestions = correctAnswers + incorrectAnswers;
        int score = (correctAnswers * 100) / totalQuestions;

        scoreTextView.setText(score + "%");
        correctAnswersTextView.setText("Правильных ответов: " + correctAnswers);
        incorrectAnswersTextView.setText("Неправильных ответов: " + incorrectAnswers);

        // Сохраняем результат в SharedPreferences
        saveScore(score);
    }

    private void saveScore(int score) {
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String username = preferences.getString("username", "Гость");
        
        // Получаем текущий лучший результат
        int bestScore = preferences.getInt(username + "_best_score", 0);
        
        // Если текущий результат лучше, обновляем лучший результат
        if (score > bestScore) {
            preferences.edit().putInt(username + "_best_score", score).apply();
        }

        // Сохраняем результат в таблицу лидеров
        String category = getIntent().getStringExtra("CATEGORY");
        if (category != null) {
            UserScore userScore = new UserScore(username, score, category);
            LeaderboardManager.getInstance(this).addScore(userScore);
        }
    }

    private void setupButtons() {
        restartButton.setOnClickListener(v -> {
            // Возвращаемся к выбору категории
            startActivity(new Intent(QuizResults.this, CategoriesFragment.class));
            finish();
        });

        homeButton.setOnClickListener(v -> {
            // Возвращаемся на главный экран
            startActivity(new Intent(QuizResults.this, MainActivity.class));
            finish();
        });
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_leaderboard) {
                startActivity(new Intent(this, LeaderboardActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
} 