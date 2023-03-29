package dev.chanlog.chanlogserver.domain.auth.service.impl

import dev.chanlog.chanlogserver.domain.auth.dto.response.CookiesResponseDto
import dev.chanlog.chanlogserver.domain.auth.repository.RefreshRepository
import dev.chanlog.chanlogserver.domain.auth.service.LogoutService
import dev.chanlog.chanlogserver.domain.user.repository.UserRepository
import dev.chanlog.chanlogserver.global.exception.BasicException
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import jakarta.servlet.http.Cookie
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class LogoutServiceImpl(
  private val userRepository: UserRepository,
  private val refreshRepository: RefreshRepository
): LogoutService {
  override fun execute(authentication: Authentication): CookiesResponseDto {
    userCheck(authentication.name)

    return CookiesResponseDto(
      createEmptyCookie("accessToken"),
      createEmptyCookie("refreshToken")
    )
  }

  private fun userCheck(id: String) {
    val user = userRepository.findById(id)
      .orElseThrow { BasicException(ErrorCode.INVALID_TOKEN) }

    user.refreshToken?.let {
      user.refreshToken = null
      userRepository.save(user)
      refreshRepository.delete(it)
    }
  }

  private fun createEmptyCookie(name: String): Cookie {
    val cookie = Cookie(name, null)
    cookie.maxAge = 0

    return cookie
  }
}