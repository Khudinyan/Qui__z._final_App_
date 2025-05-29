package com.example.qui__z;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class NameInputActivity extends AppCompatActivity {
    private TextInputEditText nameInput;
    private MaterialButton startButton;

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
        startButton = findViewById(R.id.startButton);
    }

    private void setupClickListeners() {
        startButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            
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

            Intent intent = new Intent(NameInputActivity.this, MainActivity.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
            finish();
        });
    }
} 