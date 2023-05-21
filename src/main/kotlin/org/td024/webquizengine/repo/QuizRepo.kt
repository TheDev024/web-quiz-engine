package org.td024.webquizengine.repo

import org.springframework.stereotype.Component
import org.td024.webquizengine.dto.QuizDTO
import org.td024.webquizengine.entity.Quiz

@Component
class QuizRepo {
    private val quizzes = mutableListOf<Quiz>()

    fun getNewId() = quizzes.size

    fun getById(id: Int): Quiz? = quizzes.find { it.id == id }

    fun getAll() = quizzes

    fun save(quiz: Quiz) = quizzes.add(quiz)

    fun delete(quiz: Quiz) = quizzes.remove(quiz)
}
