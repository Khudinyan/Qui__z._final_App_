package com.example.qui__z.model;

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
} 