package dev.chanlogserver.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class LoginRequestDto (
  @field:NotBlank
  val email: String,

  @field:NotBlank
  val password: String
)