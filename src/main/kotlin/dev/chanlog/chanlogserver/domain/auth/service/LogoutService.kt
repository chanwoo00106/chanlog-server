package dev.chanlog.chanlogserver.domain.auth.service

import dev.chanlog.chanlogserver.domain.auth.dto.response.CookiesResponseDto
import org.springframework.security.core.Authentication

interface LogoutService {
  fun execute(authentication: Authentication): CookiesResponseDto
}