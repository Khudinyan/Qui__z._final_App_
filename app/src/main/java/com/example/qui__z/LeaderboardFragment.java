package com.example.qui__z;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private LeaderboardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        recyclerView = view.findViewById(R.id.leaderboard_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Загружаем данные о пользователях
        List<User> users = loadUsers();
        adapter = new LeaderboardAdapter(users);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<User> loadUsers() {
        // Здесь должна быть логика загрузки пользователей из базы данных
        // Пока возвращаем тестовые данные
        List<User> users = new ArrayList<>();
        users.add(new User("Иван", 100, "flags"));
        users.add(new User("Мария", 90, "capitals"));
        users.add(new User("Алексей", 80, "languages"));
        return users;
    }
} 