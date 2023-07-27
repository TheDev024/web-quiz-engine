package org.td024.engine.mapper

import org.springframework.stereotype.Component
import org.td024.engine.dao.QuizDao
import org.td024.engine.entity.Quiz

@Component
class QuizMapper {

    fun quizDaoToQuiz(quizDao: QuizDao) = Quiz(
        title = quizDao.title,
        text = quizDao.text,
        options = quizDao.options,
        answer = quizDao.answer
    )
}
