package org.td024.engine.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.td024.engine.dao.UserDao

@Entity
@Table(name = "users")
data class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true)
    val email: String? = null,
    @JsonIgnore
    private val password: String? = null
) : UserDetails {

    @JsonIgnore
    override fun getAuthorities(): List<GrantedAuthority> = emptyList()

    override fun getPassword(): String = password!!

    @JsonIgnore
    override fun getUsername(): String = email!!

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean = true

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean = true

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean = true

    @JsonIgnore
    override fun isEnabled(): Boolean = true

    companion object {

        private val encoder = BCryptPasswordEncoder()

        fun build(userDao: UserDao) = AppUser(email = userDao.email, password = encoder.encode(userDao.password))
    }
}
