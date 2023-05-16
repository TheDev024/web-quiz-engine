package org.td024.webquizengine.repo

import org.td024.webquizengine.entity.Quiz

class QuizRepo {
    private val quizzes = mutableListOf(
        Quiz(
            "Kotlin keywords    ",
            "Which of the following is a keyword used to declare a variable in Kotlin?",
            listOf("val", "let", "var", "const")
        )
    )

    fun getById(id: Int) = quizzes[id]

    fun getAll() = quizzes

    fun save(quiz: Quiz) = quizzes.add(quiz)

    fun delete(quiz: Quiz) = quizzes.remove(quiz)
}
