package org.td024.engine.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.td024.engine.entity.AppUser
import org.td024.engine.entity.Quiz
import org.td024.engine.entity.Solution
import org.td024.engine.repo.SolutionRepository

@Service
class SolutionService(@Autowired private val solutionRepository: SolutionRepository) {

    fun getAllByUser(page: Int, size: Int, user: AppUser) =
        solutionRepository.findSolutionsByUserOrderByCompletedAt(user, PageRequest.of(page, size))

    fun deleteAllByQuiz(quiz: Quiz) = solutionRepository.deleteAllByQuiz(quiz)

    fun save(solution: Solution) = solutionRepository.save(solution)
}
