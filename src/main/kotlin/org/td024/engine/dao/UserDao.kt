package org.td024.engine.dao

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min

data class UserDao(
    @field:Email
    val email: String,
    @field:Min(8)
    val password: String
)
