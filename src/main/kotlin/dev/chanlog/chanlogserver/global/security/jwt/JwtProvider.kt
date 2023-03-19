package dev.chanlog.chanlogserver.global.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class JwtProvider {

  fun createToken(type: TokenType, payload: Map<String, Any>) =
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