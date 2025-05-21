package com.example.quiz

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val soundSwitch = findViewById<Switch>(R.id.soundSwitch)
        val vibrationSwitch = findViewById<Switch>(R.id.vibrationSwitch)
        val darkModeSwitch = findViewById<Switch>(R.id.darkModeSwitch)

        // Загружаем сохраненные настройки
        val prefs = getSharedPreferences("QuizSettings", MODE_PRIVATE)
        soundSwitch.isChecked = prefs.getBoolean("sound", true)
        vibrationSwitch.isChecked = prefs.getBoolean("vibration", true)
        darkModeSwitch.isChecked = prefs.getBoolean("darkMode", false)

        // Сохраняем настройки при изменении
        soundSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("sound", isChecked).apply()
            Toast.makeText(this, "Звук ${if (isChecked) "включен" else "выключен"}", Toast.LENGTH_SHORT).show()
        }

        vibrationSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("vibration", isChecked).apply()
            Toast.makeText(this, "Вибрация ${if (isChecked) "включена" else "выключена"}", Toast.LENGTH_SHORT).show()
        }

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("darkMode", isChecked).apply()
            Toast.makeText(this, "Темная тема ${if (isChecked) "включена" else "выключена"}", Toast.LENGTH_SHORT).show()
            recreate() // Перезапускаем активность для применения темы
        }
    }
} 