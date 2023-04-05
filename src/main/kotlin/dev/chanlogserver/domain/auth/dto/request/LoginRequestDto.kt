package dev.chanlogserver.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class LoginRequestDto (
  @field:NotBlank
  private val email: String?,

  @field:NotBlank
  private val password: String?
)