package dev.chanlogserver.domain.auth.controller

import dev.chanlogserver.domain.auth.dto.request.LoginRequestDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
  @PostMapping
  fun login(@RequestBody @Valid body: LoginRequestDto) {

  }
}