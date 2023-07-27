package org.td024.engine.dao

data class QuizDao(
    val title: String,
    val text: String,
    val options: List<String>,
    val answer: Int
)
