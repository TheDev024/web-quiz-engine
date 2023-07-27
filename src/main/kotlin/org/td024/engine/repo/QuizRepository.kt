package org.td024.engine.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.td024.engine.entity.Quiz

interface QuizRepository : JpaRepository<Quiz, Long>
