package dev.chanlogserver.domain.auth.dto.dto

import jakarta.validation.constraints.NotBlank

data class LoginDto (
  @field:NotBlank
  private val email: String,

  @field:NotBlank
  private val password: String
)
