package org.td024.webquizengine.dao

data class QuizDAO(
    val title: String,
    val text: String,
    val options: List<String>,
    val answer: Int
)
