package com.example.qui__z;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView categoryTextView;
    private MaterialButton playAgainButton;
    private MaterialButton mainMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Инициализация views
        initializeViews();

        // Настройка Toolbar
        setupToolbar();

        // Получение данных из Intent
        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        String category = getIntent().getStringExtra("CATEGORY");

        // Отображение результатов
        displayResults(score, total, category);

        // Настройка кнопок
        setupButtons();
    }

    private void initializeViews() {
        scoreTextView = findViewById(R.id.scoreTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        mainMenuButton = findViewById(R.id.mainMenuButton);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Результаты");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void displayResults(int score, int total, String category) {
        // Отображение счета
        scoreTextView.setText(String.format("Ваш счет: %d из %d", score, total));

        // Отображение категории
        String categoryTitle = "";
        switch (category) {
            case "flags": categoryTitle = "Флаги стран"; break;
            case "capitals": categoryTitle = "Столицы"; break;
            case "languages": categoryTitle = "Языки"; break;
            case "nationalities": categoryTitle = "Национальности"; break;
            case "planets": categoryTitle = "Планеты"; break;
        }
        categoryTextView.setText(String.format("Категория: %s", categoryTitle));
    }

    private void setupButtons() {
        playAgainButton.setOnClickListener(v -> {
            // Возвращаемся к предыдущей активности (QuizActivity)
            finish();
        });

        mainMenuButton.setOnClickListener(v -> {
            // Возвращаемся в главное меню
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 