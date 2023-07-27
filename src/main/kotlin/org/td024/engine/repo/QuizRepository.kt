package org.td024.engine.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.td024.engine.entity.Quiz

@Repository
interface QuizRepository : JpaRepository<Quiz, Long> {

    fun findQuizById(id: Long): Quiz?
}
