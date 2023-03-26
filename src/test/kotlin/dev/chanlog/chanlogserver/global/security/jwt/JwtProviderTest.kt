package dev.chanlog.chanlogserver.global.security.jwt

import dev.chanlog.chanlogserver.domain.user.Role
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class JwtProviderTest (
  @Autowired
  private val jwtProvider: JwtProvider
) {
  @Test
  @DisplayName("create access token test")
  fun createAccessToken() {
    val payload = jwtProvider.createPayload("123", Role.ADMIN_ROLE)
    val token = jwtProvider.createToken(TokenType.ACCESS, payload)

    val claims = jwtProvider.parseToken(TokenType.ACCESS, token.token)
    Assertions.assertThat(claims["id"]).isEqualTo("123")
  }

  @Test
  @DisplayName("create refresh token test")
  fun createRefreshToken() {
    val payload = jwtProvider.createPayload("123", Role.ADMIN_ROLE)
    val token = jwtProvider.createToken(TokenType.REFRESH, payload)

    val claims = jwtProvider.parseToken(TokenType.REFRESH, token.token)
    Assertions.assertThat(claims["id"]).isEqualTo("123")
  }
}