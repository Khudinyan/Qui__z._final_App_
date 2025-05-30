package com.example.qui__z.model;

import java.util.List;

public class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private int imageResourceId;

    public Question(String question, String option1, String option2, String option3, String option4, String answer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.imageResourceId = 0;
    }

    public Question(String question, String option1, String option2, String option3, String option4, String answer, int imageResourceId) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.imageResourceId = imageResourceId;
    }

    public static List<Question> getQuestions(String category) {
        return null;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAnswer() {
        return answer;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getQuestionText() {
        return 0;
    }

    public String getCorrectAnswer() {
        return null;
    }

    public String getOptions() {
        return null;
    }
}