package dev.chanlog.chanlogserver.domain.auth.controller

import dev.chanlog.chanlogserver.domain.auth.dto.request.ReissueTokenRequestDto
import dev.chanlog.chanlogserver.domain.auth.dto.request.SigninRequestDto
import dev.chanlog.chanlogserver.domain.auth.service.LogoutService
import dev.chanlog.chanlogserver.domain.auth.service.ReissueTokenService
import dev.chanlog.chanlogserver.domain.auth.service.SigninService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController (
  private val signinService: SigninService,
  private val reissueTokenService: ReissueTokenService,
  private val logoutService: LogoutService
) {
  @PostMapping
  fun signin(@RequestBody data: SigninRequestDto, response: HttpServletResponse) {
    val tokens = this.signinService.execute(data)

    response.addCookie(tokens.accessCookie)
    response.addCookie(tokens.refreshCookie)
  }

  @PatchMapping
  fun reissue(@CookieValue("refreshToken") refreshToken: String, authentication: Authentication, response: HttpServletResponse) {
    val tokens = reissueTokenService.execute(ReissueTokenRequestDto(authentication, refreshToken))

    response.addCookie(tokens.accessCookie)
    response.addCookie(tokens.refreshCookie)
  }

  @DeleteMapping
  fun logout(authentication: Authentication, response: HttpServletResponse) {
    val cookies = logoutService.execute(authentication)

    response.addCookie(cookies.accessCookie)
    response.addCookie(cookies.refreshCookie)
  }
}