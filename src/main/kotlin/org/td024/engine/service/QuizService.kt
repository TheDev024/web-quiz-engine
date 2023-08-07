package org.td024.engine.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.td024.engine.dao.AnswerDao
import org.td024.engine.entity.AppUser
import org.td024.engine.entity.Quiz
import org.td024.engine.model.Response
import org.td024.engine.repo.QuizRepository

@Service
class QuizService(
    @Autowired private val quizRepository: QuizRepository
) {

    fun getAll(page: Int, size: Int): Page<Quiz> = quizRepository.findAll(PageRequest.of(page, size))

    fun findQuizById(id: Long) = quizRepository.findQuizById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun save(quiz: Quiz): Quiz = quizRepository.save(quiz)

    fun solve(quizId: Long, answerDao: AnswerDao, user: AppUser): Response {
        val quiz = findQuizById(quizId)

        val success = answerDao.answer == quiz.answer
        val feedback = if (success) "Congratulations, you're right!" else "Wrong answer! Please, try again."

        return Response(success, feedback)
    }

    fun delete(id: Long) = quizRepository.deleteById(id)
}
