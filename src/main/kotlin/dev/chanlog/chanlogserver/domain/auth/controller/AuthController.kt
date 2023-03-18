package dev.chanlog.chanlogserver.domain.auth.controller

import dev.chanlog.chanlogserver.domain.auth.dto.request.SigninRequestDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
  @PostMapping
  fun signin(@RequestBody data: SigninRequestDto) {}
}