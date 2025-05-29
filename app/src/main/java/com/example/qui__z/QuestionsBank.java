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
        final QuestionsList question6 = new QuestionsList("Столица Японии?", "Осака", "Токио", "Киото", "Нагоя", "Токио");
        final QuestionsList question7 = new QuestionsList("Столица Китая?", "Шанхай", "Пекин", "Гуанчжоу", "Шэньчжэнь", "Пекин");
        final QuestionsList question8 = new QuestionsList("Столица Бразилии?", "Рио-де-Жанейро", "Бразилиа", "Сан-Паулу", "Сальвадор", "Бразилиа");
        final QuestionsList question9 = new QuestionsList("Столица Австралии?", "Сидней", "Канберра", "Мельбурн", "Брисбен", "Канберра");
        final QuestionsList question10 = new QuestionsList("Столица Канады?", "Торонто", "Оттава", "Монреаль", "Ванкувер", "Оттава");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);
        questionsList.add(question6);
        questionsList.add(question7);
        questionsList.add(question8);
        questionsList.add(question9);
        questionsList.add(question10);

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
        final QuestionsList question6 = new QuestionsList("Флаг какой страны?", "Китай", "Япония", "Корея", "Вьетнам", "Япония");
        final QuestionsList question7 = new QuestionsList("Флаг какой страны?", "Бразилия", "Аргентина", "Чили", "Колумбия", "Бразилия");
        final QuestionsList question8 = new QuestionsList("Флаг какой страны?", "Египет", "Марокко", "Тунис", "Алжир", "Египет");
        final QuestionsList question9 = new QuestionsList("Флаг какой страны?", "Индия", "Пакистан", "Бангладеш", "Шри-Ланка", "Индия");
        final QuestionsList question10 = new QuestionsList("Флаг какой страны?", "Австралия", "Новая Зеландия", "Фиджи", "Самоа", "Австралия");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);
        questionsList.add(question6);
        questionsList.add(question7);
        questionsList.add(question8);
        questionsList.add(question9);
        questionsList.add(question10);

        return questionsList;
    }

    // Метод для получения вопросов по языкам
    private static List<QuestionsList> languageQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("На каком языке говорят в России?", "Русский", "Украинский", "Белорусский", "Польский", "Русский");
        final QuestionsList question2 = new QuestionsList("На каком языке говорят во Франции?", "Немецкий", "Испанский", "Французский", "Итальянский", "Французский");
        final QuestionsList question3 = new QuestionsList("На каком языке говорят в Германии?", "Французский", "Немецкий", "Голландский", "Датский", "Немецкий");
        final QuestionsList question4 = new QuestionsList("На каком языке говорят в Италии?", "Испанский", "Португальский", "Итальянский", "Французский", "Итальянский");
        final QuestionsList question5 = new QuestionsList("На каком языке говорят в Испании?", "Португальский", "Испанский", "Французский", "Итальянский", "Испанский");
        final QuestionsList question6 = new QuestionsList("На каком языке говорят в Японии?", "Китайский", "Японский", "Корейский", "Вьетнамский", "Японский");
        final QuestionsList question7 = new QuestionsList("На каком языке говорят в Бразилии?", "Испанский", "Португальский", "Французский", "Итальянский", "Португальский");
        final QuestionsList question8 = new QuestionsList("На каком языке говорят в Египте?", "Арабский", "Турецкий", "Персидский", "Иврит", "Арабский");
        final QuestionsList question9 = new QuestionsList("На каком языке говорят в Индии?", "Хинди", "Бенгальский", "Тамильский", "Урду", "Хинди");
        final QuestionsList question10 = new QuestionsList("На каком языке говорят в Австралии?", "Английский", "Французский", "Немецкий", "Испанский", "Английский");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);
        questionsList.add(question6);
        questionsList.add(question7);
        questionsList.add(question8);
        questionsList.add(question9);
        questionsList.add(question10);

        return questionsList;
    }

    // Метод для получения вопросов по национальностям
    private static List<QuestionsList> nationalityQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Житель России - это...", "Русский", "Француз", "Немец", "Итальянец", "Русский");
        final QuestionsList question2 = new QuestionsList("Житель Франции - это...", "Испанец", "Француз", "Португалец", "Итальянец", "Француз");
        final QuestionsList question3 = new QuestionsList("Житель Германии - это...", "Австриец", "Немец", "Швейцарец", "Бельгиец", "Немец");
        final QuestionsList question4 = new QuestionsList("Житель Италии - это...", "Грек", "Итальянец", "Испанец", "Португалец", "Итальянец");
        final QuestionsList question5 = new QuestionsList("Житель Испании - это...", "Француз", "Испанец", "Португалец", "Итальянец", "Испанец");
        final QuestionsList question6 = new QuestionsList("Житель Японии - это...", "Китаец", "Японец", "Кореец", "Вьетнамец", "Японец");
        final QuestionsList question7 = new QuestionsList("Житель Бразилии - это...", "Аргентинец", "Бразилец", "Чилийец", "Колумбиец", "Бразилец");
        final QuestionsList question8 = new QuestionsList("Житель Египта - это...", "Марокканец", "Египтянин", "Тунисец", "Алжирец", "Египтянин");
        final QuestionsList question9 = new QuestionsList("Житель Индии - это...", "Пакистанец", "Индиец", "Бангладешец", "Шриланкиец", "Индиец");
        final QuestionsList question10 = new QuestionsList("Житель Австралии - это...", "Новозеландец", "Австралиец", "Фиджиец", "Самоанец", "Австралиец");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);
        questionsList.add(question6);
        questionsList.add(question7);
        questionsList.add(question8);
        questionsList.add(question9);
        questionsList.add(question10);

        return questionsList;
    }

    // Метод для получения вопросов по планетам
    private static List<QuestionsList> planetsQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Какая планета ближе всего к Солнцу?", "Венера", "Меркурий", "Марс", "Земля", "Меркурий");
        final QuestionsList question2 = new QuestionsList("Какая планета самая большая в Солнечной системе?", "Сатурн", "Юпитер", "Нептун", "Уран", "Юпитер");
        final QuestionsList question3 = new QuestionsList("На какой планете есть кольца?", "Юпитер", "Марс", "Сатурн", "Венера", "Сатурн");
        final QuestionsList question4 = new QuestionsList("Какая планета известна как 'Красная планета'?", "Венера", "Марс", "Меркурий", "Юпитер", "Марс");
        final QuestionsList question5 = new QuestionsList("Какая планета самая горячая в Солнечной системе?", "Меркурий", "Венера", "Марс", "Юпитер", "Венера");
        final QuestionsList question6 = new QuestionsList("Какая планета вращается в обратном направлении?", "Марс", "Венера", "Меркурий", "Юпитер", "Венера");
        final QuestionsList question7 = new QuestionsList("На какой планете самый длинный день?", "Венера", "Меркурий", "Марс", "Юпитер", "Венера");
        final QuestionsList question8 = new QuestionsList("Какая планета имеет больше всего спутников?", "Сатурн", "Юпитер", "Уран", "Нептун", "Сатурн");
        final QuestionsList question9 = new QuestionsList("Какая планета самая маленькая в Солнечной системе?", "Марс", "Меркурий", "Венера", "Земля", "Меркурий");
        final QuestionsList question10 = new QuestionsList("На какой планете самые сильные ветры?", "Юпитер", "Нептун", "Уран", "Сатурн", "Нептун");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);
        questionsList.add(question6);
        questionsList.add(question7);
        questionsList.add(question8);
        questionsList.add(question9);
        questionsList.add(question10);

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