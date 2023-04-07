package dev.chanlogserver.domain.auth.service.impl

import dev.chanlogserver.domain.auth.dto.dto.TokenDto
import dev.chanlogserver.domain.auth.dto.dto.Tokens
import dev.chanlogserver.domain.auth.dto.request.LoginRequestDto
import dev.chanlogserver.domain.auth.dto.response.CookieResponseDto
import dev.chanlogserver.domain.auth.service.LoginService
import dev.chanlogserver.domain.user.User
import dev.chanlogserver.domain.user.repository.UserRepository
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import dev.chanlogserver.global.security.jwt.JwtProvider
import dev.chanlogserver.global.security.jwt.TokenType
import jakarta.servlet.http.Cookie
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginServiceImpl(
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder,
  private val jwtProvider: JwtProvider,
): LoginService {
  @Value("\${jwt.domain}")
  private lateinit var cookieDomain: String
  @Value("\${environment}")
  private lateinit var environment: String

  @Transactional
  override fun execute(body: LoginRequestDto): CookieResponseDto {
    val user = findUserAndCheck(body)

    val tokens = createTokens(user)

    refreshTokenSave(tokens.refresh.token, user)

    return CookieResponseDto(
      createCookie(TokenType.ACCESS, tokens.access),
      createCookie(TokenType.REFRESH, tokens.refresh)
    )
  }

  private fun findUserAndCheck(data: LoginRequestDto): User {
    val user = userRepository.findById(data.email).orElseThrow {
      throw BasicException(ErrorCode.FAILED_LOGIN)
    }

    if (!passwordEncoder.matches(data.password, user.password))
      throw BasicException(ErrorCode.FAILED_LOGIN)

    return user
  }

  fun createTokens(user: User): Tokens {
    val payload = jwtProvider.createPayload(user.email, user.role)

    return Tokens(
      jwtProvider.createToken(TokenType.ACCESS, payload),
      jwtProvider.createToken(TokenType.REFRESH, payload)
    )
  }

  fun refreshTokenSave(refreshToken: String, user: User) {
    user.refresh.token = refreshToken
    userRepository.save(user)
  }

  fun createCookie(tokenType: TokenType, token: TokenDto): Cookie {
    val cookie = Cookie(tokenType.type, token.token)
    cookie.maxAge = token.expired
    cookie.domain = cookieDomain
    cookie.path = "/"
    cookie.isHttpOnly = true
    cookie.secure = environment == "product"

    return cookie
  }
}