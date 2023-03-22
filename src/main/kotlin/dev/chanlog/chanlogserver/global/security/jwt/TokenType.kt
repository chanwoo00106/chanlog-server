package dev.chanlog.chanlogserver.global.security.jwt

enum class TokenType(val type: String, val expired: Long) {
  ACCESS(
    "access-token",
    60L * 15 // 15 minutes
  ),
  REFRESH(
    "refresh-token",
    60L * 60 * 24 * 7 // 1 week
  );
}