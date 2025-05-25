package com.example.qui__z;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.qui__z.Question;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizFragment extends Fragment {

    private static final String TAG = "QuizFragment";

    private TextView questionTextView;
    private TextView timerTextView;
    private ImageView flagImageView;
    private ProgressBar progressBar;
    private AppCompatButton option1;
    private AppCompatButton option2;
    private AppCompatButton option3;
    private AppCompatButton option4;
    private AppCompatButton nextBtn;
    private AppCompatButton backBtn;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int selectedAnswerIndex = -1;
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
        
        questions = Question.getQuestions(category);
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
        flagImageView = view.findViewById(R.id.flagImageView);
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
                String selectedAnswer = clickedButton.getText().toString();
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
            Question currentQuestion = questions.get(currentQuestionIndex);
            Log.d(TAG, "Showing question " + (currentQuestionIndex + 1) + " of " + questions.size());
            
            // Обновление UI
            progressBar.setProgress((currentQuestionIndex + 1) * 100 / questions.size());
            questionTextView.setText(currentQuestion.getQuestionText());
            
            // Установка изображения флага
            if (currentQuestion.getImageResourceId() != 0) {
                flagImageView.setVisibility(View.VISIBLE);
                flagImageView.setImageResource(currentQuestion.getImageResourceId());
            } else {
                flagImageView.setVisibility(View.GONE);
            }

            // Перемешиваем ответы
            List<String> answers = new ArrayList<>();
            answers.add(currentQuestion.getCorrectAnswer());
            answers.addAll(Arrays.asList(currentQuestion.getOptions()));
            Collections.shuffle(answers);

            option1.setText(answers.get(0));
            option2.setText(answers.get(1));
            option3.setText(answers.get(2));
            option4.setText(answers.get(3));

            // Сброс цвета кнопок
            resetButtonColors();
            
            // Включаем кликабельность кнопок
            setAnswerButtonsClickable(true);
        } else {
            Log.e(TAG, "currentQuestionIndex out of bounds: " + currentQuestionIndex);
        }
    }

    private void checkAnswer(String selectedAnswer) {
        answered = true;
        Question currentQuestion = questions.get(currentQuestionIndex);
        boolean isCorrect = selectedAnswer.equals(currentQuestion.getCorrectAnswer());

        // Подсветка правильного и неправильного ответов
        highlightAnswers(currentQuestion.getCorrectAnswer(), selectedAnswer);

        if (isCorrect) {
            score++;
        }

        // Показываем сообщение о правильности ответа
        String message = isCorrect ? "Правильно!" : "Неправильно!";
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void highlightAnswers(String correctAnswer, String selectedAnswer) {
        AppCompatButton[] buttons = {option1, option2, option3, option4};
        for (AppCompatButton button : buttons) {
            String buttonText = button.getText().toString();
            if (buttonText.equals(correctAnswer)) {
                button.setBackgroundResource(R.drawable.round_back_blue10);
                button.setTextColor(getResources().getColor(android.R.color.white));
            } else if (buttonText.equals(selectedAnswer) && !buttonText.equals(correctAnswer)) {
                button.setBackgroundResource(R.drawable.round_back_red10);
                button.setTextColor(getResources().getColor(android.R.color.white));
            }
        }
    }

    private void resetButtonColors() {
        AppCompatButton[] buttons = {option1, option2, option3, option4};
        for (AppCompatButton button : buttons) {
            button.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            button.setTextColor(getResources().getColor(R.color.purple_500));
        }
    }

    private void setAnswerButtonsClickable(boolean clickable) {
        option1.setClickable(clickable);
        option2.setClickable(clickable);
        option3.setClickable(clickable);
        option4.setClickable(clickable);
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