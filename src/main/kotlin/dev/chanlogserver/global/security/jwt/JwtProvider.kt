package dev.chanlogserver.global.security.jwt

import dev.chanlogserver.domain.auth.dto.dto.TokenDto
import dev.chanlogserver.domain.user.Role
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date

@Component
class JwtProvider {
  @Value("\${jwt.accessSecret}")
  private lateinit var accessSecret: String
  @Value("\${jwt.refreshSecret}")
  private lateinit var refreshSecret: String

  fun createToken(tokenType: TokenType, payload: Map<String, Any>): TokenDto {
    val expired = Date(System.currentTimeMillis() + tokenType.expired * 1000)
    val token = Jwts.builder()
      .setHeader(createJwtHeader(tokenType))
      .signWith(stringToKey(getSecretKey(tokenType)), SignatureAlgorithm.HS256)
      .addClaims(payload)
      .setSubject(tokenType.type)
      .setExpiration(expired)
      .compact()

    return TokenDto(token, tokenType.expired)
  }

  fun parseToken(tokenType: TokenType, token: String): Claims {
    return try {
      Jwts.parserBuilder()
        .setSigningKey(stringToKey(getSecretKey(tokenType)))
        .build()
        .parseClaimsJws(token)
        .body
    } catch (e: ExpiredJwtException) {
      throw BasicException(ErrorCode.EXPIRED_TOKEN)
    } catch (e: Exception) {
      throw BasicException(ErrorCode.INVALID_TOKEN)
    }
  }

  fun createPayload(id: String, role: Role): Map<String, Any> {
    val payload = HashMap<String, Any>()
    payload["id"] = id
    payload["role"] = role.name

    return payload
  }

  private fun createJwtHeader(type: TokenType): Map<String, Any> {
    val headers = HashMap<String, Any>()
    headers["type"] = type.type
    headers["alg"] = "HS256"

    return headers
  }

  private fun getSecretKey(type: TokenType): String =
    if (type.type == "access-token") accessSecret else refreshSecret

  private fun stringToKey(secret: String): Key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
}