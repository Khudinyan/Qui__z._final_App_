package com.example.qui__z;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizResults extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        int correct = getIntent().getIntExtra("correct", 0);
        int incorrect = getIntent().getIntExtra("incorrect", 0);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView resultText = findViewById(R.id.resultText);
        resultText.setText("Правильных ответов: " + correct + "\nОшибок: " + incorrect);
    }
} 