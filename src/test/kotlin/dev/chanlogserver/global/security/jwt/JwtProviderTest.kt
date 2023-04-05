package dev.chanlogserver.global.security.jwt

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.PropertySource
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
@PropertySource("test")
class JwtProviderTest(
  @Autowired
  private val passwordEncoder: PasswordEncoder
) {
  @Test
  fun test() {
    println(passwordEncoder.encode("123"))
  }
}