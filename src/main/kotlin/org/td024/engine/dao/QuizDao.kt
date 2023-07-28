package org.td024.engine.dao

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class QuizDao(
    @field:NotEmpty
    val title: String,
    @field:NotEmpty
    val text: String,
    @field:Size(min = 2)
    val options: List<String>,
    val answer: List<Int>
)
