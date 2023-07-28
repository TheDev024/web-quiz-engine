package org.td024.engine.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.td024.engine.entity.AppUser

@Repository
interface UserRepository : JpaRepository<AppUser, Long> {

    fun findAppUserByEmail(email: String): AppUser?

    fun existsAppUserByEmail(email: String): Boolean
}
