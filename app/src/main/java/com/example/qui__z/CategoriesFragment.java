package com.example.qui__z;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import android.widget.TextView;

public class CategoriesFragment extends Fragment {
    private String userName;
    private TextView userNameTextView;
    private TextView userScoreTextView;
    private String selectedCategory = "";
    private CardView flagsMainLayout, capitalLayout;
    private MaterialButton startQuizBtn;
    private static final String KEY_SELECTED_CATEGORY = "selected_category";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString("USER_NAME");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        
        // Инициализация views
        flagsMainLayout = view.findViewById(R.id.flagsCard);
        capitalLayout = view.findViewById(R.id.capitalsCard);
        startQuizBtn = view.findViewById(R.id.start_quiz_button);
        userNameTextView = view.findViewById(R.id.nameInput);
        userScoreTextView = view.findViewById(R.id.scoreText);

        // Установка имени пользователя
        if (userName != null) {
            userNameTextView.setText("Привет, " + userName + "!");
        }

        setupCardListeners();
        setupStartButton();

        return view;
    }

    private void setupCardListeners() {
        flagsMainLayout.setOnClickListener(v -> selectCategory("flags"));
        capitalLayout.setOnClickListener(v -> selectCategory("capital"));
        View languageLayout = getView().findViewById(R.id.languageLayout);
        if (languageLayout != null) languageLayout.setOnClickListener(v -> selectCategory("language"));

        View nationalityLayout = getView().findViewById(R.id.nationalityLayout);
        if (nationalityLayout != null) nationalityLayout.setOnClickListener(v -> selectCategory("nationality"));

        View planetsLayout = getView().findViewById(R.id.planetsLayout);
        if (planetsLayout != null) planetsLayout.setOnClickListener(v -> selectCategory("planets"));
    }

    private void selectCategory(String category) {
        selectedCategory = category;
        // Можно добавить визуальную индикацию выбранной категории
        Toast.makeText(getContext(), "Выбрана категория: " + category, Toast.LENGTH_SHORT).show();
    }

    private void setupStartButton() {
        startQuizBtn.setOnClickListener(v -> {
            if (!selectedCategory.isEmpty()) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra("CATEGORY", selectedCategory);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Пожалуйста, выберите категорию викторины", Toast.LENGTH_SHORT).show();
            }
        });
    }
} 