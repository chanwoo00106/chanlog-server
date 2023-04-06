package dev.chanlogserver.domain.user.service

import dev.chanlogserver.domain.user.dto.dto.CustomUserDetails
import dev.chanlogserver.domain.user.repository.UserRepository
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
  private val userRepository: UserRepository
): UserDetailsService {
  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.findById(username)
      .orElseThrow { BasicException(ErrorCode.INVALID_TOKEN) }

    return CustomUserDetails(user)
  }
}