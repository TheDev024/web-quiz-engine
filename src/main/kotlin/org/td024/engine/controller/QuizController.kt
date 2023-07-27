package org.td024.engine.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.td024.engine.dao.QuizDao
import org.td024.engine.mapper.QuizMapper
import org.td024.engine.service.QuizService

@RestController
@RequestMapping("/api/quizzes")
class QuizController(@Autowired private val service: QuizService, @Autowired private val mapper: QuizMapper) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.findQuizById(id)

    @PostMapping
    fun create(@RequestBody quizDao: QuizDao) = service.save(mapper.quizDaoToQuiz(quizDao))

    @PostMapping("/{id}/solve")
    fun solve(@PathVariable id: Long, @RequestParam answer: Int) = service.solve(id, answer)
}
