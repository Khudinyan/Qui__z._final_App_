package com.example.qui__z.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.example.qui__z.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {
    private SharedPreferences preferences;
    private TextView usernameTextView;
    private SwitchMaterial darkModeSwitch;
    private MaterialButton logoutButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        preferences = requireActivity().getSharedPreferences("QuizPrefs", 0);
        
        usernameTextView = root.findViewById(R.id.usernameTextView);
        darkModeSwitch = root.findViewById(R.id.darkModeSwitch);
        logoutButton = root.findViewById(R.id.logoutButton);

        // Загрузка имени пользователя
        String username = preferences.getString("username", "");
        usernameTextView.setText(username);

        // Настройка переключателя темной темы
        boolean isDarkMode = preferences.getBoolean("dark_mode", false);
        darkModeSwitch.setChecked(isDarkMode);
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("dark_mode", isChecked).apply();
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Настройка кнопки выхода
        logoutButton.setOnClickListener(v -> {
            preferences.edit().clear().apply();
            requireActivity().finish();
        });

        return root;
    }
} 