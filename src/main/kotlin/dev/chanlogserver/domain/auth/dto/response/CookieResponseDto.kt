package dev.chanlogserver.domain.auth.dto.response

import jakarta.servlet.http.Cookie

data class CookieResponseDto (
  val access: Cookie,
  val refresh: Cookie
)
