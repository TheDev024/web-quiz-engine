package org.td024.engine.dao

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class QuizDao(
    @field:NotEmpty(message = "'title' field can't be empty!")
    val title: String,
    @field:NotEmpty(message = "'text' field can't be empty!")
    val text: String,
    @field:Size(min = 2, message = "There must be at least 2 options!")
    val options: List<String>,
    val answer: List<Int>
)
