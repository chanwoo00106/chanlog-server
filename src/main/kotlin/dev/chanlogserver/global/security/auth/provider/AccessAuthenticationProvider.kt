package dev.chanlogserver.global.security.auth.provider

import dev.chanlogserver.domain.user.Role
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import dev.chanlogserver.global.security.auth.authentication.AccessAuthentication
import dev.chanlogserver.global.security.jwt.JwtProvider
import dev.chanlogserver.global.security.jwt.TokenType
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class AccessAuthenticationProvider(
  private val jwtProvider: JwtProvider
): AuthenticationProvider {
  override fun authenticate(authentication: Authentication): Authentication {
    val data = jwtProvider.parseToken(TokenType.ACCESS, authentication.name)
    if (data["id"] == null || Role.valueOf(data["role"]) == null)
      throw BasicException(ErrorCode.INVALID_TOKEN)


    return UsernamePasswordAuthenticationToken(data["id"], null, mutableListOf(Role.valueOf(data["role"])))
  }

  override fun supports(authentication: Class<*>)
    = AccessAuthentication::class.java.isAssignableFrom(authentication)
}