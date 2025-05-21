package com.example.qui__z;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView progressTextView;
    private TextView scoreTextView;
    private TextView questionTextView;
    private TextView timerTextView;
    private ImageView flagImageView;
    private MaterialButton option1Button;
    private MaterialButton option2Button;
    private MaterialButton option3Button;
    private MaterialButton option4Button;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String category;
    private String userName;
    private CountDownTimer timer;
    private long timeSpent = 0;
    private boolean answered = false;
    private User currentUser;
    private static final long QUESTION_TIME_MILLIS = 2 * 60 * 1000; // 2 минуты
    private long timeLeftInMillis = QUESTION_TIME_MILLIS;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Инициализация views
        initializeViews();
        
        // Получение данных из Intent
        category = getIntent().getStringExtra("CATEGORY");
        userName = getIntent().getStringExtra("USER_NAME");

        // Загружаем или создаем пользователя
        if (User.hasSavedData(this)) {
            currentUser = User.loadUserData(this);
        } else {
            currentUser = new User(userName, 0, category);
        }

        // Загрузка вопросов
        loadQuestions();

        // Запуск таймера
        startTimer();

        // Настройка первого вопроса
        showQuestion();

        // Настройка обработчиков нажатий
        setupClickListeners();
    }

    private void initializeViews() {
        progressTextView = findViewById(R.id.progressTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        timerTextView = findViewById(R.id.timerTextView);
        flagImageView = findViewById(R.id.flagImageView);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        option4Button = findViewById(R.id.option4Button);
        progressBar = findViewById(R.id.progressBar);
    }

    private void loadQuestions() {
        questions = new ArrayList<>();
        
        switch (category) {
            case "flags":
                // Вопросы про флаги
                questions.add(new Question("Флаг какой страны изображен?", "Россия", new String[]{"Украина", "Беларусь", "Казахстан"}, R.drawable.flag_russia));
                questions.add(new Question("Флаг какой страны изображен?", "Франция", new String[]{"Италия", "Испания", "Португалия"}, R.drawable.flag_france));
                questions.add(new Question("Флаг какой страны изображен?", "Германия", new String[]{"Австрия", "Швейцария", "Бельгия"}, R.drawable.flag_germany));
                questions.add(new Question("Флаг какой страны изображен?", "Япония", new String[]{"Китай", "Корея", "Вьетнам"}, R.drawable.flag_japan));
                questions.add(new Question("Флаг какой страны изображен?", "США", new String[]{"Канада", "Мексика", "Бразилия"}, R.drawable.flag_usa));
                questions.add(new Question("Флаг какой страны изображен?", "Великобритания", new String[]{"Ирландия", "Шотландия", "Уэльс"}, R.drawable.flag_uk));
                questions.add(new Question("Флаг какой страны изображен?", "Италия", new String[]{"Испания", "Португалия", "Греция"}, R.drawable.flag_italy));
                questions.add(new Question("Флаг какой страны изображен?", "Китай", new String[]{"Япония", "Корея", "Вьетнам"}, R.drawable.flag_china));
                questions.add(new Question("Флаг какой страны изображен?", "Бразилия", new String[]{"Аргентина", "Чили", "Колумбия"}, R.drawable.flag_brazil));
                questions.add(new Question("Флаг какой страны изображен?", "Австралия", new String[]{"Новая Зеландия", "Фиджи", "Папуа-Новая Гвинея"}, R.drawable.flag_australia));
                break;

            case "capitals":
                // Вопросы про столицы
                questions.add(new Question("Столица России?", "Москва", new String[]{"Санкт-Петербург", "Новосибирск", "Екатеринбург"}, 0));
                questions.add(new Question("Столица Франции?", "Париж", new String[]{"Лион", "Марсель", "Бордо"}, 0));
                questions.add(new Question("Столица Германии?", "Берлин", new String[]{"Мюнхен", "Гамбург", "Франкфурт"}, 0));
                questions.add(new Question("Столица Японии?", "Токио", new String[]{"Осака", "Киото", "Нагоя"}, 0));
                questions.add(new Question("Столица США?", "Вашингтон", new String[]{"Нью-Йорк", "Лос-Анджелес", "Чикаго"}, 0));
                questions.add(new Question("Столица Великобритании?", "Лондон", new String[]{"Манчестер", "Ливерпуль", "Бирмингем"}, 0));
                questions.add(new Question("Столица Италии?", "Рим", new String[]{"Милан", "Неаполь", "Турин"}, 0));
                questions.add(new Question("Столица Китая?", "Пекин", new String[]{"Шанхай", "Гуанчжоу", "Шэньчжэнь"}, 0));
                questions.add(new Question("Столица Бразилии?", "Бразилиа", new String[]{"Рио-де-Жанейро", "Сан-Паулу", "Сальвадор"}, 0));
                questions.add(new Question("Столица Австралии?", "Канберра", new String[]{"Сидней", "Мельбурн", "Брисбен"}, 0));
                break;

            case "languages":
                // Вопросы про языки
                questions.add(new Question("Официальный язык России?", "Русский", new String[]{"Украинский", "Белорусский", "Казахский"}, 0));
                questions.add(new Question("Официальный язык Франции?", "Французский", new String[]{"Испанский", "Итальянский", "Португальский"}, 0));
                questions.add(new Question("Официальный язык Германии?", "Немецкий", new String[]{"Австрийский", "Швейцарский", "Голландский"}, 0));
                questions.add(new Question("Официальный язык Японии?", "Японский", new String[]{"Китайский", "Корейский", "Вьетнамский"}, 0));
                questions.add(new Question("Официальный язык США?", "Английский", new String[]{"Испанский", "Французский", "Немецкий"}, 0));
                questions.add(new Question("Официальный язык Великобритании?", "Английский", new String[]{"Шотландский", "Валлийский", "Ирландский"}, 0));
                questions.add(new Question("Официальный язык Италии?", "Итальянский", new String[]{"Испанский", "Португальский", "Французский"}, 0));
                questions.add(new Question("Официальный язык Китая?", "Китайский", new String[]{"Японский", "Корейский", "Вьетнамский"}, 0));
                questions.add(new Question("Официальный язык Бразилии?", "Португальский", new String[]{"Испанский", "Французский", "Итальянский"}, 0));
                questions.add(new Question("Официальный язык Австралии?", "Английский", new String[]{"Австралийский", "Аборигенский", "Маори"}, 0));
                break;

            case "nationalities":
                // Вопросы про национальности
                questions.add(new Question("Житель России?", "Русский", new String[]{"Украинец", "Белорус", "Казах"}, 0));
                questions.add(new Question("Житель Франции?", "Француз", new String[]{"Испанец", "Итальянец", "Португалец"}, 0));
                questions.add(new Question("Житель Германии?", "Немец", new String[]{"Австриец", "Швейцарец", "Голландец"}, 0));
                questions.add(new Question("Житель Японии?", "Японец", new String[]{"Китаец", "Кореец", "Вьетнамец"}, 0));
                questions.add(new Question("Житель США?", "Американец", new String[]{"Канадец", "Мексиканец", "Бразилец"}, 0));
                questions.add(new Question("Житель Великобритании?", "Британец", new String[]{"Шотландец", "Валлиец", "Ирландец"}, 0));
                questions.add(new Question("Житель Италии?", "Итальянец", new String[]{"Испанец", "Португалец", "Француз"}, 0));
                questions.add(new Question("Житель Китая?", "Китаец", new String[]{"Японец", "Кореец", "Вьетнамец"}, 0));
                questions.add(new Question("Житель Бразилии?", "Бразилец", new String[]{"Аргентинец", "Чилийец", "Колумбиец"}, 0));
                questions.add(new Question("Житель Австралии?", "Австралиец", new String[]{"Новозеландец", "Фиджиец", "Папуас"}, 0));
                break;

            case "planets":
                // Вопросы про планеты
                questions.add(new Question("Самая большая планета Солнечной системы?", "Юпитер", new String[]{"Сатурн", "Нептун", "Уран"}, 0));
                questions.add(new Question("Ближайшая к Солнцу планета?", "Меркурий", new String[]{"Венера", "Марс", "Земля"}, 0));
                questions.add(new Question("Планета с кольцами?", "Сатурн", new String[]{"Юпитер", "Уран", "Нептун"}, 0));
                questions.add(new Question("Красная планета?", "Марс", new String[]{"Венера", "Меркурий", "Юпитер"}, 0));
                questions.add(new Question("Планета с самым большим количеством спутников?", "Сатурн", new String[]{"Юпитер", "Уран", "Нептун"}, 0));
                questions.add(new Question("Самая горячая планета?", "Венера", new String[]{"Меркурий", "Марс", "Юпитер"}, 0));
                questions.add(new Question("Планета с самым длинным днем?", "Венера", new String[]{"Меркурий", "Марс", "Юпитер"}, 0));
                questions.add(new Question("Планета с самым коротким годом?", "Меркурий", new String[]{"Венера", "Марс", "Земля"}, 0));
                questions.add(new Question("Планета с самым длинным годом?", "Нептун", new String[]{"Уран", "Сатурн", "Юпитер"}, 0));
                questions.add(new Question("Планета с самым большим перепадом температур?", "Меркурий", new String[]{"Венера", "Марс", "Юпитер"}, 0));
                break;
        }

        // Перемешиваем вопросы
        Collections.shuffle(questions);
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
            
            // Обновление UI
            progressTextView.setText(String.format("Вопрос %d из %d", 
                currentQuestionIndex + 1, questions.size()));
            progressBar.setProgress((currentQuestionIndex + 1) * 100 / questions.size());
            questionTextView.setText("Угадайте страну по флагу");
            
            // Установка изображения флага
            flagImageView.setVisibility(View.VISIBLE);
            flagImageView.setImageResource(currentQuestion.getImageResourceId());

            // Перемешиваем ответы
            List<String> answers = new ArrayList<>(Arrays.asList(currentQuestion.getOptions()));
            Collections.shuffle(answers);
            
            option1Button.setText(answers.get(0));
            option2Button.setText(answers.get(1));
            option3Button.setText(answers.get(2));
            option4Button.setText(answers.get(3));

            // Сброс цвета кнопок
            resetButtonColors();
        } else {
            finishQuiz();
        }
    }

    private void setupClickListeners() {
        View.OnClickListener optionClickListener = v -> {
            if (!answered) {
                MaterialButton clickedButton = (MaterialButton) v;
                String selectedAnswer = clickedButton.getText().toString();
                checkAnswer(selectedAnswer);
            }
        };

        option1Button.setOnClickListener(optionClickListener);
        option2Button.setOnClickListener(optionClickListener);
        option3Button.setOnClickListener(optionClickListener);
        option4Button.setOnClickListener(optionClickListener);
    }

    private void checkAnswer(String selectedAnswer) {
        answered = true;
        Question currentQuestion = questions.get(currentQuestionIndex);
        boolean isCorrect = selectedAnswer.equals(currentQuestion.getCorrectAnswer());

        // Подсветка правильного и неправильного ответов
        highlightAnswers(currentQuestion.getCorrectAnswer(), selectedAnswer);

        if (isCorrect) {
            score++;
            scoreTextView.setText(String.format("Счет: %d", score));
            currentUser.addScore(10);
        }

        // Задержка перед следующим вопросом
        new android.os.Handler().postDelayed(() -> {
            currentQuestionIndex++;
            answered = false;
            showQuestion();
        }, 1000);
    }

    private void highlightAnswers(String correctAnswer, String selectedAnswer) {
        MaterialButton[] buttons = {option1Button, option2Button, option3Button, option4Button};
        
        for (MaterialButton button : buttons) {
            String buttonText = button.getText().toString();
            if (buttonText.equals(correctAnswer)) {
                button.setBackgroundTintList(getColorStateList(R.color.correct_answer));
            } else if (buttonText.equals(selectedAnswer) && !selectedAnswer.equals(correctAnswer)) {
                button.setBackgroundTintList(getColorStateList(R.color.wrong_answer));
            }
        }
    }

    private void resetButtonColors() {
        MaterialButton[] buttons = {option1Button, option2Button, option3Button, option4Button};
        for (MaterialButton button : buttons) {
            button.setBackgroundTintList(getColorStateList(R.color.primary));
        }
    }

    private void finishQuiz() {
        if (timer != null) {
            timer.cancel();
        }

        // Сохранение результатов
        currentUser.setTimeSpent(QUESTION_TIME_MILLIS - timeLeftInMillis);
        currentUser.saveUserData(this);

        // Показываем диалог с результатами
        new AlertDialog.Builder(this)
                .setTitle("Викторина завершена!")
                .setMessage("Ваш результат: " + score + " очков\n" +
                        "Правильных ответов: " + score + " из " + questions.size())
                .setPositiveButton("Таблица лидеров", (dialog, which) -> {
                    Intent intent = new Intent(QuizActivity.this, LeaderboardActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Выход", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quiz_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_exit) {
            showExitConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Выход из викторины")
                .setMessage("Вы уверены, что хотите выйти из викторины? Ваш прогресс будет потерян.")
                .setPositiveButton("Да", (dialog, which) -> finish())
                .setNegativeButton("Нет", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        showExitConfirmationDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
} 