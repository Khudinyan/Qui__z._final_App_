package com.example.qui__z;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        SwitchMaterial soundSwitch = view.findViewById(R.id.sound_switch);
        SwitchMaterial vibrationSwitch = view.findViewById(R.id.vibration_switch);

        // Загружаем сохраненные настройки
        soundSwitch.setChecked(loadSoundSetting());
        vibrationSwitch.setChecked(loadVibrationSetting());

        // Сохраняем настройки при изменении
        soundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> saveSoundSetting(isChecked));
        vibrationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> saveVibrationSetting(isChecked));

        return view;
    }

    private boolean loadSoundSetting() {
        // Здесь должна быть логика загрузки настройки звука
        return true;
    }

    private boolean loadVibrationSetting() {
        // Здесь должна быть логика загрузки настройки вибрации
        return true;
    }

    private void saveSoundSetting(boolean enabled) {
        // Здесь должна быть логика сохранения настройки звука
    }

    private void saveVibrationSetting(boolean enabled) {
        // Здесь должна быть логика сохранения настройки вибрации
    }
} 