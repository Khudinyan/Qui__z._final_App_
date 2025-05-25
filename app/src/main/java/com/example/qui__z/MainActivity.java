package com.example.qui__z;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.fragment.NavHostFragment;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private String selectedTopic = "";
    private String selectedLevel="";
    private String playerName = "";
    private CardView capitalLayout, flagsLayout, continentsLayout;
    private Button startQuizBtn;

    private NavController navController;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerName = getIntent().getStringExtra("USER_NAME");
        Log.d(TAG, "Attempting to get USER_NAME from Intent. Received: " + playerName);

        if (playerName == null || playerName.isEmpty()) {
            Log.w(TAG, "USER_NAME not provided in Intent. Using default name.");
            playerName = "ТестовыйИгрок";
        }

        Log.d(TAG, "Using playerName: " + playerName);

        FragmentContainerView navHostContainer = findViewById(R.id.nav_host_fragment);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            BottomNavigationView navView = findViewById(R.id.bottom_navigation);
            if (navView != null) {
                NavigationUI.setupWithNavController(navView, navController);
            }

            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME", playerName);
            Log.d(TAG, "Setting nav graph with USER_NAME: " + playerName);
            navController.setGraph(R.navigation.nav_graph, bundle);
        } else {
            Log.e(TAG, "Ошибка: NavHostFragment не найден");
            Toast.makeText(this, "Ошибка: NavHostFragment не найден", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void restartGame() {
        SharedPreferences prefs = getSharedPreferences("quiz_app", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("playerName", "");
        editor.putString("selectedTopic", "");
        editor.apply();

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkPlayerNameAndStart() {
        SharedPreferences prefs = getSharedPreferences("quiz_app", MODE_PRIVATE);
        String storedPlayerName = prefs.getString("playerName", null);

        if (storedPlayerName == null || storedPlayerName.isEmpty()) {
            Toast.makeText(MainActivity.this, "Выберите викторину и начинайте заново", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("selectedTopic", "");
            editor.apply();

            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Здесь может быть логика старта викторины с сохраненным именем
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController currentNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, currentNavController) || super.onOptionsItemSelected(item);
    }
}