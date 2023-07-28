package org.td024.engine.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT, reason = "User with this email already exists!")
class UserExistsException(override val message: String = "User with this email already exists!") : RuntimeException()
