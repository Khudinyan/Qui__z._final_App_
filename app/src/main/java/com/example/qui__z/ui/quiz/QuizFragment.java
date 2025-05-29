package com.example.qui__z.ui.quiz;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.qui__z.R;
import com.example.qui__z.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizFragment extends Fragment {
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
    private String category;
    private String userName;
    private String selectedOptionByUser = "";
    private static final String TAG = "QuizFragment";

    // Callback для обработки нажатия системной кнопки "Назад"
    private OnBackPressedCallback onBackPressedCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("CATEGORY", "flags");
            userName = getArguments().getString("USER_NAME");
            
            // Показываем мотивирующую цитату
            String quote = QuizQuotes.getQuote(category);
            if (getContext() != null) {
                Toast.makeText(getContext(), quote, Toast.LENGTH_LONG).show();
            }
        }
        Log.d(TAG, "onCreate: Category is " + category);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);

        try {
            initializeViews(root);
            setupClickListeners();
            
            // Загрузка вопросов
            questions = Question.getQuestions(category);
            Log.d(TAG, "Loaded " + questions.size() + " questions for category: " + category);

            if (questions == null || questions.isEmpty()) {
                showErrorAndNavigateBack();
                return root;
            }

            // Показываем мотивирующую цитату в диалоговом окне
            if (getContext() != null) {
                String quote = QuizQuotes.getQuote(category);
                new AlertDialog.Builder(requireContext())
                    .setTitle("Мотивация")
                    .setMessage(quote)
                    .setPositiveButton("Начать!", (dialog, which) -> {
                        try {
            Collections.shuffle(questions);
                showQuestion();
                        } catch (Exception e) {
                            Log.e(TAG, "Ошибка при показе вопроса", e);
                            showErrorAndNavigateBack();
                        }
                    })
                    .setCancelable(false)
                    .show();
            }

        } catch (Exception e) {
            Log.e(TAG, "Ошибка инициализации Quiz Fragment", e);
            showErrorAndNavigateBack();
        }

        // Настраиваем обработку системной кнопки "Назад"
        onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (currentQuestionIndex == 0) {
                    showExitConfirmationDialog();
                } else {
                    currentQuestionIndex--;
                    selectedOptionByUser = "";
                    showQuestion();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);

        return root;
    }

    private void initializeViews(View root) {
        questionTextView = root.findViewById(R.id.question);
        timerTextView = root.findViewById(R.id.timer);
        flagImageView = root.findViewById(R.id.flagImageView);
        progressBar = root.findViewById(R.id.progressBar);
        option1 = root.findViewById(R.id.option1);
        option2 = root.findViewById(R.id.option2);
        option3 = root.findViewById(R.id.option3);
        option4 = root.findViewById(R.id.option4);
        nextBtn = root.findViewById(R.id.nextBtn);
        backBtn = root.findViewById(R.id.backBtn);

        // Check if views are null after findViewById (optional but good practice)
        if (questionTextView == null) Log.e(TAG, "questionTextView is null");
        if (timerTextView == null) Log.e(TAG, "timerTextView is null");
        if (flagImageView == null) Log.e(TAG, "flagImageView is null");
        if (progressBar == null) Log.e(TAG, "progressBar is null");
        if (option1 == null) Log.e(TAG, "option1 is null");
        if (option2 == null) Log.e(TAG, "option2 is null");
        if (option3 == null) Log.e(TAG, "option3 is null");
        if (option4 == null) Log.e(TAG, "option4 is null");
        if (nextBtn == null) Log.e(TAG, "nextBtn is null");
        if (backBtn == null) Log.e(TAG, "backBtn is null");
    }

    private void setupClickListeners() {
        View.OnClickListener optionClickListener = v -> {
            if (selectedOptionByUser.isEmpty()) {
                AppCompatButton clickedButton = (AppCompatButton) v;
                selectedOptionByUser = clickedButton.getText().toString();

                clickedButton.setBackgroundResource(R.drawable.round_back_red10);
                clickedButton.setTextColor(getResources().getColor(android.R.color.white));

                revealAnswer();

                checkAnswer(selectedOptionByUser);

                setAnswerButtonsClickable(false);
            }
        };

        option1.setOnClickListener(optionClickListener);
        option2.setOnClickListener(optionClickListener);
        option3.setOnClickListener(optionClickListener);
        option4.setOnClickListener(optionClickListener);

        nextBtn.setOnClickListener(v -> {
            Log.d(TAG, "Next button clicked. selectedOptionByUser: " + selectedOptionByUser);
            if (selectedOptionByUser.isEmpty()) {
                Log.d(TAG, "Answer not selected. Prompting user.");
                // TODO: Показать сообщение пользователю "Пожалуйста, сделайте выбор"
                // Пример с Toast (можно раскомментировать, если хотите использовать Toast)
                // Toast.makeText(getContext(), "Пожалуйста, сделайте выбор!", Toast.LENGTH_SHORT).show();
            } else {
                currentQuestionIndex++;
                selectedOptionByUser = "";
                Log.d(TAG, "Next question index: " + currentQuestionIndex + ", Total questions: " + questions.size());
                if (currentQuestionIndex < questions.size()) {
                    Log.d(TAG, "Showing next question.");
                    showQuestion();
                } else {
                    Log.d(TAG, "Finishing quiz.");
                    finishQuiz();
                }
            }
        });

        backBtn.setOnClickListener(v -> {
            Log.d(TAG, "Back button clicked. currentQuestionIndex: " + currentQuestionIndex);
            if (currentQuestionIndex == 0) {
                Log.d(TAG, "Showing exit confirmation dialog.");
                showExitConfirmationDialog();
            } else {
                currentQuestionIndex--;
                selectedOptionByUser = "";
                Log.d(TAG, "Showing previous question. New index: " + currentQuestionIndex);
                showQuestion();
            }
        });
    }

    private void showQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            finishQuiz();
            return;
        }

            Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion == null) {
            showErrorAndNavigateBack();
            return;
        }

        try {
            progressBar.setProgress((currentQuestionIndex + 1) * 100 / questions.size());
            questionTextView.setText(currentQuestion.getQuestionText());
            
            if (currentQuestion.getImageResourceId() != 0) {
                flagImageView.setVisibility(View.VISIBLE);
                flagImageView.setImageResource(currentQuestion.getImageResourceId());
            } else {
                flagImageView.setVisibility(View.GONE);
            }

            List<String> answers = new ArrayList<>();
            answers.add(currentQuestion.getCorrectAnswer());
            answers.addAll(Arrays.asList(currentQuestion.getOptions()));
            Collections.shuffle(answers);

            option1.setText(answers.get(0));
            option2.setText(answers.get(1));
            option3.setText(answers.get(2));
            option4.setText(answers.get(3));

            resetOptionsAppearance();
            setAnswerButtonsClickable(true);
        } catch (Exception e) {
            Log.e(TAG, "Ошибка при отображении вопроса", e);
            showErrorAndNavigateBack();
        }
    }

    private void resetOptionsAppearance() {
        int normalBackground = R.drawable.round_back_white_stroke2_10;
        int textColor = getResources().getColor(R.color.purple_500);

        option1.setBackgroundResource(normalBackground);
        option1.setTextColor(textColor);
        option2.setBackgroundResource(normalBackground);
        option2.setTextColor(textColor);
        option3.setBackgroundResource(normalBackground);
        option3.setTextColor(textColor);
        option4.setBackgroundResource(normalBackground);
        option4.setTextColor(textColor);

        selectedOptionByUser = "";
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        boolean isCorrect = selectedAnswer.equals(currentQuestion.getCorrectAnswer());

        if (isCorrect) {
            score++;
            Log.d(TAG, "Правильный ответ! Текущий счет: " + score);
            // Подсвечиваем правильный ответ зеленым
            highlightCorrectAnswer(currentQuestion.getCorrectAnswer());
        } else {
            Log.d(TAG, "Неправильный ответ. Правильный ответ: " + currentQuestion.getCorrectAnswer());
            // Подсвечиваем неправильный ответ красным и правильный зеленым
            highlightWrongAnswer(selectedAnswer, currentQuestion.getCorrectAnswer());
        }
    }

    private void highlightCorrectAnswer(String correctAnswer) {
        AppCompatButton[] buttons = {option1, option2, option3, option4};
        for (AppCompatButton button : buttons) {
            if (button.getText().toString().equals(correctAnswer)) {
                button.setBackgroundResource(R.drawable.round_back_green10);
                button.setTextColor(getResources().getColor(android.R.color.white));
            }
        }
    }

    private void highlightWrongAnswer(String selectedAnswer, String correctAnswer) {
        AppCompatButton[] buttons = {option1, option2, option3, option4};
        for (AppCompatButton button : buttons) {
            String buttonText = button.getText().toString();
            if (buttonText.equals(selectedAnswer)) {
                // Подсвечиваем выбранный неправильный ответ красным
                button.setBackgroundResource(R.drawable.round_back_red10);
                button.setTextColor(getResources().getColor(android.R.color.white));
            } else if (buttonText.equals(correctAnswer)) {
                // Подсвечиваем правильный ответ зеленым
                button.setBackgroundResource(R.drawable.round_back_green10);
                button.setTextColor(getResources().getColor(android.R.color.white));
            }
        }
    }

    private void revealAnswer() {
        final String correctAnswer = questions.get(currentQuestionIndex).getCorrectAnswer();
        int correctBackground = R.drawable.round_back_green10;
        int textColor = getResources().getColor(android.R.color.white);

        AppCompatButton[] buttons = {option1, option2, option3, option4};
        for (AppCompatButton button : buttons) {
            if (button.getText().toString().equals(correctAnswer)) {
                button.setBackgroundResource(correctBackground);
                button.setTextColor(textColor);
            }
        }
    }

    private void highlightAnswers(String correctAnswer, String selectedAnswer) {
        // Эта логика теперь не используется напрямую после выбора ответа
        // Подсветка реализована в слушателе кликов и методе revealAnswer()
    }

    private void setAnswerButtonsClickable(boolean clickable) {
         AppCompatButton[] buttons = {option1, option2, option3, option4};
         for (AppCompatButton button : buttons) {
             button.setClickable(clickable);
         }
    }

    private void finishQuiz() {
        Log.d(TAG, "Викторина завершена. Счет: " + score + "/" + questions.size());
        
        // Создаем диалог с результатами
        new AlertDialog.Builder(requireContext())
            .setTitle("Результаты викторины")
            .setMessage(String.format("Поздравляем, %s!\n\nВаш результат: %d из %d", 
                userName, score, questions.size()))
            .setPositiveButton("Завершить", (dialog, which) -> {
        Navigation.findNavController(requireView()).navigateUp();
            })
            .setCancelable(false)
            .show();
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Подтверждение выхода")
                .setMessage("Вы действительно хотите выйти из викторины?")
                .setPositiveButton("Да", (dialog, which) -> {
                    Navigation.findNavController(requireView()).navigateUp();
                })
                .setNegativeButton("Нет", null)
                .setCancelable(true)
                .show();
    }

    private void showErrorAndNavigateBack() {
        if (getContext() != null) {
            new AlertDialog.Builder(requireContext())
                .setTitle("Ошибка")
                .setMessage("Не удалось загрузить вопросы. Пожалуйста, попробуйте позже.")
                .setPositiveButton("OK", (dialog, which) -> {
                    if (getActivity() != null) {
                        getActivity().onBackPressed();
                    }
                })
                .setCancelable(false)
                .show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (onBackPressedCallback != null) {
            onBackPressedCallback.remove();
        }
    }
} 