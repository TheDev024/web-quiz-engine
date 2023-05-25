package org.td024.webquizengine.controller

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.td024.webquizengine.dao.AnswerDAO
import org.td024.webquizengine.dao.QuizDAO
import org.td024.webquizengine.dto.QuizDTO
import org.td024.webquizengine.entity.Response
import org.td024.webquizengine.mapper.QuizMapper
import org.td024.webquizengine.service.QuizService

@RestController
@RequestMapping("/api/quizzes")
class QuizController(@Autowired val service: QuizService, @Autowired val mapper: QuizMapper) {
    @GetMapping
    fun getAllQuizzes() = service.getAll().map { quiz -> mapper.quizToQuizDTO(quiz) }

    @GetMapping("/{id}")
    fun getQuizById(@PathVariable id: Int) =
        mapper.quizToQuizDTO(service.getById(id))

    @PostMapping
    fun addQuiz(@RequestBody @Valid quizDAO: QuizDAO): QuizDTO {
        val quiz = mapper.quizDAOToQuiz(quizDAO)
        service.save(quiz)
        return mapper.quizToQuizDTO(quiz)
    }

    @PostMapping("/{id}/solve")
    fun answerQuiz(@PathVariable id: Int, @RequestBody answerDAO: AnswerDAO): Response {
        val quiz = service.getById(id)
        val result = answerDAO.answer == quiz.answer
        return Response(
            result,
            if (result) "Congratulations, you're right!"
            else "Wrong answer! Please, try again."
        )
    }
}
