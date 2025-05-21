package com.example.qui__z.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.qui__z.R;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizFragment extends Fragment {
    private TextView questionTextView;
    private MaterialCardView[] answerCards;
    private TextView[] answerTextViews;
    private Button nextButton;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questions;
    private String category;
    private String userName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("category");
            String userName = getArguments().getString("USER_NAME");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);

        try {
            questionTextView = root.findViewById(R.id.questionTextView);
             if (questionTextView == null) throw new NullPointerException("questionTextView is null");
            answerCards = new MaterialCardView[]{
                root.findViewById(R.id.answerCard1),
                root.findViewById(R.id.answerCard2),
                root.findViewById(R.id.answerCard3),
                root.findViewById(R.id.answerCard4)
            };
             if (answerCards[0] == null) throw new NullPointerException("answerCard1 is null");
             if (answerCards[1] == null) throw new NullPointerException("answerCard2 is null");
             if (answerCards[2] == null) throw new NullPointerException("answerCard3 is null");
             if (answerCards[3] == null) throw new NullPointerException("answerCard4 is null");
            answerTextViews = new TextView[]{
                root.findViewById(R.id.answerTextView1),
                root.findViewById(R.id.answerTextView2),
                root.findViewById(R.id.answerTextView3),
                root.findViewById(R.id.answerTextView4)
            };
            if (answerTextViews[0] == null) throw new NullPointerException("answerTextView1 is null");
            if (answerTextViews[1] == null) throw new NullPointerException("answerTextView2 is null");
            if (answerTextViews[2] == null) throw new NullPointerException("answerTextView3 is null");
            if (answerTextViews[3] == null) throw new NullPointerException("answerTextView4 is null");
            nextButton = root.findViewById(R.id.nextButton);
            if (nextButton == null) throw new NullPointerException("nextButton is null");

            loadQuestions();
            displayQuestion();

            for (int i = 0; i < answerCards.length; i++) {
                final int index = i;
                answerCards[i].setOnClickListener(v -> checkAnswer(index));
            }

            nextButton.setOnClickListener(v -> {
                if (currentQuestionIndex < questions.size() - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    // TODO: Показать результаты и вернуться на главный экран
                    Navigation.findNavController(v).navigateUp();
                }
            });
        } catch (Exception e) {
             Toast.makeText(getContext(), "Ошибка инициализации Quiz Fragment: " + e.getMessage(), Toast.LENGTH_LONG).show();
             // requireActivity().finish(); // Закомментируем пока, чтобы увидеть тост
        }

        return root;
    }

    private void loadQuestions() {
        questions = new ArrayList<>();
        // TODO: Загрузить вопросы в зависимости от категории
        // Временные данные для примера
        questions.add(new Question("Вопрос 1", new String[]{"Ответ 1", "Ответ 2", "Ответ 3", "Ответ 4"}, 0));
        questions.add(new Question("Вопрос 2", new String[]{"Ответ 1", "Ответ 2", "Ответ 3", "Ответ 4"}, 1));
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestionText());
        
        List<String> answers = new ArrayList<>();
        for (String answer : question.getAnswers()) {
            answers.add(answer);
        }
        Collections.shuffle(answers);
        
        for (int i = 0; i < answerTextViews.length; i++) {
            answerTextViews[i].setText(answers.get(i));
            answerCards[i].setCardBackgroundColor(getResources().getColor(R.color.white));
        }

        nextButton.setEnabled(false);
    }

    private void checkAnswer(int selectedIndex) {
        Question question = questions.get(currentQuestionIndex);
        boolean isCorrect = selectedIndex == question.getCorrectAnswerIndex();
        
        if (isCorrect) {
            score++;
            answerCards[selectedIndex].setCardBackgroundColor(getResources().getColor(R.color.correct_answer));
        } else {
            answerCards[selectedIndex].setCardBackgroundColor(getResources().getColor(R.color.wrong_answer));
            answerCards[question.getCorrectAnswerIndex()].setCardBackgroundColor(getResources().getColor(R.color.correct_answer));
        }

        for (MaterialCardView card : answerCards) {
            card.setClickable(false);
        }
        nextButton.setEnabled(true);
    }

    private static class Question {
        private String questionText;
        private String[] answers;
        private int correctAnswerIndex;

        public Question(String questionText, String[] answers, int correctAnswerIndex) {
            this.questionText = questionText;
            this.answers = answers;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String[] getAnswers() {
            return answers;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }
    }
} 