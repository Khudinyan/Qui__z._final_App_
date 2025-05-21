package com.example.qui__z;

public class QuestionsList {
    private String question;
    private String[] options; // 4 варианта
    private int correctAnswerIndex; // индекс правильного ответа (0-3)
    private String userSelectedAnswer = "";
    private String explanation = "";
    private int imageResource = -1; // -1 если нет картинки

    // Конструктор без картинки
    public QuestionsList(String question, String[] options, int correctAnswerIndex, String explanation) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.explanation = explanation;
    }

    // Конструктор с картинкой
    public QuestionsList(String question, String[] options, int correctAnswerIndex, String explanation, int imageResource) {
        this(question, options, correctAnswerIndex, explanation);
        this.imageResource = imageResource;
    }

    // Геттеры
    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getCorrectAnswer() {
        return options[correctAnswerIndex];
    }

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean hasImage() {
        return imageResource != -1;
    }
} 