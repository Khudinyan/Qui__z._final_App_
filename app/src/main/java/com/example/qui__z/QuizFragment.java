package com.example.qui__z;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizFragment extends Fragment {

    private static final String TAG = "QuizFragment";

    private TextView questionTextView;
    private TextView timerTextView;
    private ProgressBar progressBar;
    private AppCompatButton option1;
    private AppCompatButton option2;
    private AppCompatButton option3;
    private AppCompatButton option4;
    private AppCompatButton nextBtn;
    private AppCompatButton backBtn;

    private List<QuestionsList> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String selectedAnswer = "";
    private boolean answered = false;
    private CountDownTimer timer;
    private static final long QUESTION_TIME_MILLIS = 2 * 60 * 1000; // 2 минуты
    private long timeLeftInMillis = QUESTION_TIME_MILLIS;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        initializeViews(view);
        setupClickListeners();
        
        // Получаем категорию из аргументов
        String category = getArguments() != null ? getArguments().getString("category", "flags") : "flags";
        Log.d(TAG, "Received category: " + category);
        
        questions = QuestionsBank.getQuestions(category);
        Log.d(TAG, "Loaded questions count: " + (questions != null ? questions.size() : 0));
        
        if (questions == null || questions.isEmpty()) {
            Log.e(TAG, "No questions loaded for category: " + category);
            Toast.makeText(getContext(), "Ошибка загрузки вопросов", Toast.LENGTH_SHORT).show();
            return view;
        }

        Collections.shuffle(questions);
        startTimer();
        showQuestion();

        return view;
    }

    private void initializeViews(View view) {
        questionTextView = view.findViewById(R.id.question);
        timerTextView = view.findViewById(R.id.timer);
        progressBar = view.findViewById(R.id.progressBar);
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);
        nextBtn = view.findViewById(R.id.nextBtn);
        backBtn = view.findViewById(R.id.backBtn);
    }

    private void setupClickListeners() {
        View.OnClickListener optionClickListener = v -> {
            if (!answered) {
                AppCompatButton clickedButton = (AppCompatButton) v;
                selectedAnswer = clickedButton.getText().toString();
                checkAnswer(selectedAnswer);
            }
        };

        option1.setOnClickListener(optionClickListener);
        option2.setOnClickListener(optionClickListener);
        option3.setOnClickListener(optionClickListener);
        option4.setOnClickListener(optionClickListener);

        nextBtn.setOnClickListener(v -> {
            if (answered) {
                currentQuestionIndex++;
                answered = false;
                if (currentQuestionIndex < questions.size()) {
                    showQuestion();
                } else {
                    finishQuiz();
                }
            }
        });

        backBtn.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                answered = false;
                showQuestion();
            }
        });
    }

    private void startTimer() {
        timer = new CountDownTimer(QUESTION_TIME_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                finishQuiz();
            }
        }.start();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }

    private void showQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuestionsList currentQuestion = questions.get(currentQuestionIndex);
            Log.d(TAG, "Showing question " + (currentQuestionIndex + 1) + " of " + questions.size());
            
            // Обновление UI
            progressBar.setProgress((currentQuestionIndex + 1) * 100 / questions.size());
            questionTextView.setText(currentQuestion.getQuestion());
            
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());

            resetOptionsAppearance();
        } else {
            Log.e(TAG, "currentQuestionIndex out of bounds: " + currentQuestionIndex);
        }
    }

    private void resetOptionsAppearance() {
        option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        
        option1.setTextColor(getResources().getColor(R.color.primary));
        option2.setTextColor(getResources().getColor(R.color.primary));
        option3.setTextColor(getResources().getColor(R.color.primary));
        option4.setTextColor(getResources().getColor(R.color.primary));
    }

    private void checkAnswer(String selectedAnswer) {
        answered = true;
        QuestionsList currentQuestion = questions.get(currentQuestionIndex);

        if (selectedAnswer.equals(currentQuestion.getAnswer())) {
            score++;
            switch (selectedAnswer) {
                case "option1":
                    option1.setBackgroundResource(R.drawable.round_back_green10);
                    break;
                case "option2":
                    option2.setBackgroundResource(R.drawable.round_back_green10);
                    break;
                case "option3":
                    option3.setBackgroundResource(R.drawable.round_back_green10);
                    break;
                case "option4":
                    option4.setBackgroundResource(R.drawable.round_back_green10);
                    break;
            }
        } else {
            switch (selectedAnswer) {
                case "option1":
                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    break;
                case "option2":
                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    break;
                case "option3":
                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    break;
                case "option4":
                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    break;
            }

            switch (currentQuestion.getAnswer()) {
                case "option1":
                    option1.setBackgroundResource(R.drawable.round_back_green10);
                    break;
                case "option2":
                    option2.setBackgroundResource(R.drawable.round_back_green10);
                    break;
                case "option3":
                    option3.setBackgroundResource(R.drawable.round_back_green10);
                    break;
                case "option4":
                    option4.setBackgroundResource(R.drawable.round_back_green10);
                    break;
            }
        }
    }

    private void finishQuiz() {
        if (timer != null) {
            timer.cancel();
        }

        // TODO: Показать диалог с результатами
        Toast.makeText(getContext(), 
            String.format("Викторина завершена!\nВаш результат: %d из %d", 
                score, questions.size()), 
            Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
} 