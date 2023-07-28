package org.td024.engine.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Quiz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String? = null,
    val text: String? = null,
    @ElementCollection
    val options: List<String>? = null,
    @JsonIgnore
    @ElementCollection
    val answer: List<Int>? = null
)
