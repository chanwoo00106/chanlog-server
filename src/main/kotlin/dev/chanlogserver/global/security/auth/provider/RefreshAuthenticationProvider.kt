package dev.chanlogserver.global.security.auth.provider

import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import dev.chanlogserver.global.security.auth.authentication.RefreshAuthentication
import dev.chanlogserver.global.security.jwt.JwtProvider
import dev.chanlogserver.global.security.jwt.TokenType
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService

class RefreshAuthenticationProvider(
  private val jwtProvider: JwtProvider,
  private val userDetailsService: UserDetailsService
): AuthenticationProvider {
  override fun authenticate(authentication: Authentication): Authentication {
    val data = jwtProvider.parseToken(TokenType.REFRESH, authentication.name)
    if (data["id"] == null) throw BasicException(ErrorCode.INVALID_TOKEN)

    val userDetails = userDetailsService.loadUserByUsername(data["id"].toString())

    return UsernamePasswordAuthenticationToken(userDetails.username, userDetails.password, userDetails.authorities)
  }

  override fun supports(authentication: Class<*>) = RefreshAuthentication::class == authentication::class
}