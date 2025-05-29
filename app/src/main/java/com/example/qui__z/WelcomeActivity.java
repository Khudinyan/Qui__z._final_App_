package com.example.qui__z;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY = 2000; // 2 секунды

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView loadingText = findViewById(R.id.loadingText);
        loadingText.setText("Загрузка...");

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this, NameInputActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
} 