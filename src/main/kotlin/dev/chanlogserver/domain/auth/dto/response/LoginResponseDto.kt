package dev.chanlogserver.domain.auth.dto.response

import jakarta.servlet.http.Cookie

data class LoginResponseDto (
  val access: Cookie,
  val refresh: Cookie
)
