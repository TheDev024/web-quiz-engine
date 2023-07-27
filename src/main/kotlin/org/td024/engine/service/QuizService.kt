package org.td024.engine.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.td024.engine.entity.Quiz
import org.td024.engine.model.Response
import org.td024.engine.repo.QuizRepository

@Service
class QuizService(@Autowired private val repository: QuizRepository) {

    fun getAll(): List<Quiz> = repository.findAll()

    fun findQuizById(id: Long) = repository.findQuizById(id)

    fun save(quiz: Quiz): Quiz = repository.save(quiz)

    fun solve(quizId: Long, answer: Int): Response {
        val quiz = findQuizById(quizId) ?: throw NotFoundException()

        val (success, feedback) =
            if (quiz.answer == answer) Pair(true, "Congratulations, you're right!")
            else Pair(false, "Wrong answer! Please, try again.")

        return Response(success, feedback)
    }
}
