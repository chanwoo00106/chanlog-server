package dev.chanlog.chanlogserver.domain.auth.dto.request

import org.springframework.security.core.Authentication

data class ReissueTokenRequestDto (
  val authentication: Authentication,
  val refreshToken: String
)