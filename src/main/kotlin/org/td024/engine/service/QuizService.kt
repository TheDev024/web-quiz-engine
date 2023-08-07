package org.td024.engine.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.td024.engine.dao.AnswerDao
import org.td024.engine.entity.Quiz
import org.td024.engine.model.Response
import org.td024.engine.repo.QuizRepository

@Service
class QuizService(@Autowired private val repository: QuizRepository) {

    fun getAll(page: Int, size: Int): Page<Quiz> = repository.findAll(PageRequest.of(page, size))

    fun findQuizById(id: Long) = repository.findQuizById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun save(quiz: Quiz): Quiz = repository.save(quiz)

    fun solve(quizId: Long, answerDao: AnswerDao): Response {
        val quiz = findQuizById(quizId)

        val success = quiz.answer == answerDao.answer
        val feedback = if (success) "Congratulations, you're right!" else "Wrong answerDao! Please, try again."

        return Response(success, feedback)
    }

    fun delete(id: Long) = repository.deleteById(id)
}
