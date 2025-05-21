package com.example.qui__z.ui.quiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qui__z.R;
import com.example.qui__z.model.User;
import com.example.qui__z.model.Question;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private ImageView flagImageView;
    private Button[] answerButtons;
    private ProgressBar progressBar;
    private Button nextButton;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questions;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String userName = getIntent().getStringExtra("USER_NAME");
        String category = getIntent().getStringExtra("CATEGORY");
        currentUser = new User(userName, 0, category);

        initializeViews();
        loadQuestions();
        displayQuestion();
    }

    private void initializeViews() {
        questionTextView = findViewById(R.id.questionTextView);
        flagImageView = findViewById(R.id.flagImageView);
        progressBar = findViewById(R.id.progressBar);
        nextButton = findViewById(R.id.nextButton);

        answerButtons = new Button[]{
            findViewById(R.id.answerButton1),
            findViewById(R.id.answerButton2),
            findViewById(R.id.answerButton3),
            findViewById(R.id.answerButton4)
        };

        for (int i = 0; i < answerButtons.length; i++) {
            final int index = i;
            answerButtons[i].setOnClickListener(v -> checkAnswer(index));
        }

        nextButton.setOnClickListener(v -> {
            if (currentQuestionIndex < questions.size() - 1) {
                currentQuestionIndex++;
                displayQuestion();
            } else {
                finishQuiz();
            }
        });
    }

    private void loadQuestions() {
        questions = new ArrayList<>();
        String category = currentUser.getCategory();

        switch (category) {
            case "flags":
                loadFlagQuestions();
                break;
            case "capitals":
                loadCapitalQuestions();
                break;
            case "nationalities":
                loadNationalityQuestions();
                break;
            case "languages":
                loadLanguageQuestions();
                break;
            case "planets":
                loadPlanetQuestions();
                break;
        }
    }

    private void loadFlagQuestions() {
        questions.add(new Question(
            "Флаг какой страны изображен?",
            "Австралия",
            new String[]{"Новая Зеландия", "Фиджи", "Папуа-Новая Гвинея"},
            R.drawable.flag_usa
        ));
        // Добавьте больше вопросов здесь
    }

    private void loadCapitalQuestions() {
        questions.add(new Question(
            "Столица России?",
            "Москва",
            new String[]{"Санкт-Петербург", "Новосибирск", "Екатеринбург"},
            R.drawable.capital_moscow
        ));
        // Добавьте больше вопросов здесь
    }

    private void loadNationalityQuestions() {
        questions.add(new Question(
            "Как называется житель России?",
            "Русский",
            new String[]{"Россиянин", "Российский", "Россиянец"},
            R.drawable.nationality_russian
        ));
        // Добавьте больше вопросов здесь
    }

    private void loadLanguageQuestions() {
        questions.add(new Question(
            "На каком языке говорят в Германии?",
            "Немецкий",
            new String[]{"Голландский", "Австрийский", "Швейцарский"},
            R.drawable.language_french
        ));
        // Добавьте больше вопросов здесь
    }

    private void loadPlanetQuestions() {
        questions.add(new Question(
            "Какая планета ближе всего к Солнцу?",
            "Меркурий",
            new String[]{"Венера", "Марс", "Юпитер"},
            R.drawable.planet_mercury
        ));
        // Добавьте больше вопросов здесь
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestionText());
        
        if (question.getImageResourceId() != 0) {
            flagImageView.setVisibility(ImageView.VISIBLE);
            flagImageView.setImageResource(question.getImageResourceId());
        } else {
            flagImageView.setVisibility(ImageView.GONE);
        }

        List<String> answers = new ArrayList<>(Arrays.asList(question.getOptions()));
        Collections.shuffle(answers);
        
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(answers.get(i));
            answerButtons[i].setEnabled(true);
        }

        nextButton.setEnabled(false);
        progressBar.setProgress((currentQuestionIndex + 1) * 100 / questions.size());
    }

    private void checkAnswer(int selectedIndex) {
        Question question = questions.get(currentQuestionIndex);
        String selectedAnswer = answerButtons[selectedIndex].getText().toString();
        boolean isCorrect = selectedAnswer.equals(question.getCorrectAnswer());
        
        if (isCorrect) {
            score++;
            answerButtons[selectedIndex].setBackgroundColor(getResources().getColor(R.color.correct_answer));
        } else {
            answerButtons[selectedIndex].setBackgroundColor(getResources().getColor(R.color.wrong_answer));
            for (int i = 0; i < answerButtons.length; i++) {
                if (answerButtons[i].getText().toString().equals(question.getCorrectAnswer())) {
                    answerButtons[i].setBackgroundColor(getResources().getColor(R.color.correct_answer));
                    break;
                }
            }
        }

        for (Button button : answerButtons) {
            button.setEnabled(false);
        }
        nextButton.setEnabled(true);
    }

    private void finishQuiz() {
        currentUser.setScore(score);
        // TODO: Сохранить результат в базу данных или SharedPreferences
        finish();
    }
} 