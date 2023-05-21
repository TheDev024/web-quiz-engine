package org.td024.webquizengine.mapper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.td024.webquizengine.dao.QuizDAO
import org.td024.webquizengine.dto.QuizDTO
import org.td024.webquizengine.entity.Quiz
import org.td024.webquizengine.repo.QuizRepo

@Component
class QuizMapper(@Autowired val repo: QuizRepo) {
    fun quizToQuizDTO(quiz: Quiz): QuizDTO = QuizDTO(
        quiz.id!!,
        quiz.title,
        quiz.text,
        quiz.options
    )

    fun quizDAOToQuiz(quizDAO: QuizDAO): Quiz = Quiz(
        repo.getNewId(),
        quizDAO.title,
        quizDAO.text,
        quizDAO.options,
        quizDAO.answer
    )
}
