package com.example.qui__z;

public class Question {
    private String question;
    private String correctAnswer;
    private String[] wrongAnswers;
    private int imageResource;

    public Question(String question, String correctAnswer, String[] wrongAnswers, int imageResource) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
        this.imageResource = imageResource;
    }

    public Question(String question, String[] strings, String россия, int flagRussia) {

    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getWrongAnswers() {
        return wrongAnswers;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getOptions() {
        return null;
    }

    public int getImageResourceId() {
        return 0;
    }
}