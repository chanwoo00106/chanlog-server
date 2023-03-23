package dev.chanlog.chanlogserver.domain.auth.dto.dto

import dev.chanlog.chanlogserver.global.security.jwt.Token

data class CreateJwtTokenDto (
  val accessJwt: Token,
  val refreshJwt: Token
)