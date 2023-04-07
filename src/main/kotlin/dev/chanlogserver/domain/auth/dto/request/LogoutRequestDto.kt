package dev.chanlogserver.domain.auth.dto.request

import org.springframework.security.core.Authentication

data class LogoutRequestDto (
  val authentication: Authentication
)