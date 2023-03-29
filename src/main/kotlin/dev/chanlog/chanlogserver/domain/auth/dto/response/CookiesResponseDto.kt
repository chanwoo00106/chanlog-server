package dev.chanlog.chanlogserver.domain.auth.dto.response

import jakarta.servlet.http.Cookie

data class CookiesResponseDto (
  val accessCookie: Cookie,
  val refreshCookie: Cookie
)