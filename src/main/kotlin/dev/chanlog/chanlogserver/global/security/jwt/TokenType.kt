package dev.chanlog.chanlogserver.global.security.jwt

import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import java.nio.charset.StandardCharsets
import java.security.Key

enum class TokenType(val type: String, val expired: Long) {
  ACCESS(
    "access-token",
    60L * 15 // 15 minutes
  ),
  REFRESH(
    "refresh-token",
    60L * 60 * 24 * 7 // 1 week
  );

  @Value("accessSecret")
  private lateinit var accessSecret: String
  @Value("refreshSecret")
  private lateinit var refreshSecret: String

  fun getSecret(): Key =
    if (this.type === "access-token") StringToKey(accessSecret) else StringToKey(refreshSecret)

  private fun StringToKey(secret: String): Key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
}