package com.example.qui__z;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.fragment.NavHostFragment;

import android.view.MenuItem;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем имя пользователя из WelcomeActivity
        userName = getIntent().getStringExtra("USER_NAME");
        if (userName == null || userName.isEmpty()) {
            Toast.makeText(this, "Ошибка: имя пользователя не указано", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Получаем NavHostFragment через FragmentContainerView
        FragmentContainerView navHostContainer = findViewById(R.id.nav_host_fragment);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            // Установка нижней навигации
            BottomNavigationView navView = findViewById(R.id.bottom_navigation);
            if (navView != null) {
                 NavigationUI.setupWithNavController(navView, navController);
            }

            // Передача имени пользователя в начальный фрагмент (CategoriesFragment)
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME", userName);
            // Установка графа навигации с аргументами
            navController.setGraph(R.navigation.nav_graph, bundle);
        } else {
             Toast.makeText(this, "Ошибка: NavHostFragment не найден", Toast.LENGTH_SHORT).show();
             finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
} 