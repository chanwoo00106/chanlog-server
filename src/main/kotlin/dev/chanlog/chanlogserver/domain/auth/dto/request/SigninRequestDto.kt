package dev.chanlog.chanlogserver.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class SigninRequestDto (
  @field:NotBlank
  val id: String,

  @field:NotBlank
  val password: String
)