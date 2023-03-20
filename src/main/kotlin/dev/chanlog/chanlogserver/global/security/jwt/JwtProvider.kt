package dev.chanlog.chanlogserver.global.security.jwt

import dev.chanlog.chanlogserver.global.security.exception.BasicException
import dev.chanlog.chanlogserver.global.security.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class JwtProvider {

  fun parseToken(type: TokenType, token: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(type.getSecret())
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw BasicException(ErrorCode.EXPIRED_TOKEN)
        } catch (e: Exception) {
            throw BasicException(ErrorCode.INVALID_TOKEN)
        }
    }

  fun createToken(type: TokenType, payload: Map<String, Any>): String =
    Jwts.builder()
      .setHeader(createJwtHeader(type))
      .signWith(type.getSecret(), SignatureAlgorithm.HS256)
      .addClaims(payload)
      .setSubject(type.type)
      .setExpiration(Date(System.currentTimeMillis() + type.expired * 1000))
      .compact()

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
}