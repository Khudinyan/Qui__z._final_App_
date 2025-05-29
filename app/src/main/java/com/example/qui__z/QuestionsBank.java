package com.example.qui__z;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    // Метод для получения вопросов по столицам
    private static List<QuestionsList> capitalQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Столица России?", "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Москва");
        final QuestionsList question2 = new QuestionsList("Столица Франции?", "Лондон", "Берлин", "Париж", "Мадрид", "Париж");
        final QuestionsList question3 = new QuestionsList("Столица Германии?", "Берлин", "Мюнхен", "Гамбург", "Франкфурт", "Берлин");
        final QuestionsList question4 = new QuestionsList("Столица Италии?", "Милан", "Рим", "Неаполь", "Турин", "Рим");
        final QuestionsList question5 = new QuestionsList("Столица Испании?", "Барселона", "Мадрид", "Валенсия", "Севилья", "Мадрид");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);

        return questionsList;
    }

    // Метод для получения вопросов по флагам
    private static List<QuestionsList> flagsQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Флаг какой страны?", "Россия", "Франция", "Германия", "Италия", "Россия");
        final QuestionsList question2 = new QuestionsList("Флаг какой страны?", "Испания", "Португалия", "Франция", "Италия", "Франция");
        final QuestionsList question3 = new QuestionsList("Флаг какой страны?", "Австрия", "Германия", "Швейцария", "Бельгия", "Германия");
        final QuestionsList question4 = new QuestionsList("Флаг какой страны?", "Греция", "Италия", "Испания", "Португалия", "Италия");
        final QuestionsList question5 = new QuestionsList("Флаг какой страны?", "Франция", "Испания", "Португалия", "Италия", "Испания");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);

        return questionsList;
    }

    // Метод для получения вопросов по языкам
    private static List<QuestionsList> languageQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Официальный язык России?", "Русский", "Английский", "Французский", "Немецкий", "Русский");
        final QuestionsList question2 = new QuestionsList("Официальный язык Франции?", "Английский", "Французский", "Немецкий", "Испанский", "Французский");
        final QuestionsList question3 = new QuestionsList("Официальный язык Германии?", "Английский", "Французский", "Немецкий", "Испанский", "Немецкий");
        final QuestionsList question4 = new QuestionsList("Официальный язык Италии?", "Английский", "Французский", "Немецкий", "Итальянский", "Итальянский");
        final QuestionsList question5 = new QuestionsList("Официальный язык Испании?", "Английский", "Французский", "Немецкий", "Испанский", "Испанский");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);

        return questionsList;
    }

    // Метод для получения вопросов по национальностям
    private static List<QuestionsList> nationalityQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Житель России?", "Русский", "Француз", "Немец", "Итальянец", "Русский");
        final QuestionsList question2 = new QuestionsList("Житель Франции?", "Русский", "Француз", "Немец", "Итальянец", "Француз");
        final QuestionsList question3 = new QuestionsList("Житель Германии?", "Русский", "Француз", "Немец", "Итальянец", "Немец");
        final QuestionsList question4 = new QuestionsList("Житель Италии?", "Русский", "Француз", "Немец", "Итальянец", "Итальянец");
        final QuestionsList question5 = new QuestionsList("Житель Испании?", "Русский", "Француз", "Немец", "Испанец", "Испанец");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);

        return questionsList;
    }

    // Метод для получения вопросов по планетам
    private static List<QuestionsList> planetsQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Самая большая планета?", "Меркурий", "Венера", "Земля", "Юпитер", "Юпитер");
        final QuestionsList question2 = new QuestionsList("Самая близкая к Солнцу планета?", "Меркурий", "Венера", "Земля", "Марс", "Меркурий");
        final QuestionsList question3 = new QuestionsList("Самая горячая планета?", "Меркурий", "Венера", "Земля", "Марс", "Венера");
        final QuestionsList question4 = new QuestionsList("Самая холодная планета?", "Юпитер", "Сатурн", "Уран", "Нептун", "Нептун");
        final QuestionsList question5 = new QuestionsList("Самая маленькая планета?", "Меркурий", "Венера", "Земля", "Марс", "Меркурий");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);

        return questionsList;
    }

    // Метод для получения вопросов по выбранной теме
    public static List<QuestionsList> getQuestions(String selectedTopicName) {
        switch (selectedTopicName) {
            case "capital":
                return capitalQuestions();
            case "flags":
                return flagsQuestions();
            case "language":
                return languageQuestions();
            case "nationality":
                return nationalityQuestions();
            case "planets":
                return planetsQuestions();
            default:
                return capitalQuestions(); // По умолчанию возвращаем вопросы по столицам
        }
    }
} 