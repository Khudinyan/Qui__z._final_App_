package com.example.qui__z;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private String selectedTopic = "";
    private String selectedLevel = "";
    private String playerName = "";
    private CardView flagsCard, capitalsCard, nationalitiesCard, languagesCard, planetsCard;
    private MaterialButton startQuizBtn;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerName = getIntent().getStringExtra("USER_NAME");
        if (playerName == null || playerName.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите ваше имя", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, NameInputActivity.class));
            finish();
            return;
        }

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        flagsCard = findViewById(R.id.flagsCard);
        capitalsCard = findViewById(R.id.capitalsCard);
        nationalitiesCard = findViewById(R.id.nationalitiesCard);
        languagesCard = findViewById(R.id.languagesCard);
        planetsCard = findViewById(R.id.planetsCard);
        startQuizBtn = findViewById(R.id.start_quiz_button);
    }

    private void setupClickListeners() {
        flagsCard.setOnClickListener(v -> selectCategory("flags"));
        capitalsCard.setOnClickListener(v -> selectCategory("capital"));
        nationalitiesCard.setOnClickListener(v -> selectCategory("nationality"));
        languagesCard.setOnClickListener(v -> selectCategory("language"));
        planetsCard.setOnClickListener(v -> selectCategory("planets"));

        startQuizBtn.setOnClickListener(v -> {
            if (!selectedTopic.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("CATEGORY", selectedTopic);
                intent.putExtra("USER_NAME", playerName);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Пожалуйста, выберите категорию", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectCategory(String category) {
        selectedTopic = category;
        Toast.makeText(this, "Выбрана категория: " + category, Toast.LENGTH_SHORT).show();
    }
}