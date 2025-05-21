package com.example.qui__z;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {
    public static List<Question> getQuestions(String category) {
        List<Question> questions = new ArrayList<>();
        
        switch (category) {
            case "flags":
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Россия",
                    new String[]{"Франция", "Италия", "Нидерланды"},
                    R.drawable.flag_russia
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "США",
                    new String[]{"Великобритания", "Австралия", "Новая Зеландия"},
                    R.drawable.flag_usa
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Япония",
                    new String[]{"Китай", "Южная Корея", "Вьетнам"},
                    R.drawable.flag_japan
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Германия",
                    new String[]{"Бельгия", "Италия", "Испания"},
                    R.drawable.flag_germany
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Франция",
                    new String[]{"Россия", "Нидерланды", "Чехия"},
                    R.drawable.flag_france
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Великобритания",
                    new String[]{"США", "Австралия", "Новая Зеландия"},
                    R.drawable.flag_uk
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Италия",
                    new String[]{"Франция", "Румыния", "Молдова"},
                    R.drawable.flag_italy
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Испания",
                    new String[]{"Португалия", "Греция", "Кипр"},
                    R.drawable.flag_spain
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Китай",
                    new String[]{"Япония", "Южная Корея", "Вьетнам"},
                    R.drawable.flag_china
                ));
                questions.add(new Question(
                    "Флаг какой страны изображен?",
                    "Бразилия",
                    new String[]{"Аргентина", "Колумбия", "Перу"},
                    R.drawable.flag_brazil
                ));
                break;

            case "capitals":
                questions.add(new Question(
                    "Столица России?",
                    "Москва",
                    new String[]{"Санкт-Петербург", "Новосибирск", "Екатеринбург"},
                    R.drawable.capital_moscow
                ));
                questions.add(new Question(
                    "Столица США?",
                    "Вашингтон",
                    new String[]{"Нью-Йорк", "Лос-Анджелес", "Чикаго"},
                    R.drawable.capital_washington
                ));
                questions.add(new Question(
                    "Столица Японии?",
                    "Токио",
                    new String[]{"Осака", "Киото", "Нагоя"},
                    R.drawable.capital_tokyo
                ));
                questions.add(new Question(
                    "Столица Германии?",
                    "Берлин",
                    new String[]{"Мюнхен", "Гамбург", "Франкфурт"},
                    R.drawable.capital_berlin
                ));
                questions.add(new Question(
                    "Столица Франции?",
                    "Париж",
                    new String[]{"Лион", "Марсель", "Бордо"},
                    R.drawable.capital_paris
                ));
                questions.add(new Question(
                    "Столица Великобритании?",
                    "Лондон",
                    new String[]{"Манчестер", "Бирмингем", "Ливерпуль"},
                    R.drawable.capital_london
                ));
                questions.add(new Question(
                    "Столица Италии?",
                    "Рим",
                    new String[]{"Милан", "Неаполь", "Турин"},
                    R.drawable.capital_rome
                ));
                questions.add(new Question(
                    "Столица Испании?",
                    "Мадрид",
                    new String[]{"Барселона", "Валенсия", "Севилья"},
                    R.drawable.capital_madrid
                ));
                questions.add(new Question(
                    "Столица Китая?",
                    "Пекин",
                    new String[]{"Шанхай", "Гуанчжоу", "Шэньчжэнь"},
                    R.drawable.capital_beijing
                ));
                questions.add(new Question(
                    "Столица Бразилии?",
                    "Бразилиа",
                    new String[]{"Рио-де-Жанейро", "Сан-Паулу", "Сальвадор"},
                    R.drawable.capital_brasilia
                ));
                break;

            case "nationalities":
                questions.add(new Question(
                    "Как называют жителей России?",
                    "Россияне",
                    new String[]{"Русские", "Славяне", "Европейцы"},
                    R.drawable.nationality_russian
                ));
                questions.add(new Question(
                    "Как называют жителей США?",
                    "Американцы",
                    new String[]{"Англосаксы", "Европейцы", "Западные"},
                    R.drawable.nationality_american
                ));
                questions.add(new Question(
                    "Как называют жителей Японии?",
                    "Японцы",
                    new String[]{"Азиаты", "Самураи", "Ниндзя"},
                    R.drawable.nationality_japanese
                ));
                questions.add(new Question(
                    "Как называют жителей Германии?",
                    "Немцы",
                    new String[]{"Германцы", "Европейцы", "Западные"},
                    R.drawable.nationality_german
                ));
                questions.add(new Question(
                    "Как называют жителей Франции?",
                    "Французы",
                    new String[]{"Галлы", "Европейцы", "Западные"},
                    R.drawable.nationality_french
                ));
                questions.add(new Question(
                    "Как называют жителей Великобритании?",
                    "Британцы",
                    new String[]{"Англичане", "Европейцы", "Западные"},
                    R.drawable.nationality_british
                ));
                questions.add(new Question(
                    "Как называют жителей Италии?",
                    "Итальянцы",
                    new String[]{"Романцы", "Европейцы", "Южные"},
                    R.drawable.nationality_italian
                ));
                questions.add(new Question(
                    "Как называют жителей Испании?",
                    "Испанцы",
                    new String[]{"Иберийцы", "Европейцы", "Южные"},
                    R.drawable.nationality_spanish
                ));
                questions.add(new Question(
                    "Как называют жителей Китая?",
                    "Китайцы",
                    new String[]{"Азиаты", "Восточные", "Азиатские"},
                    R.drawable.nationality_chinese
                ));
                questions.add(new Question(
                    "Как называют жителей Бразилии?",
                    "Бразильцы",
                    new String[]{"Латиноамериканцы", "Южные", "Американцы"},
                    R.drawable.nationality_brazilian
                ));
                break;

            case "languages":
                questions.add(new Question(
                    "Официальный язык России?",
                    "Русский",
                    new String[]{"Английский", "Китайский", "Немецкий"},
                    R.drawable.language_russian
                ));
                questions.add(new Question(
                    "Официальный язык США?",
                    "Английский",
                    new String[]{"Испанский", "Французский", "Немецкий"},
                    R.drawable.language_english
                ));
                questions.add(new Question(
                    "Официальный язык Японии?",
                    "Японский",
                    new String[]{"Китайский", "Корейский", "Вьетнамский"},
                    R.drawable.language_japanese
                ));
                questions.add(new Question(
                    "Официальный язык Германии?",
                    "Немецкий",
                    new String[]{"Французский", "Итальянский", "Испанский"},
                    R.drawable.language_german
                ));
                questions.add(new Question(
                    "Официальный язык Франции?",
                    "Французский",
                    new String[]{"Английский", "Немецкий", "Испанский"},
                    R.drawable.language_french
                ));
                questions.add(new Question(
                    "Официальный язык Великобритании?",
                    "Английский",
                    new String[]{"Шотландский", "Валлийский", "Ирландский"},
                    R.drawable.language_english
                ));
                questions.add(new Question(
                    "Официальный язык Италии?",
                    "Итальянский",
                    new String[]{"Французский", "Испанский", "Португальский"},
                    R.drawable.language_italian
                ));
                questions.add(new Question(
                    "Официальный язык Испании?",
                    "Испанский",
                    new String[]{"Португальский", "Французский", "Итальянский"},
                    R.drawable.language_spanish
                ));
                questions.add(new Question(
                    "Официальный язык Китая?",
                    "Китайский",
                    new String[]{"Японский", "Корейский", "Вьетнамский"},
                    R.drawable.language_chinese
                ));
                questions.add(new Question(
                    "Официальный язык Бразилии?",
                    "Португальский",
                    new String[]{"Испанский", "Французский", "Итальянский"},
                    R.drawable.language_portuguese
                ));
                break;

            case "planets":
                questions.add(new Question(
                    "Какая планета ближе всего к Солнцу?",
                    "Меркурий",
                    new String[]{"Венера", "Марс", "Юпитер"},
                    R.drawable.planet_mercury
                ));
                questions.add(new Question(
                    "Самая большая планета Солнечной системы?",
                    "Юпитер",
                    new String[]{"Сатурн", "Уран", "Нептун"},
                    R.drawable.planet_jupiter
                ));
                break;
        }
        
        return questions;
    }
} 