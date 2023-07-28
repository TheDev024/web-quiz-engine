package org.td024.engine.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
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

    override fun getAuthorities(): List<GrantedAuthority> = emptyList()

    override fun getPassword(): String = password!!

    override fun getUsername(): String = email!!

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    companion object {
        fun build(userDao: UserDao) = AppUser(email = userDao.email, password = userDao.password)
    }
}
