package dev.chanlogserver.domain.auth.controller

import dev.chanlogserver.domain.auth.dto.request.LoginRequestDto
import dev.chanlogserver.domain.auth.service.LoginService
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
  private val loginService: LoginService
) {
  @PostMapping
  fun login(@RequestBody @Valid body: LoginRequestDto, response: HttpServletResponse) {
    val cookies = loginService.execute(body)
    response.addCookie(cookies.access)
    response.addCookie(cookies.refresh)
  }
}