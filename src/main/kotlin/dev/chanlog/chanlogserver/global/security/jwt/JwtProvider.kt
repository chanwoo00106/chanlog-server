package dev.chanlog.chanlogserver.global.security.jwt

import dev.chanlog.chanlogserver.global.exception.BasicException
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import kotlin.collections.HashMap

@Component
class JwtProvider {
  @Value("\${jwt.accessSecret}")
  private lateinit var accessSecret: String
  @Value("\${jwt.refreshSecret}")
  private lateinit var refreshSecret: String

  fun parseToken(type: TokenType, token: String): Claims {
    return try {
      Jwts.parserBuilder()
        .setSigningKey(stringToKey(getSecretKey(type)))
        .build()
        .parseClaimsJws(token)
        .body
      } catch (e: ExpiredJwtException) {
        throw BasicException(ErrorCode.EXPIRED_TOKEN)
      } catch (e: Exception) {
        throw BasicException(ErrorCode.INVALID_TOKEN)
      }
    }

  fun createToken(type: TokenType, payload: Map<String, Any>): Token {
    val expired = Date(System.currentTimeMillis() + type.expired * 1000)
    val token = Jwts.builder()
      .setHeader(createJwtHeader(type))
      .signWith(stringToKey(getSecretKey(type)), SignatureAlgorithm.HS256)
      .addClaims(payload)
      .setSubject(type.type)
      .setExpiration(expired)
      .compact()

    return Token(token, expired)
  }

  fun createPayload(id: String): Map<String, Any> {
    val payload = HashMap<String, Any>()
    payload["id"] = id

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