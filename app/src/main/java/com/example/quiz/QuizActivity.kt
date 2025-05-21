package com.example.quiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class QuizActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var scoreTextView: TextView
    private lateinit var progressTextView: TextView

    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questions: List<Question>
    private var quizType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        try {
            // Получаем тип викторины
            quizType = intent.getStringExtra("QUIZ_TYPE")
            if (quizType == null) {
                Toast.makeText(this, "Ошибка: тип викторины не указан", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

            // Инициализация UI элементов
            questionTextView = findViewById(R.id.questionTextView)
            option1Button = findViewById(R.id.option1Button)
            option2Button = findViewById(R.id.option2Button)
            option3Button = findViewById(R.id.option3Button)
            option4Button = findViewById(R.id.option4Button)
            scoreTextView = findViewById(R.id.scoreTextView)
            progressTextView = findViewById(R.id.progressTextView)

            // Загружаем вопросы в зависимости от типа викторины
            questions = when (quizType) {
                "Викторина по флагам" -> getFlagQuestions()
                "Викторина по столицам" -> getCapitalQuestions()
                "Викторина по национальностям" -> getNationalityQuestions()
                "Викторина по языкам" -> getLanguageQuestions()
                "Викторина по планетам" -> getPlanetQuestions()
                else -> {
                    Toast.makeText(this, "Неизвестный тип викторины", Toast.LENGTH_SHORT).show()
                    finish()
                    return
                }
            }

            if (questions.isEmpty()) {
                Toast.makeText(this, "Ошибка: нет доступных вопросов", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

            // Устанавливаем обработчики нажатий
            val optionButtons = listOf(option1Button, option2Button, option3Button, option4Button)
            optionButtons.forEachIndexed { index, button ->
                button.setOnClickListener {
                    checkAnswer(index)
                }
            }

            // Показываем первый вопрос
            showQuestion()

        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка при инициализации: ${e.message}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun showQuestion() {
        try {
            if (currentQuestionIndex < questions.size) {
                val question = questions[currentQuestionIndex]
                questionTextView.text = question.questionText
                
                val options = question.options
                option1Button.text = options[0]
                option2Button.text = options[1]
                option3Button.text = options[2]
                option4Button.text = options[3]

                progressTextView.text = "Вопрос ${currentQuestionIndex + 1} из ${questions.size}"
                scoreTextView.text = "Счет: $score"
            } else {
                showFinalScore()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка при отображении вопроса: ${e.message}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        try {
            val question = questions[currentQuestionIndex]
            if (selectedOptionIndex == question.correctAnswerIndex) {
                score++
                Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Неправильно! Правильный ответ: ${question.options[question.correctAnswerIndex]}", Toast.LENGTH_SHORT).show()
            }

            currentQuestionIndex++
            showQuestion()
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка при проверке ответа: ${e.message}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun showFinalScore() {
        try {
            val percentage = (score.toFloat() / questions.size * 100).toInt()
            val message = when {
                percentage >= 90 -> "Отлично! Вы набрали $score из ${questions.size} ($percentage%)"
                percentage >= 70 -> "Хорошо! Вы набрали $score из ${questions.size} ($percentage%)"
                percentage >= 50 -> "Неплохо! Вы набрали $score из ${questions.size} ($percentage%)"
                else -> "Попробуйте еще раз! Вы набрали $score из ${questions.size} ($percentage%)"
            }

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            finish()
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка при отображении результата: ${e.message}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun getFlagQuestions(): List<Question> {
        return listOf(
            Question(
                "Какой флаг принадлежит России?",
                listOf("Белый-синий-красный", "Красный-белый-синий", "Синий-белый-красный", "Красный-синий-белый"),
                0
            ),
            Question(
                "Какой флаг принадлежит США?",
                listOf("Красный-белый-синий", "Белый-синий-красный", "Синий-белый-красный", "Красный-синий-белый"),
                0
            ),
            Question(
                "Какой флаг принадлежит Франции?",
                listOf("Синий-белый-красный", "Красный-белый-синий", "Белый-синий-красный", "Красный-синий-белый"),
                0
            )
        )
    }

    private fun getCapitalQuestions(): List<Question> {
        return listOf(
            Question(
                "Столица России?",
                listOf("Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург"),
                0
            ),
            Question(
                "Столица Франции?",
                listOf("Париж", "Лион", "Марсель", "Бордо"),
                0
            ),
            Question(
                "Столица Германии?",
                listOf("Берлин", "Мюнхен", "Гамбург", "Франкфурт"),
                0
            )
        )
    }

    private fun getNationalityQuestions(): List<Question> {
        return listOf(
            Question(
                "Житель России называется...",
                listOf("Русский", "Россиянин", "Российский", "Россиянец"),
                0
            ),
            Question(
                "Житель Франции называется...",
                listOf("Француз", "Французский", "Француженка", "Французы"),
                0
            ),
            Question(
                "Житель Германии называется...",
                listOf("Немец", "Германец", "Германский", "Немцы"),
                0
            )
        )
    }

    private fun getLanguageQuestions(): List<Question> {
        return listOf(
            Question(
                "Официальный язык России?",
                listOf("Русский", "Английский", "Французский", "Немецкий"),
                0
            ),
            Question(
                "Официальный язык Франции?",
                listOf("Французский", "Английский", "Немецкий", "Испанский"),
                0
            ),
            Question(
                "Официальный язык Германии?",
                listOf("Немецкий", "Английский", "Французский", "Испанский"),
                0
            )
        )
    }

    private fun getPlanetQuestions(): List<Question> {
        return listOf(
            Question(
                "Самая большая планета Солнечной системы?",
                listOf("Юпитер", "Сатурн", "Уран", "Нептун"),
                0
            ),
            Question(
                "Ближайшая к Солнцу планета?",
                listOf("Меркурий", "Венера", "Земля", "Марс"),
                0
            ),
            Question(
                "Планета с кольцами?",
                listOf("Сатурн", "Юпитер", "Уран", "Нептун"),
                0
            )
        )
    }
}

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
) 