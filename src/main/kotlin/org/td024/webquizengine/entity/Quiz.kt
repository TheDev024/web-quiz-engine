package org.td024.webquizengine.entity

data class Quiz (
    val title: String,
    val text: String,
    val options: List<String>
)