package com.example.qui__z;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qui__z.model.UserScore;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private TextView timerTextView;
    private ProgressBar progressBar;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private Button nextBtn;
    private Button backBtn;
    private BottomNavigationView bottomNavigationView;
    private ImageView flagImageView;

    private List<QuestionsList> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String selectedAnswer = "";
    private boolean answered = false;
    private CountDownTimer timer;
    private static final long QUESTION_TIME_MILLIS = 2 * 60 * 1000; // 2 минуты
    private long timeLeftInMillis = QUESTION_TIME_MILLIS;
    private String category;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        category = getIntent().getStringExtra("CATEGORY");
        userName = getIntent().getStringExtra("USER_NAME");

        initializeViews();
        setupClickListeners();
        setupBottomNavigation();

        questions = QuestionsBank.getQuestions(category);
        Collections.shuffle(questions);

        startTimer();
        showQuestion();
    }

    private void initializeViews() {
        questionTextView = findViewById(R.id.question);
        timerTextView = findViewById(R.id.timer);
        progressBar = findViewById(R.id.progressBar);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);
        backBtn = findViewById(R.id.backBtn);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        flagImageView = findViewById(R.id.flagImageView);
    }

    private void setupClickListeners() {
        View.OnClickListener optionClickListener = v -> {
            if (!answered) {
                Button clickedButton = (Button) v;
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

    private void setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_leaderboard) {
                startActivity(new Intent(this, LeaderboardActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                finish();
                return true;
            }
            return false;
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

            progressBar.setProgress((currentQuestionIndex + 1) * 100 / questions.size());
            questionTextView.setText(currentQuestion.getQuestion());

            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());

            // Показываем флаг только для категории "flags"
            if (category.equals("flags")) {
                flagImageView.setVisibility(View.VISIBLE);
                String countryName = currentQuestion.getAnswer().toLowerCase();
                // Преобразуем название страны в формат имени ресурса
                String flagResourceName = "flag_" + countryName;
                int flagResource = getResources().getIdentifier(
                    flagResourceName,
                    "drawable",
                    getPackageName()
                );
                if (flagResource != 0) {
                    flagImageView.setImageResource(flagResource);
                } else {
                    // Если флаг не найден, скрываем ImageView
                    flagImageView.setVisibility(View.GONE);
                }
            } else {
                flagImageView.setVisibility(View.GONE);
            }

            resetOptionsAppearance();
        } else {
            finishQuiz();
        }
    }

    private void resetOptionsAppearance() {
        option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);

        option1.setTextColor(getResources().getColor(R.color.white));
        option2.setTextColor(getResources().getColor(R.color.white));
        option3.setTextColor(getResources().getColor(R.color.white));
        option4.setTextColor(getResources().getColor(R.color.white));
    }

    private void checkAnswer(String selectedAnswer) {
        QuestionsList currentQuestion = questions.get(currentQuestionIndex);
        answered = true;

        if (selectedAnswer.equals(currentQuestion.getAnswer())) {
            score++;
            if (selectedAnswer.equals(currentQuestion.getOption1())) {
                option1.setBackgroundResource(R.drawable.round_back_green10);
            } else if (selectedAnswer.equals(currentQuestion.getOption2())) {
                option2.setBackgroundResource(R.drawable.round_back_green10);
            } else if (selectedAnswer.equals(currentQuestion.getOption3())) {
                option3.setBackgroundResource(R.drawable.round_back_green10);
            } else if (selectedAnswer.equals(currentQuestion.getOption4())) {
                option4.setBackgroundResource(R.drawable.round_back_green10);
            }
        } else {
            if (selectedAnswer.equals(currentQuestion.getOption1())) {
                option1.setBackgroundResource(R.drawable.round_back_red10);
            } else if (selectedAnswer.equals(currentQuestion.getOption2())) {
                option2.setBackgroundResource(R.drawable.round_back_red10);
            } else if (selectedAnswer.equals(currentQuestion.getOption3())) {
                option3.setBackgroundResource(R.drawable.round_back_red10);
            } else if (selectedAnswer.equals(currentQuestion.getOption4())) {
                option4.setBackgroundResource(R.drawable.round_back_red10);
            }

            if (currentQuestion.getAnswer().equals(currentQuestion.getOption1())) {
                option1.setBackgroundResource(R.drawable.round_back_green10);
            } else if (currentQuestion.getAnswer().equals(currentQuestion.getOption2())) {
                option2.setBackgroundResource(R.drawable.round_back_green10);
            } else if (currentQuestion.getAnswer().equals(currentQuestion.getOption3())) {
                option3.setBackgroundResource(R.drawable.round_back_green10);
            } else if (currentQuestion.getAnswer().equals(currentQuestion.getOption4())) {
                option4.setBackgroundResource(R.drawable.round_back_green10);
            }
        }
    }

    private void finishQuiz() {
        if (timer != null) {
            timer.cancel();
        }

        Intent intent = new Intent(QuizActivity.this, QuizResults.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.size());
        intent.putExtra("CATEGORY", category);
        intent.putExtra("USER_NAME", userName);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        if (currentQuestionIndex > 0) {
            // Если это не первый вопрос, показываем диалог подтверждения
            new AlertDialog.Builder(this)
                .setTitle("Выход из викторины")
                .setMessage("Вы уверены, что хотите выйти из викторины? Весь прогресс будет потерян.")
                .setPositiveButton("Да", (dialog, which) -> {
                    finish();
                })
                .setNegativeButton("Нет", null)
                .show();
        } else {
            super.onBackPressed();
        }
    }
}