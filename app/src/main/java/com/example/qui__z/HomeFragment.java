package com.example.qui__z;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MaterialButton startQuizButton = view.findViewById(R.id.start_quiz_button);
        startQuizButton.setOnClickListener(v -> {
            // Запускаем активность с викториной
            startActivity(new android.content.Intent(getActivity(), QuizActivity.class));
        });

        return view;
    }
} 