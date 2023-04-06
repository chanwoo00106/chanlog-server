package dev.chanlogserver.global.security.jwt

enum class TokenType(val type: String, val expired: Int) {
  ACCESS(
    "access-token",
    60 * 15 // 15 minutes
  ),
  REFRESH(
    "refresh-token",
    60 * 60 * 24 * 7 // 1 week
  );
}