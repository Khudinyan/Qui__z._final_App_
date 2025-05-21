package com.example.qui__z;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {
    private List<User> scores;

    public LeaderboardAdapter(List<User> scores) {
        this.scores = scores;
    }

    public void updateScores(List<User> newScores) {
        this.scores = newScores;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_leaderboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = scores.get(position);
        holder.nameTextView.setText(user.getName());
        holder.scoreTextView.setText(String.format("%d очков", user.getScore()));
        holder.categoryTextView.setText(getCategoryTitle(user.getCategory()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    private String getCategoryTitle(String category) {
        switch (category) {
            case "flags": return "Флаги стран";
            case "capitals": return "Столицы";
            case "languages": return "Языки";
            case "nationalities": return "Национальности";
            case "planets": return "Планеты";
            default: return category;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView scoreTextView;
        TextView categoryTextView;

        ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.nameTextView);
            scoreTextView = view.findViewById(R.id.scoreTextView);
            categoryTextView = view.findViewById(R.id.categoryTextView);
        }
    }
} 