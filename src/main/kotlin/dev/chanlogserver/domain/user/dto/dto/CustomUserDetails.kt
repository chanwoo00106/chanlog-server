package dev.chanlogserver.domain.user.dto.dto

import dev.chanlogserver.domain.user.User
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
  private val user: User
): UserDetails {
  override fun getAuthorities()
    = mutableListOf(user.role)

  override fun getPassword()
    = user.password

  override fun getUsername()
    = user.email

  override fun isAccountNonExpired() = true

  override fun isAccountNonLocked() = true

  override fun isCredentialsNonExpired() = true

  override fun isEnabled() = true
}
