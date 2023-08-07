package org.td024.engine.repo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.td024.engine.entity.AppUser
import org.td024.engine.entity.Quiz
import org.td024.engine.entity.Solution

@Repository
interface SolutionRepository : JpaRepository<Solution, Long> {

    fun findSolutionsByUserOrderByCompletedAt(user: AppUser, pageable: Pageable): Page<Solution>

    fun deleteAllByQuiz(quiz: Quiz)
}
