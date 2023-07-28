package org.td024.engine.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.td024.engine.handler.AuthEntryPoint
import org.td024.engine.service.CustomUserDetailsService

@Configuration
class SecurityConfig(@Autowired private val userDetailsService: CustomUserDetailsService) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authFilter(): AuthenticationEntryPoint = AuthEntryPoint()

    @Bean
    fun authProvider(): DaoAuthenticationProvider {
        val auth = DaoAuthenticationProvider()

        auth.setUserDetailsService(userDetailsService)
        auth.setPasswordEncoder(passwordEncoder())

        return auth
    }

    @Bean
    fun authManager(authConfig: AuthenticationConfiguration): AuthenticationManager = authConfig.authenticationManager

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http.csrf { csrf -> csrf.disable() }
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .exceptionHandling { handler -> handler.authenticationEntryPoint(authFilter()) }
        .authenticationProvider(authProvider())
        .authorizeHttpRequests { auth ->
            auth.requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                .anyRequest().authenticated()
        }.build()
}