package org.td024.webquizengine.mapper

import org.springframework.stereotype.Component
import org.td024.webquizengine.dao.QuizDAO
import org.td024.webquizengine.dto.QuizDTO
import org.td024.webquizengine.entity.Quiz

@Component
class QuizMapper {
    fun quizToQuizDTO(quiz: Quiz): QuizDTO = QuizDTO(
        id = quiz.id!!,
        quiz.title!!,
        quiz.text!!,
        quiz.options!!
    )

    fun quizDAOToQuiz(quizDAO: QuizDAO): Quiz = Quiz(
        title = quizDAO.title,
        text = quizDAO.text,
        options = quizDAO.options,
        answer = quizDAO.answer ?: emptyList()
    )
}
