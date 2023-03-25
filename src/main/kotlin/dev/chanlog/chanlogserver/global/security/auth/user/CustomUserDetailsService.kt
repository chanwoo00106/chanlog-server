package dev.chanlog.chanlogserver.global.security.auth.user

import dev.chanlog.chanlogserver.domain.user.Role
import dev.chanlog.chanlogserver.global.exception.BasicException
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import io.jsonwebtoken.Claims
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService: UserDetailsService {
  override fun loadUserByUsername(claims: Claims): UserDetails {
    val username = claims["id"].toString()
    val role = claims["role"].toString()
    val hasRole = Role.values().filter { value -> value.toString() == role }

    if (username.isBlank() || hasRole.isEmpty()) throw BasicException(ErrorCode.INVALID_TOKEN)

    return CustomUserDetails(username, Role.valueOf(role))
  }
}