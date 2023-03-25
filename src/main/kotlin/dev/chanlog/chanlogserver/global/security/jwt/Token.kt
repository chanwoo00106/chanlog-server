package dev.chanlog.chanlogserver.global.security.jwt

import io.jsonwebtoken.Claims
import java.util.Date

data class Token (
  val token: String,
  val expired: Int
)
