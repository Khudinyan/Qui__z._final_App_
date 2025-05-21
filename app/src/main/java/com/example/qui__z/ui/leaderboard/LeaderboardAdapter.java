package com.example.qui__z.ui.leaderboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qui__z.R;
import com.example.qui__z.model.UserScore;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {
    private List<UserScore> userScores;

    public LeaderboardAdapter(List<UserScore> userScores) {
        this.userScores = userScores;
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
        UserScore userScore = userScores.get(position);
        holder.rankTextView.setText(String.valueOf(position + 1));
        holder.nameTextView.setText(userScore.getName());
        holder.scoreTextView.setText(String.valueOf(userScore.getScore()));
    }

    @Override
    public int getItemCount() {
        return userScores.size();
    }

    public void updateData(List<UserScore> newUserScores) {
        this.userScores = newUserScores;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rankTextView;
        TextView nameTextView;
        TextView scoreTextView;

        ViewHolder(View view) {
            super(view);
            rankTextView = view.findViewById(R.id.rankTextView);
            nameTextView = view.findViewById(R.id.nameTextView);
            scoreTextView = view.findViewById(R.id.scoreTextView);
        }
    }
} 