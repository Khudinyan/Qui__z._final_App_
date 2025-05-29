package com.example.qui__z;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qui__z.adapter.LeaderboardAdapter;
import com.example.qui__z.model.UserScore;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView leaderboardRecyclerView;
    private Spinner categorySpinner;
    private LeaderboardAdapter adapter;
    private List<UserScore> allScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        initializeViews();
        setupCategorySpinner();
        loadScores();
        setupBottomNavigation();
    }

    private void initializeViews() {
        leaderboardRecyclerView = findViewById(R.id.leaderboardRecyclerView);
        categorySpinner = findViewById(R.id.categorySpinner);
        
        leaderboardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LeaderboardAdapter(new ArrayList<>());
        leaderboardRecyclerView.setAdapter(adapter);
    }

    private void setupCategorySpinner() {
        String[] categories = {"Все категории", "Столицы", "Флаги", "Языки", "Национальности", "Планеты"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    adapter.updateScores(allScores);
                } else {
                    String selectedCategory = categories[position];
                    List<UserScore> filteredScores = new ArrayList<>();
                    for (UserScore score : allScores) {
                        if (score.getCategory().equals(selectedCategory)) {
                            filteredScores.add(score);
                        }
                    }
                    adapter.updateScores(filteredScores);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ничего не делаем, если ничего не выбрано
            }
        });
    }

    private void loadScores() {
        allScores = LeaderboardManager.getInstance(this).getScores();
        adapter.updateScores(allScores);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_leaderboard);
        
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_leaderboard) {
                return true;
            } else if (itemId == R.id.navigation_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
} 