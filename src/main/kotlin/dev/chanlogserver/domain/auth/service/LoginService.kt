package dev.chanlogserver.domain.auth.service

import dev.chanlogserver.domain.auth.dto.request.LoginRequestDto
import dev.chanlogserver.domain.auth.dto.response.CookieResponseDto

interface LoginService {
  fun execute(body: LoginRequestDto): CookieResponseDto
}