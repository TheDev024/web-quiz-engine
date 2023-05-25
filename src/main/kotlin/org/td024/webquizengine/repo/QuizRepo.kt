package org.td024.webquizengine.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.td024.webquizengine.entity.Quiz

interface QuizRepo : JpaRepository<Quiz, Int>
