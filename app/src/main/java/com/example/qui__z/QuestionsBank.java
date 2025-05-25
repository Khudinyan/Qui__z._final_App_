package com.example.qui__z;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    // Метод для получения вопросов по столицам
    private static List<QuestionsList> capitalQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        questionsList.add(new QuestionsList("Какая столица у Бразилии?", "Бразилиа", "Рио-де-Жанейро", "Сан-Паулу", "Сальвадор", "Бразилиа", ""));
        questionsList.add(new QuestionsList("Какая столица у Египта?", "Каир", "Александрия", "Гиза", "Луксор", "Каир", ""));
        questionsList.add(new QuestionsList("Какая столица у Турции?", "Анкара", "Стамбул", "Измир", "Анталья", "Анкара", ""));
        questionsList.add(new QuestionsList("Какая столица у Индии?", "Нью-Дели", "Мумбаи", "Бангалор", "Калькутта", "Нью-Дели", ""));
        questionsList.add(new QuestionsList("Какая столица у Мексики?", "Мехико", "Гвадалахара", "Канкун", "Монтеррей", "Мехико", ""));
        questionsList.add(new QuestionsList("Какая столица у Аргентины?", "Буэнос-Айрес", "Кордова", "Росарио", "Мендоса", "Буэнос-Айрес", ""));
        questionsList.add(new QuestionsList("Какая столица у Украины?", "Киев", "Львов", "Харьков", "Одесса", "Киев", ""));
        questionsList.add(new QuestionsList("Какая столица у Ирана?", "Тегеран", "Исфахан", "Шираз", "Мешхед", "Тегеран", ""));
        questionsList.add(new QuestionsList("Какая столица у Швеции?", "Стокгольм", "Гётеборг", "Мальмё", "Уппсала", "Стокгольм", ""));
        questionsList.add(new QuestionsList("Какая столица у Норвегии?", "Осло", "Берген", "Тронхейм", "Ставангер", "Осло", ""));

        return questionsList;
    }

    // Метод для получения вопросов по флагам
    private static List<QuestionsList> flagsQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        questionsList.add(new QuestionsList("Флаг какой страны состоит из трех вертикальных полос: синей, белой и красной?", "а) Италия", "б) Россия", "в) Франция", "г) Нидерланды", "в) Франция", ""));
        questionsList.add(new QuestionsList("На флаге какой страны изображен красный круг на белом фоне?", "а) Китай", "б) Япония", "в) Южная Корея", "г) Вьетнам", "б) Япония", ""));
        questionsList.add(new QuestionsList("Какой флаг имеет черный, красный и желтый горизонтальные полосы?", "а) Бельгия", "б) Германия", "в) Украина", "г) Эфиопия", "б) Германия", ""));
        questionsList.add(new QuestionsList("Флаг какой страны называют \"Юнион Джек\"?", "а) США", "б) Австралия", "в) Великобритания", "г) Канада", "в) Великобритания", ""));
        questionsList.add(new QuestionsList("На флаге какой страны изображен кленовый лист?", "а) Швейцария", "б) Канада", "в) Норвегия", "г) Финляндия", "б) Канада", ""));
        questionsList.add(new QuestionsList("Какой флаг имеет пять желтых звезд на красном фоне?", "а) Северная Корея", "б) Вьетнам", "в) Китай", "г) Сингапур", "в) Китай", ""));
        questionsList.add(new QuestionsList("Флаг какой страны состоит из красного креста на белом фоне?", "а) Швеция", "б) Дания", "в) Швейцария", "г) Норвегия", "в) Швейцария", ""));
        questionsList.add(new QuestionsList("Какой флаг имеет зеленый фон с белым полумесяцем и звездой?", "а) Пакистан", "б) Турция", "в) Алжир", "г) Малайзия", "б) Турция", ""));
        questionsList.add(new QuestionsList("На флаге какой страны изображены четыре звезды и синий крест?", "а) Новая Зеландия", "б) Австралия", "в) Самоа", "г) Фиджи", "г) Фиджи", ""));
        questionsList.add(new QuestionsList("Флаг какой страны состоит из 14 горизонтальных красно-белых полос и синего квадрата с желтым полумесяцем и звездой?", "а) Индонезия", "б) Малайзия", "в) Сингапур", "г) Таиланд", "б) Малайзия", ""));

        return questionsList;
    }

    // Метод для получения вопросов по языкам
    private static List<QuestionsList> languageQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        questionsList.add(new QuestionsList("На каком языке говорят в Бразилии?", "а) Португальский", "б) Испанский", "в) Бразильский", "г) Английский", "а) Португальский", ""));
        questionsList.add(new QuestionsList("Какой язык является официальным в Китае?", "а) Китайский", "б) Мандарин", "в) Кантонский", "г) Английский", "а) Китайский", ""));
        questionsList.add(new QuestionsList("На каком языке говорят в Японии?", "а) Японский", "б) Корейский", "в) Китайский", "г) Мандарин", "а) Японский", ""));
        questionsList.add(new QuestionsList("На каком языке говорят в Германии?", "а) Немецкий", "б) Голландский", "в) Французский", "г) Английский", "а) Немецкий", ""));
        questionsList.add(new QuestionsList("На каком языке говорят в Италии?", "а) Итальянский", "б) Испанский", "в) Французский", "г) Латынь", "а) Итальянский", ""));
        questionsList.add(new QuestionsList("Какой язык является официальным в Индии?", "а) Хинди", "б) Английский", "в) Бенгальский", "г) Все перечисленные", "г) Все перечисленные", ""));
        questionsList.add(new QuestionsList("На каком языке говорят в Египте?", "а) Арабский", "б) Египетский", "в) Французский", "г) Английский", "а) Арабский", ""));
        questionsList.add(new QuestionsList("Какой язык является официальным в Южной Корее?", "а) Корейский", "б) Японский", "в) Китайский", "г) Английский", "а) Корейский", ""));
        questionsList.add(new QuestionsList("На каком языке говорят в Швеции?", "а) Шведский", "б) Норвежский", "в) Датский", "г) Финский", "а) Шведский", ""));
        questionsList.add(new QuestionsList("Какой язык является официальным в Мексике?", "а) Испанский", "б) Мексиканский", "в) Португальский", "г) Английский", "а) Испанский", ""));

        return questionsList;
    }

    // Метод для получения вопросов по национальностям
    private static List<QuestionsList> nationalityQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        questionsList.add(new QuestionsList("Как называют жителей Японии?", "а) Японцы", "б) Китайцы", "в) Корейцы", "г) Монголы", "а) Японцы", ""));
        questionsList.add(new QuestionsList("Как называют жителей Германии?", "а) Немцы", "б) Голландцы", "в) Французы", "г) Австрийцы", "а) Немцы", ""));
        questionsList.add(new QuestionsList("Как называют жителей Франции?", "а) Французы", "б) Бельгийцы", "в) Швейцарцы", "г) Канадцы", "а) Французы", ""));
        questionsList.add(new QuestionsList("Как называют жителей Италии?", "а) Итальянцы", "б) Испанцы", "в) Греки", "г) Римляне", "а) Итальянцы", ""));
        questionsList.add(new QuestionsList("Как называют жителей Испании?", "а) Испанцы", "б) Португальцы", "в) Мексиканцы", "г) Колумбийцы", "а) Испанцы", ""));
        questionsList.add(new QuestionsList("Как называют жителей Китая?", "а) Китайцы", "б) Японцы", "в) Корейцы", "г) Вьетнамцы", "а) Китайцы", ""));
        questionsList.add(new QuestionsList("Как называют жителей Бразилии?", "а) Бразильцы", "б) Аргентинцы", "в) Колумбийцы", "г) Перуанцы", "а) Бразильцы", ""));
        questionsList.add(new QuestionsList("Как называют жителей Египта?", "а) Египтяне", "б) Арабы", "в) Африканцы", "г) Азиаты", "а) Египтяне", ""));
        questionsList.add(new QuestionsList("Как называют жителей Австралии?", "а) Австралийцы", "б) Новозеландцы", "в) Океанийцы", "г) Азиаты", "а) Австралийцы", ""));
        questionsList.add(new QuestionsList("Как называют жителей Канады?", "а) Канадцы", "б) Американцы", "в) Мексиканцы", "г) Североамериканцы", "а) Канадцы", ""));

        return questionsList;
    }

    // Метод для получения вопросов по планетам
    private static List<QuestionsList> planetsQuestions() {
        final List<QuestionsList> questionsList = new ArrayList<>();

        questionsList.add(new QuestionsList("Какая планета ближайшая к Солнцу?", "а) Меркурий", "б) Венера", "в) Земля", "г) Марс", "а) Меркурий", ""));
        questionsList.add(new QuestionsList("Какая планета самая большая в Солнечной системе?", "а) Юпитер", "б) Сатурн", "в) Уран", "г) Нептун", "а) Юпитер", ""));
        questionsList.add(new QuestionsList("Какую планету называют Красной планетой?", "а) Марс", "б) Венера", "в) Юпитер", "г) Сатурн", "а) Марс", ""));
        questionsList.add(new QuestionsList("На какой планете самые большие кольца?", "а) Сатурн", "б) Юпитер", "в) Уран", "г) Нептун", "а) Сатурн", ""));
        questionsList.add(new QuestionsList("Какая планета известна своим Большим красным пятном?", "а) Юпитер", "б) Марс", "в) Венера", "г) Меркурий", "а) Юпитер", ""));
        questionsList.add(new QuestionsList("Какая планета вращается в обратном направлении?", "а) Венера", "б) Уран", "в) Нептун", "г) Плутон", "а) Венера", ""));
        questionsList.add(new QuestionsList("На какой планете самый длинный день?", "а) Венера", "б) Меркурий", "в) Марс", "г) Юпитер", "а) Венера", ""));
        questionsList.add(new QuestionsList("Какая планета имеет больше всего спутников?", "а) Юпитер", "б) Сатурн", "в) Уран", "г) Нептун", "б) Сатурн", ""));
        questionsList.add(new QuestionsList("На какой планете самые сильные ветры?", "а) Нептун", "б) Юпитер", "в) Сатурн", "г) Уран", "а) Нептун", ""));
        questionsList.add(new QuestionsList("Какая планета имеет самый большой перепад температур?", "а) Меркурий", "б) Венера", "в) Марс", "г) Юпитер", "а) Меркурий", ""));

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
                return capitalQuestions(); // По умолчанию возвращаем вопросы по флагам
        }
    }
} 