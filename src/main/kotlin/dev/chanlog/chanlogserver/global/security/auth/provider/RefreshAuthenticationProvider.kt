package dev.chanlog.chanlogserver.global.security.auth.provider

import dev.chanlog.chanlogserver.global.security.auth.authentication.RefreshAuthentication
import dev.chanlog.chanlogserver.global.security.auth.user.UserDetailsService
import dev.chanlog.chanlogserver.global.security.jwt.JwtProvider
import dev.chanlog.chanlogserver.global.security.jwt.TokenType
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class RefreshAuthenticationProvider(
  private val jwtProvider: JwtProvider,
  private val userDetailsService: UserDetailsService
): AuthenticationProvider {

  override fun authenticate(authentication: Authentication): Authentication {
    val claims = jwtProvider.parseToken(TokenType.REFRESH, authentication.name)

    val user = userDetailsService.loadUserByUsername(claims)

    return UsernamePasswordAuthenticationToken(
      user.username,
      user.password,
      user.authorities
    )
  }

  override fun supports(authentication: Class<*>): Boolean {
    return authentication == RefreshAuthentication::class.java
  }
}