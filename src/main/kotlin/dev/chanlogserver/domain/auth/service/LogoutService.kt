package dev.chanlogserver.domain.auth.service

import dev.chanlogserver.domain.auth.dto.request.LogoutRequestDto
import dev.chanlogserver.domain.auth.dto.response.CookieResponseDto

interface LogoutService {
  fun execute(data: LogoutRequestDto): CookieResponseDto
}