package org.td024.webquizengine.dto

data class QuizDTO (
    val id: Int,
    val title: String,
    val text: String,
    val options: List<String>
)
