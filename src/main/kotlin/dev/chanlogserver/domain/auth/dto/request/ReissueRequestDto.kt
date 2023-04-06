package dev.chanlogserver.domain.auth.dto.request

import org.springframework.security.core.Authentication

data class ReissueRequestDto (
  val authentication: Authentication,
  val refreshToken: String
)