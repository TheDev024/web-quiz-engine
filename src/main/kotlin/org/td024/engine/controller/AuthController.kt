package org.td024.engine.controller

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.td024.engine.dao.UserDao
import org.td024.engine.service.UserService

@RestController
@RequestMapping("/api/auth")
class AuthController(@Autowired private val userService: UserService) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody userDao: UserDao) = userService.save(userDao)
}