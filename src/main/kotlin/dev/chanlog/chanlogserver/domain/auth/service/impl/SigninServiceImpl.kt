package dev.chanlog.chanlogserver.domain.auth.service.impl

import dev.chanlog.chanlogserver.domain.auth.dto.request.SigninRequestDto
import dev.chanlog.chanlogserver.domain.auth.dto.response.SigninResponseDto
import dev.chanlog.chanlogserver.domain.auth.entity.Refresh
import dev.chanlog.chanlogserver.domain.auth.repository.RefreshRepository
import dev.chanlog.chanlogserver.domain.auth.service.SigninService
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
  override fun execute(data: SigninRequestDto): SigninResponseDto {
    val user = userRepository.findById(data.id).orElse(null) ?: throw BasicException(ErrorCode.FAILED_TOKEN)

    val matches = passwordEncoder.matches(data.password, user.password)
    if (!matches) throw BasicException(ErrorCode.FAILED_TOKEN)

    val payload = jwtProvider.createPayload(data.id)
    val accessJwt = jwtProvider.createToken(TokenType.ACCESS, payload)
    val refreshJwt = jwtProvider.createToken(TokenType.REFRESH, payload)

    refreshRepository.save(Refresh(refreshJwt.token))

    val accessToken = createCookie("accessToken", accessJwt)
    val refreshToken = createCookie("refreshToken", refreshJwt)

    return SigninResponseDto(accessToken, refreshToken)
  }

  fun createCookie(name: String, token: Token): Cookie {
    val cookie = Cookie(name, token.token)
    cookie.isHttpOnly = true
    cookie.maxAge = token.expired.time.toInt()
    cookie.secure = true
    cookie.path = "/"
    cookie.domain = this.cookieDomain

    return cookie
  }
}