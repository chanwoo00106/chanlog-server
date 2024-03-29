package dev.chanlogserver.domain.auth.controller

import dev.chanlogserver.domain.auth.dto.request.LoginRequestDto
import dev.chanlogserver.domain.auth.dto.request.LogoutRequestDto
import dev.chanlogserver.domain.auth.dto.request.ReissueRequestDto
import dev.chanlogserver.domain.auth.service.LoginService
import dev.chanlogserver.domain.auth.service.LogoutService
import dev.chanlogserver.domain.auth.service.ReissueService
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
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
class AuthController(
  private val loginService: LoginService,
  private val reissueService: ReissueService,
  private val logoutService: LogoutService
) {
  @PostMapping
  fun login(@RequestBody @Valid body: LoginRequestDto, response: HttpServletResponse) {
    val cookies = loginService.execute(body)
    response.addCookie(cookies.access)
    response.addCookie(cookies.refresh)
  }

  @PatchMapping
  fun reissue(@CookieValue("refresh-token") refreshToken: String, authentication: Authentication, response: HttpServletResponse) {
    val cookies = reissueService.execute(ReissueRequestDto(authentication, refreshToken))
    response.addCookie(cookies.access)
    response.addCookie(cookies.refresh)
  }

  @DeleteMapping
  fun logout(authentication: Authentication, response: HttpServletResponse) {
    val cookies = logoutService.execute(LogoutRequestDto(authentication))

    response.addCookie(cookies.access)
    response.addCookie(cookies.refresh)
  }
}