package org.td024.engine.controller

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.td024.engine.dao.AnswerDao
import org.td024.engine.dao.QuizDao
import org.td024.engine.entity.AppUser
import org.td024.engine.mapper.QuizMapper
import org.td024.engine.service.QuizService
import org.td024.engine.service.SolutionService

@RestController
@RequestMapping("/api/quizzes")
class QuizController(
    @Autowired private val quizService: QuizService,
    @Autowired private val solutionService: SolutionService,
    @Autowired private val mapper: QuizMapper
) {

    @GetMapping
    fun getAll(@RequestParam(defaultValue = "1") page: Int, @RequestParam(defaultValue = "10") size: Int) =
        quizService.getAll(page - 1, size)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = quizService.findQuizById(id)

    @GetMapping("/solutions")
    fun getAllSolutions(
        @AuthenticationPrincipal user: AppUser,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ) = solutionService.getAllByUser(page - 1, size, user)

    @PostMapping
    fun create(@RequestBody @Valid quizDao: QuizDao, @AuthenticationPrincipal user: AppUser) =
        quizService.save(mapper.quizDaoToQuiz(quizDao, user))

    @PostMapping("/{id}/solve")
    fun solve(@PathVariable id: Long, @RequestBody answerDao: AnswerDao, @AuthenticationPrincipal user: AppUser) =
        quizService.solve(id, answerDao, user)

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long, @AuthenticationPrincipal user: AppUser) {
        val quiz = quizService.findQuizById(id)

        if (quiz.author != user) throw ResponseStatusException(
            HttpStatus.FORBIDDEN,
            "Quizzes can only be deleted by their authors!"
        )

        solutionService.deleteAllByQuiz(quiz)

        return quizService.delete(id)
    }
}
