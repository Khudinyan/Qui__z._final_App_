package com.example.qui__z.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.qui__z.R;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        MaterialCardView flagsCard = root.findViewById(R.id.flagsCard);
        MaterialCardView capitalsCard = root.findViewById(R.id.capitalsCard);
        MaterialCardView nationalitiesCard = root.findViewById(R.id.nationalitiesCard);
        MaterialCardView languagesCard = root.findViewById(R.id.languagesCard);
        MaterialCardView planetsCard = root.findViewById(R.id.planetsCard);

        flagsCard.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("category", "flags");
            Navigation.findNavController(v).navigate(R.id.action_home_to_quiz, args);
        });

        capitalsCard.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("category", "capitals");
            Navigation.findNavController(v).navigate(R.id.action_home_to_quiz, args);
        });

        nationalitiesCard.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("category", "nationalities");
            Navigation.findNavController(v).navigate(R.id.action_home_to_quiz, args);
        });

        languagesCard.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("category", "languages");
            Navigation.findNavController(v).navigate(R.id.action_home_to_quiz, args);
        });

        planetsCard.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("category", "planets");
            Navigation.findNavController(v).navigate(R.id.action_home_to_quiz, args);
        });

        return root;
    }
} 