package org.td024.webquizengine.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.td024.webquizengine.entity.Response
import org.td024.webquizengine.repo.QuizRepo

@RestController
@RequestMapping("/api")
class QuizController {
    val repo = QuizRepo()

    @GetMapping("/quiz")
    fun getQuiz() = repo.getById(0)

    @PostMapping("/quiz")
    fun answerQuiz(@RequestParam answer: Int) = Response(
        answer == 2,
        if (answer == 2) "Congratulations, you're right!"
        else "Wrong answer! Please, try again."
    )
}
