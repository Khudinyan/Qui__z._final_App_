package com.example.qui__z;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class NameInputActivity extends AppCompatActivity {
    private TextInputEditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_input);

        // Инициализация views
        initializeViews();
        
        // Настройка обработчиков событий
        setupClickListeners();
    }

    private void initializeViews() {
        nameInput = findViewById(R.id.nameInput);
        MaterialButton startButton = findViewById(R.id.startButton);
    }

    private void setupClickListeners() {
        MaterialButton startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            // Получаем nameInput здесь, непосредственно перед использованием
            TextInputEditText currentNameInput = findViewById(R.id.nameInput);

            if (currentNameInput == null) {
                 Toast.makeText(this, "Ошибка: Поле ввода имени не найдено в ClickListener", Toast.LENGTH_SHORT).show();
                 return;
            }
            
            CharSequence text = currentNameInput.getText();
            if (text == null) {
                 Toast.makeText(this, "Ошибка: Текст из поля ввода равен null", Toast.LENGTH_SHORT).show();
                 return;
            }
            
            String rawName = text.toString();
            Toast.makeText(this, "Добро пожаловать" + rawName, Toast.LENGTH_SHORT).show(); // Временный Toast
            
            String name = rawName.trim();
            
            Toast.makeText(NameInputActivity.this, "Передача имени: " + name, Toast.LENGTH_SHORT).show(); // Временный Toast

            if (name.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите ваше имя", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (name.length() < 2) {
                Toast.makeText(this, "Имя должно содержать минимум 2 символа", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (name.length() > 20) {
                Toast.makeText(this, "Имя не должно превышать 20 символов", Toast.LENGTH_SHORT).show();
                return;
            }

            // Создаем нового пользователя и сохраняем его данные
            // User user = new User(name, 0, ""); // Закомментировано
            // user.saveScore(this); // Закомментировано

            // Переходим к главному экрану
            Intent intent = new Intent(NameInputActivity.this, MainActivity.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
            finish();
        });
    }
} 