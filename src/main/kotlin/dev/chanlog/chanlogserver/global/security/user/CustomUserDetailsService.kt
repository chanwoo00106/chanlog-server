package dev.chanlog.chanlogserver.global.security.user

import dev.chanlog.chanlogserver.domain.user.repository.UserRepository
import dev.chanlog.chanlogserver.global.exception.BasicException
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import dev.chanlog.chanlogserver.global.security.jwt.JwtProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
  private val userRepository: UserRepository,
  private val jwtProvider: JwtProvider
): UserDetailsService {
  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.findById(username).orElse(null)
      ?: throw BasicException(ErrorCode.INVALID_TOKEN)

    return CustomUserDetails(user, jwtProvider)
  }
}