package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private var selectedQuizContainer: CardView? = null
    private var selectedQuizType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            // Находим все контейнеры
            val quiz1Container = findViewById<CardView>(R.id.quiz1Container)
            val quiz2Container = findViewById<CardView>(R.id.quiz2Container)
            val quiz3Container = findViewById<CardView>(R.id.quiz3Container)
            val quiz4Container = findViewById<CardView>(R.id.quiz4Container)
            val quiz5Container = findViewById<CardView>(R.id.quiz5Container)
            val startButton = findViewById<Button>(R.id.startButton)

            // Создаем список контейнеров с их типами викторин
            val quizContainers = listOf(
                Pair(quiz1Container, "Викторина по флагам"),
                Pair(quiz2Container, "Викторина по столицам"),
                Pair(quiz3Container, "Викторина по национальностям"),
                Pair(quiz4Container, "Викторина по языкам"),
                Pair(quiz5Container, "Викторина по планетам")
            )

            // Устанавливаем обработчики нажатий для каждого контейнера
            quizContainers.forEach { (container, quizType) ->
                container.setOnClickListener {
                    // Сбрасываем цвет предыдущего выбранного контейнера
                    selectedQuizContainer?.setCardBackgroundColor(Color.WHITE)
                    (selectedQuizContainer?.getChildAt(0) as? android.widget.TextView)?.setTextColor(Color.BLACK)

                    // Устанавливаем черный цвет для выбранного контейнера
                    container.setCardBackgroundColor(Color.BLACK)
                    (container.getChildAt(0) as? android.widget.TextView)?.setTextColor(Color.WHITE)

                    // Сохраняем выбранный контейнер и тип викторины
                    selectedQuizContainer = container
                    selectedQuizType = quizType
                }
            }

            // Обработчик нажатия на кнопку "Начать викторину"
            startButton.setOnClickListener {
                if (selectedQuizType == null) {
                    Toast.makeText(this, "Пожалуйста, выберите викторину", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val intent = Intent(this, QuizActivity::class.java).apply {
                    putExtra("QUIZ_TYPE", selectedQuizType)
                }
                startActivity(intent)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка при инициализации", Toast.LENGTH_SHORT).show()
        }
    }
} 
} 