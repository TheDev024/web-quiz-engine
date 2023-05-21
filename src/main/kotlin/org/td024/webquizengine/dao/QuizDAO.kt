package org.td024.webquizengine.dao

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class QuizDAO(
    @field:NotBlank val title: String,
    @field:NotBlank val text: String,
    @field:Size(min = 2) val options: List<String>,
    val answer: List<Int>
)
