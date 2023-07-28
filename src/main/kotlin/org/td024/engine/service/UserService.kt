package org.td024.engine.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.td024.engine.dao.UserDao
import org.td024.engine.entity.AppUser
import org.td024.engine.exception.UserExistsException
import org.td024.engine.repo.UserRepository

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    fun findUserByEmail(email: String) = userRepository.findAppUserByEmail(email)

    fun save(userDao: UserDao): AppUser {
        if (userRepository.existsAppUserByEmail(userDao.email)) throw UserExistsException()

        return userRepository.save(AppUser.build(userDao))
    }
}
