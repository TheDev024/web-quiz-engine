package org.td024.engine.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(@Autowired private val userService: UserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userService.findUserByEmail(username) ?: throw UsernameNotFoundException("User not found with email $username")
}
