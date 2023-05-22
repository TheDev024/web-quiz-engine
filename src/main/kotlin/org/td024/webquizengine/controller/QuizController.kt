package org.td024.webquizengine.controller

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.td024.webquizengine.dao.QuizDAO
import org.td024.webquizengine.dao.SolutionDAO
import org.td024.webquizengine.dto.QuizDTO
import org.td024.webquizengine.entity.Response
import org.td024.webquizengine.mapper.QuizMapper
import org.td024.webquizengine.repo.QuizRepo

@RestController
@RequestMapping("/api/quizzes")
class QuizController(@Autowired val repo: QuizRepo, @Autowired val mapper: QuizMapper) {
    @GetMapping
    fun getAllQuizzes() = repo.getAll().map { quiz -> mapper.quizToQuizDTO(quiz) }

    @GetMapping("/{id}")
    fun getQuizById(@PathVariable id: Int) =
        mapper.quizToQuizDTO(repo.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND))

    @PostMapping
    fun addQuiz(@RequestBody @Valid quizDAO: QuizDAO): QuizDTO {
        val quiz = mapper.quizDAOToQuiz(quizDAO)
        repo.save(quiz)
        return mapper.quizToQuizDTO(quiz)
    }

    @PostMapping("/{id}/solve")
    fun answerQuiz(@PathVariable id: Int, @RequestBody solution: SolutionDAO): Response {
        val quiz = repo.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val result = solution.answer == quiz.answer
        return Response(
            result,
            if (result) "Congratulations, you're right!"
            else "Wrong answer! Please, try again."
        )
    }
}
