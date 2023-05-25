package org.td024.webquizengine.entity

import jakarta.persistence.*
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Entity
data class Quiz (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val title: String? = null,
    val text: String? = null,
    @ElementCollection(fetch = FetchType.EAGER)
    val options: List<String>? = null,
    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection(fetch = FetchType.EAGER)
    val answer: List<Int>? = null
)
