package dev.chanlogserver.domain.auth.service.impl

import dev.chanlogserver.domain.auth.dto.request.LogoutRequestDto
import dev.chanlogserver.domain.auth.dto.response.CookieResponseDto
import dev.chanlogserver.domain.auth.service.LogoutService
import dev.chanlogserver.domain.user.User
import dev.chanlogserver.domain.user.repository.UserRepository
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import dev.chanlogserver.global.security.jwt.TokenType
import jakarta.servlet.http.Cookie
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class LogoutServiceImpl(
  private val userRepository: UserRepository
): LogoutService {
  @Value("\${jwt.domain}")
  private lateinit var cookieDomain: String
  @Value("\${environment}")
  private lateinit var environment: String

  override fun execute(data: LogoutRequestDto): CookieResponseDto {
    val user = userCheck(data.authentication.name)

    deleteRefresh(user)

    return CookieResponseDto(
      createCookie(TokenType.ACCESS),
      createCookie(TokenType.REFRESH)
    )
  }

  private fun userCheck(email: String)
    = userRepository.findById(email)
      .orElseThrow { BasicException(ErrorCode.INVALID_TOKEN) }

  private fun deleteRefresh(user: User) {
    user.refresh.token = null
    userRepository.save(user)
  }

  private fun createCookie(tokenType: TokenType): Cookie {
    val cookie = Cookie(tokenType.type, null)
    cookie.maxAge = 0
    cookie.domain = cookieDomain
    cookie.path = "/"
    cookie.isHttpOnly = true
    cookie.secure = environment == "product"

    return cookie
  }
}