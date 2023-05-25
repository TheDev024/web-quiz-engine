package org.td024.webquizengine.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.td024.webquizengine.entity.Quiz
import org.td024.webquizengine.repo.QuizRepo

@Service
class QuizService(@Autowired private val repo: QuizRepo) {

    fun getById(id: Int) = repo.getReferenceById(id)

    fun getAll(): List<Quiz> = repo.findAll()

    fun save(quiz: Quiz) = repo.save(quiz)

}
