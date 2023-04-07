package dev.chanlogserver.domain.auth.service.impl

import dev.chanlogserver.domain.auth.dto.request.LogoutRequestDto
import dev.chanlogserver.domain.auth.dto.response.CookieResponseDto
import dev.chanlogserver.domain.auth.service.LogoutService
import dev.chanlogserver.domain.user.repository.UserRepository
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import dev.chanlogserver.global.security.jwt.TokenType
import jakarta.servlet.http.Cookie
import org.springframework.stereotype.Service

@Service
class LogoutServiceImpl(
  private val userRepository: UserRepository
): LogoutService {
  override fun execute(data: LogoutRequestDto): CookieResponseDto {
    userCheck(data.authentication.name)

    return CookieResponseDto(
      createCookie(TokenType.ACCESS),
      createCookie(TokenType.REFRESH)
    )
  }

  private fun userCheck(email: String) {
    userRepository.findById(email)
      .orElseThrow { BasicException(ErrorCode.INVALID_TOKEN) }
  }

  private fun createCookie(tokenType: TokenType)
    = Cookie(tokenType.type, null)
}