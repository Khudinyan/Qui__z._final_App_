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
import androidx.navigation.Navigation;
import android.widget.TextView;

public class CategoriesFragment extends Fragment {
    private String userName;
    private TextView userNameTextView;
    private TextView userScoreTextView;
    private String selectedCategory = "";
    private CardView flagsMainLayout, capitalLayout, continentsLayout;
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
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userNameTextView = view.findViewById(R.id.userName);
        userScoreTextView = view.findViewById(R.id.userScore);

        if (userName == null || userName.isEmpty()) {
            Toast.makeText(getContext(), "Ошибка: имя пользователя не указано", Toast.LENGTH_SHORT).show();
            userNameTextView.setText("Имя не указано");
        } else {
            userNameTextView.setText(userName);
            userScoreTextView.setText("Счет: 0");
        }

        initializeViews(view);
        
        if (savedInstanceState != null) {
            selectedCategory = savedInstanceState.getString(KEY_SELECTED_CATEGORY, "");
            if (!selectedCategory.isEmpty()) {
                selectCategory(selectedCategory);
            }
        }

        setupCardListeners();
        setupStartButton();
    }

    private void initializeViews(View view) {
        try {
            flagsMainLayout = view.findViewById(R.id.flagsMainLayout);
            capitalLayout = view.findViewById(R.id.capitalLayout);
            continentsLayout = view.findViewById(R.id.continentsLayout);
            startQuizBtn = view.findViewById(R.id.startQuizBtn);
            startQuizBtn.setEnabled(false);

            if (flagsMainLayout == null) throw new NullPointerException("flagsMainLayout is null in CategoriesFragment");
            if (capitalLayout == null) throw new NullPointerException("capitalLayout is null in CategoriesFragment");
            if (continentsLayout == null) throw new NullPointerException("continentsLayout is null in CategoriesFragment");
            if (startQuizBtn == null) throw new NullPointerException("startQuizBtn is null in CategoriesFragment");

        } catch (Exception e) {
            Toast.makeText(getContext(), "Ошибка инициализации интерфейса в CategoriesFragment: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void setupCardListeners() {
        flagsMainLayout.setOnClickListener(v -> selectCategory("flags"));
        capitalLayout.setOnClickListener(v -> selectCategory("capitals"));
        continentsLayout.setOnClickListener(v -> selectCategory("continents"));
    }

    private void setupStartButton() {
        startQuizBtn.setOnClickListener(v -> {
            if (!selectedCategory.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString("category", selectedCategory);
                bundle.putString("USER_NAME", userName);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_quizFragment, bundle);
            } else {
                Toast.makeText(getContext(), "Пожалуйста, выберите категорию викторины", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectCategory(String category) {
        selectedCategory = category;
        startQuizBtn.setEnabled(true);

        try {
            resetCardSelection();

            CardView selectedCard = null;
            switch (category) {
                case "flags":
                    selectedCard = flagsMainLayout;
                    break;
                case "capitals":
                    selectedCard = capitalLayout;
                    break;
                case "continents":
                    selectedCard = continentsLayout;
                    break;
            }

            if (selectedCard != null) {
                selectedCard.setCardBackgroundColor(requireContext().getResources().getColor(R.color.selected_card));
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Ошибка при выборе категории", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetCardSelection() {
        try {
            int defaultColor = requireContext().getResources().getColor(android.R.color.white);
            flagsMainLayout.setCardBackgroundColor(defaultColor);
            capitalLayout.setCardBackgroundColor(defaultColor);
            continentsLayout.setCardBackgroundColor(defaultColor);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Ошибка при сбросе выделения", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_SELECTED_CATEGORY, selectedCategory);
    }
} 