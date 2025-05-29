package com.example.qui__z;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.button.MaterialButton;

public class SettingsActivity extends AppCompatActivity {
    private SwitchMaterial darkModeSwitch;
    private SwitchMaterial soundSwitch;
    private SwitchMaterial vibrationSwitch;
    private MaterialButton logoutButton;
    private TextView usernameTextView;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Настройки");

        initializeViews();
        loadSettings();
        setupListeners();
        setupBottomNavigation();
    }

    private void initializeViews() {
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        soundSwitch = findViewById(R.id.sound_switch);
        vibrationSwitch = findViewById(R.id.vibration_switch);
        logoutButton = findViewById(R.id.logoutButton);
        usernameTextView = findViewById(R.id.usernameTextView);
    }

    private void loadSettings() {
        // Загрузка имени пользователя
        String username = preferences.getString("username", "Гость");
        usernameTextView.setText(username);

        // Загрузка настроек
        boolean isDarkMode = preferences.getBoolean("dark_mode", false);
        boolean isSoundEnabled = preferences.getBoolean("sound_enabled", true);
        boolean isVibrationEnabled = preferences.getBoolean("vibration_enabled", true);

        darkModeSwitch.setChecked(isDarkMode);
        soundSwitch.setChecked(isSoundEnabled);
        vibrationSwitch.setChecked(isVibrationEnabled);

        // Применяем текущую тему
        applyTheme(isDarkMode);
    }

    private void setupListeners() {
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("dark_mode", isChecked).apply();
            applyTheme(isChecked);
        });

        soundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("sound_enabled", isChecked).apply();
        });

        vibrationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("vibration_enabled", isChecked).apply();
        });

        logoutButton.setOnClickListener(v -> {
            // Очищаем настройки
            preferences.edit().clear().apply();
            // Возвращаемся на главный экран
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void applyTheme(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_settings);
        
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
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 