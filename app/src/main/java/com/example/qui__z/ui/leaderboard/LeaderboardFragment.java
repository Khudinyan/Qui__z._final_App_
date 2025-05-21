package com.example.qui__z.ui.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qui__z.R;
import com.example.qui__z.model.UserScore;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private LeaderboardAdapter adapter;
    private List<UserScore> userScores;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        // Инициализация RecyclerView
        recyclerView = root.findViewById(R.id.leaderboard_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);

        // Инициализация списка и адаптера
        userScores = new ArrayList<>();
        adapter = new LeaderboardAdapter(userScores);
        recyclerView.setAdapter(adapter);

        // Загрузка данных
        loadUsers();

        return root;
    }

    private void loadUsers() {
        // TODO: Загрузить данные из базы данных или SharedPreferences
        // Пример данных
        userScores.clear();
        userScores.add(new UserScore("Иван", 100, "flags"));
        userScores.add(new UserScore("Мария", 90, "capitals"));
        userScores.add(new UserScore("Алексей", 80, "languages"));
        adapter.updateData(userScores);
    }
} 