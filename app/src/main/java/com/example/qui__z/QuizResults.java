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
import com.example.qui__z.ui.leaderboard.LeaderboardManager;

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
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String category = getIntent().getStringExtra("CATEGORY");
        String userName = getIntent().getStringExtra("USER_NAME");

        // Сохраняем результат в таблицу лидеров
        try {
            UserScore userScore = new UserScore(userName, score, category);
            LeaderboardManager.getInstance(this).addScore(userScore);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Отображаем результаты
        scoreTextView.setText(String.format("%d/%d", score, totalQuestions));
        correctAnswersTextView.setText(String.valueOf(score));
        incorrectAnswersTextView.setText(String.valueOf(totalQuestions - score));
    }

    private void setupButtons() {
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuizResults.this, QuizActivity.class);
            intent.putExtra("CATEGORY", getIntent().getStringExtra("CATEGORY"));
            intent.putExtra("USER_NAME", getIntent().getStringExtra("USER_NAME"));
            startActivity(intent);
            finish();
        });

        homeButton.setOnClickListener(v -> {
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