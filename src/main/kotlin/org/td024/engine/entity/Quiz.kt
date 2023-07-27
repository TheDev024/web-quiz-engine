package org.td024.engine.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

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
    val answer: Int = 0
)
