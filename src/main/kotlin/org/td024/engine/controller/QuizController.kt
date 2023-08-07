package org.td024.engine.controller

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.td024.engine.dao.AnswerDao
import org.td024.engine.dao.QuizDao
import org.td024.engine.entity.AppUser
import org.td024.engine.mapper.QuizMapper
import org.td024.engine.service.QuizService

@RestController
@RequestMapping("/api/quizzes")
class QuizController(@Autowired private val service: QuizService, @Autowired private val mapper: QuizMapper) {

    @GetMapping
    fun getAll(@RequestParam(defaultValue = "1") page: Int, @RequestParam(defaultValue = "10") size: Int) =
        service.getAll(page - 1, size)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.findQuizById(id)

    @PostMapping
    fun create(@RequestBody @Valid quizDao: QuizDao, @AuthenticationPrincipal user: AppUser) =
        service.save(mapper.quizDaoToQuiz(quizDao, user))

    @PostMapping("/{id}/solve")
    fun solve(@PathVariable id: Long, @RequestBody answerDao: AnswerDao) = service.solve(id, answerDao)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long, @AuthenticationPrincipal user: AppUser) {
        val quiz = service.findQuizById(id)

        if (quiz.author != user) throw ResponseStatusException(
            HttpStatus.FORBIDDEN,
            "Quizzes can only be deleted by their authors!"
        )

        return service.delete(id)
    }
}
