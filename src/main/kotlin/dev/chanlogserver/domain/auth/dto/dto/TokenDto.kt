package dev.chanlogserver.domain.auth.dto.dto

data class TokenDto (
  val token: String,
  val expired: Int
)