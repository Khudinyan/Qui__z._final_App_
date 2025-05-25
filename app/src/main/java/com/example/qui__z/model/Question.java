package com.example.qui__z.model;

import java.util.List;

public class Question {
    private String questionText;
    private String correctAnswer;
    private String[] options;
    private int imageResourceId;

    public Question(String questionText, String correctAnswer, String[] options, int imageResourceId) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = options;
        this.imageResourceId = imageResourceId;
    }

    public static List<Question> getQuestions(String category) {
        return null;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getOptions() {
        return options;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getQuestion() {
        return 0;
    }
}