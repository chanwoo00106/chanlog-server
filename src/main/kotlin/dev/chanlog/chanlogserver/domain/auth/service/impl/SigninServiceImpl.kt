package dev.chanlog.chanlogserver.domain.auth.service.impl

import dev.chanlog.chanlogserver.domain.auth.dto.dto.CreateJwtTokenDto
import dev.chanlog.chanlogserver.domain.auth.dto.request.SigninRequestDto
import dev.chanlog.chanlogserver.domain.auth.dto.response.CookiesResponseDto
import dev.chanlog.chanlogserver.domain.auth.entity.Refresh
import dev.chanlog.chanlogserver.domain.auth.repository.RefreshRepository
import dev.chanlog.chanlogserver.domain.auth.service.SigninService
import dev.chanlog.chanlogserver.domain.user.entity.User
import dev.chanlog.chanlogserver.domain.user.repository.UserRepository
import dev.chanlog.chanlogserver.global.exception.BasicException
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import dev.chanlog.chanlogserver.global.security.jwt.JwtProvider
import dev.chanlog.chanlogserver.global.security.jwt.Token
import dev.chanlog.chanlogserver.global.security.jwt.TokenType
import jakarta.servlet.http.Cookie
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SigninServiceImpl(
  val userRepository: UserRepository,
  val refreshRepository: RefreshRepository,
  val passwordEncoder: PasswordEncoder,
  val jwtProvider: JwtProvider
): SigninService {
  @Value("\${jwt.domain}")
  private lateinit var cookieDomain: String
  @Value("\${environment}")
  private lateinit var environment: String

  override fun execute(data: SigninRequestDto): CookiesResponseDto {
    val user = userCheck(data)

    val tokens = createJwtToken(user)

    saveRefresh(tokens.refreshJwt, user)

    val accessToken = createCookie("accessToken", tokens.accessJwt)
    val refreshToken = createCookie("refreshToken", tokens.refreshJwt)

    return CookiesResponseDto(accessToken, refreshToken)
  }

  private fun userCheck(data: SigninRequestDto): User {
    val user = userRepository.findById(data.id).orElse(null) ?: throw BasicException(ErrorCode.FAILED_TOKEN)

    val matches = passwordEncoder.matches(data.password, user.password)
    if (!matches) throw BasicException(ErrorCode.FAILED_TOKEN)

    return user
  }

  fun createJwtToken(user: User): CreateJwtTokenDto {
    val payload = jwtProvider.createPayload(user.id, user.role)
    val accessJwt = jwtProvider.createToken(TokenType.ACCESS, payload)
    val refreshJwt = jwtProvider.createToken(TokenType.REFRESH, payload)

    return CreateJwtTokenDto(accessJwt, refreshJwt)
  }

  fun saveRefresh(token: Token, user: User) {
    val refresh = Refresh(token.token)
    user.refreshToken?.let {
      user.refreshToken = null
      userRepository.save(user)
      refreshRepository.delete(it)
    }

    user.refreshToken = refreshRepository.save(refresh)
    userRepository.save(user)
  }

  fun createCookie(name: String, token: Token): Cookie {
    val cookie = Cookie(name, token.token)
    cookie.isHttpOnly = true
    cookie.maxAge = token.expired
    cookie.secure = environment == "product"
    cookie.path = "/"
    cookie.domain = this.cookieDomain

    return cookie
  }
}