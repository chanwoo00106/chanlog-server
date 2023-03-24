package dev.chanlog.chanlogserver.global.security.auth.provider

import dev.chanlog.chanlogserver.global.security.auth.authentication.AccessAuthentication
import dev.chanlog.chanlogserver.global.security.jwt.JwtProvider
import dev.chanlog.chanlogserver.global.security.jwt.TokenType
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AccessAuthenticationProvider(
  private val jwtProvider: JwtProvider,
  private val userDetailsService: UserDetailsService
): AuthenticationProvider {
  override fun authenticate(authentication: Authentication): Authentication {
    val claims = jwtProvider.parseToken(TokenType.ACCESS, authentication.name)

    val user = userDetailsService.loadUserByUsername(claims["id"].toString())

    return UsernamePasswordAuthenticationToken(
      user.username,
      user.password,
      user.authorities
    )
  }

  override fun supports(authentication: Class<*>): Boolean {
    return authentication == AccessAuthentication::class.java
  }
}